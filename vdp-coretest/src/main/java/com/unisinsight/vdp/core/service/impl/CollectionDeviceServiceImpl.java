/*
 * www.unisinsight.com Inc.
 * Copyright (c) 2018 All Rights Reserved
 */
package com.unisinsight.vdp.core.service.impl;
import com.unisinsight.framework.common.exception.BaseException;
import com.unisinsight.framework.common.utils.BeanCopyUtil;
import com.unisinsight.vdp.common.vms.response.bean.ResourceTreeNode;
import com.unisinsight.vdp.core.common.enums.VideoErrorCode;
import com.unisinsight.vdp.core.dto.request.CollectionDeviceReqDTO;
import com.unisinsight.vdp.core.dto.response.CollectionDeviceResDTO;
import com.unisinsight.vdp.core.manager.mapper.CollectionDeviceMapperManager;
import com.unisinsight.vdp.core.manager.mapper.CollectionGroupMapperManager;
import com.unisinsight.vdp.core.mapper.CollectionDeviceMapper;
import com.unisinsight.vdp.core.model.CollectionDevice;
import com.unisinsight.vdp.core.model.CollectionGroup;
import com.unisinsight.vdp.core.service.CollectionDeviceService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Condition;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 收藏视频资源ServiceImpl
 *
 * @author wangshuai [KF.wangshuaiB@h3c.com]
 * @date 2018/09/18
 * @since 1.0
 */
@Service
public class CollectionDeviceServiceImpl  implements CollectionDeviceService {
    /**
     * 注入collectionDeviceMapper
     */
    @Resource
    private CollectionDeviceMapper collectionDeviceMapper;
    /**
     * 注入collectionGroupMapperManager
     */
    @Resource
    private CollectionGroupMapperManager collectionGroupMapperManager;
    /**
     * 注入collectionDeviceMapperManager
     */
    @Resource
    private CollectionDeviceMapperManager collectionDeviceMapperManager;

    /**
     * 收藏资源
     * @param reqDTO 资源收藏请求参数
     * @return CollectionDeviceResDTO
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public CollectionDeviceResDTO save(CollectionDeviceReqDTO reqDTO) {
        //验证组是否有效
        if (!collectionGroupMapperManager.validGroupId(reqDTO.getGroupId())) {
            throw BaseException.of(VideoErrorCode.GROUP_NOT_EXIST.of());
        }
        //判断该监控点是否已经收藏了
        Condition condition = new Condition(CollectionGroup.class);
        condition.createCriteria().andCondition("group_id is not null");
        List<CollectionDevice> collectionDeviceList = collectionDeviceMapper.selectByCondition(condition);
        collectionDeviceList.forEach(c -> {
            if (c.getName().equals(reqDTO.getName())) {
                throw BaseException.of(VideoErrorCode.DUPLICATE_DEVICE_NAME.of());
            }
        });
        CollectionDevice collectionDevice = new CollectionDevice();
        //保存资源点
        collectionDevice.setCreateTime(new Date());
        //单个资源点收藏
        BeanUtils.copyProperties(reqDTO, collectionDevice);
        collectionDeviceMapper.insertSelective(collectionDevice);
        //设置时间戳字段的值
        return setTime(BeanCopyUtil.convert(collectionDevice, CollectionDeviceResDTO.class), collectionDevice);
    }

    /**
     * 批量收藏资源
     * @param reqDTOList 资源集合
     * @return  List<CollectionDeviceResDTO>
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<CollectionDeviceResDTO> batchSave(List<CollectionDeviceReqDTO> reqDTOList) {
        //验证传入的资源组是否相同
        int groupId = reqDTOList.get(0).getGroupId();
        reqDTOList.forEach(c -> {
            if (c.getGroupId() != groupId) {
                throw BaseException.of(VideoErrorCode.GROUPID_IS_DIFFERENT.of());
            }
        });
        //验证组是否有效
        if (!collectionGroupMapperManager.validGroupId(groupId)) {
            throw BaseException.of(VideoErrorCode.GROUP_NOT_EXIST.of());
        }
        //批量收藏时，若已经有资源点收藏了，与其他相同组，则更新，否则抛出异常
        Condition condition = new Condition(CollectionGroup.class);
        condition.createCriteria().andCondition("group_id is not null");
        List<CollectionDevice> collectionDeviceList = collectionDeviceMapper.selectByCondition(condition);
        //一个监控点不能收藏到两个资源组
        List<Long> collectionDeviceIds = new ArrayList<>();
        reqDTOList.forEach(g -> {
            String name = g.getName();
            collectionDeviceList.forEach(c -> {
                if (c.getName().equals(name) && c.getGroupId() != groupId) {
                    throw BaseException.of(VideoErrorCode.DUPLICATE_DEVICE_NAME.of());
                }
                if (c.getName().equals(name) && c.getGroupId() == groupId) {
                    collectionDeviceIds.add(c.getCollectionDeviceId());
                }
            });
        });
        //删除已经收藏的资源点
        if (!collectionDeviceIds.isEmpty()) {
            collectionDeviceMapper.deleteBatch(collectionDeviceIds);
        }
        //从资源树获取摄像机在线转态
        List<ResourceTreeNode> resourceTreeNodes = collectionDeviceMapperManager.getResourceTree();
        resourceTreeNodes.forEach(d -> {
            String id = d.getId();
            int status = d.getOnlineStatus();
            reqDTOList.forEach(e -> {
                if (id.equals(e.getId())) {
                    e.setOnlineStatus(status);
                }
            });
        });
        List<CollectionDevice> deviceList =  BeanCopyUtil.convertList(reqDTOList, CollectionDevice.class);
        deviceList.forEach(g -> g.setCreateTime(new Date()));
        //插入资源
        collectionDeviceMapper.batchInsert(deviceList);
        //doList转DTOList
        return doToDtoList(deviceList);
    }

    /**
     * 依据主键删除资源
     * @param id 主键
     */
    @Override
    public void deleteById(Integer id) {
        collectionDeviceMapperManager.deleteById(Long.valueOf(id));
    }

    /**
     * 依据id查询资源点
     * @param id 监控点主键
     * @return CollectionDeviceResDTO
     */
    @Override
    public CollectionDeviceResDTO findById(Integer id) {
        CollectionDevice collectionDevice = collectionDeviceMapper.selectByPrimaryKey(Long.valueOf(id));
        return BeanCopyUtil.convert(collectionDevice, CollectionDeviceResDTO.class);
    }

    /**
     * 根据名称模糊搜索资源
     * @param name 关键字
     * @return List<CollectionDeviceResDTO>
     */
    @Override
    public  List<CollectionDeviceResDTO> searchKey(String name) {
        Condition condition = new Condition(CollectionDevice.class);
        condition.createCriteria().andCondition("name like '%" + name + "%'");
        List<CollectionDevice> collectionDevicesList = collectionDeviceMapper.selectByCondition(condition);
        return doToDtoList(collectionDevicesList);
    }

    /**
     * createTime和updateTime设置时间戳
     * @param collectionDeviceResDTO:返回前端DTO
     * @param collectionDevice:从数据库获取的Model
     * @return CollectionDeviceResDTO
     */
    @Override
    public CollectionDeviceResDTO setTime(CollectionDeviceResDTO collectionDeviceResDTO, CollectionDevice collectionDevice) {
        if (null != collectionDevice.getCreateTime()) {
            collectionDeviceResDTO.setCreateTime(collectionDevice.getCreateTime().getTime());
        }
        if (null != collectionDevice.getUpdateTime()) {
            collectionDeviceResDTO.setUpdateTime(collectionDevice.getUpdateTime().getTime());
        }
        return collectionDeviceResDTO;
    }

    /**
     * doList转DTOList，并设置时间戳类型字段
     * @param collectionDevice 监控点集合
     * @return List<CollectionDeviceResDTO>
     */
    @Override
    public List<CollectionDeviceResDTO> doToDtoList(List<CollectionDevice> collectionDevice) {
        return  collectionDevice.stream().map(model -> {
            CollectionDeviceResDTO collectionDeviceResDTO =  BeanCopyUtil.convert(model, CollectionDeviceResDTO.class);
            return setTime(collectionDeviceResDTO, model);
        }).collect(Collectors.toList());
    }
}
