/*
 * www.unisinsight.com Inc.
 * Copyright (c) 2018 All Rights Reserved
 */
package com.unisinsight.vdp.core.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.unisinsight.framework.common.utils.BeanCopyUtil;
import com.unisinsight.vdp.common.vms.response.bean.ResourceTreeNode;
import com.unisinsight.vdp.core.dto.request.CollectionDeviceReqDTO;
import com.unisinsight.vdp.core.dto.request.CollectionGroupReqDTO;
import com.unisinsight.vdp.core.dto.response.CollectionDeviceResDTO;
import com.unisinsight.vdp.core.dto.response.CollectionGroupResDTO;
import com.unisinsight.vdp.core.manager.mapper.CollectionDeviceMapperManager;
import com.unisinsight.vdp.core.manager.mapper.CollectionGroupMapperManager;
import com.unisinsight.vdp.core.mapper.CollectionDeviceMapper;
import com.unisinsight.vdp.core.mapper.CollectionGroupMapper;
import com.unisinsight.vdp.core.model.CollectionDevice;
import com.unisinsight.vdp.core.model.CollectionGroup;
import com.unisinsight.vdp.core.service.CollectionGroupService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Condition;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 视频收藏组ServiceImpl
 *
 * @author wangshuai [KF.wangshuaiB@h3c.com]
 * @date 2018/09/18
 * @since 1.0
 */
@Service
public class CollectionGroupServiceImpl  implements CollectionGroupService {
    /**
     * 注入collectionGroupMapper
     */
    @Resource
    private CollectionGroupMapper collectionGroupMapper;
    /**
     * 注入collectionDeviceMapper
     */
    @Resource
    private CollectionDeviceMapper collectionDeviceMapper;
    /**
     * 注入collectionDeviceMapperManager
     */
    @Resource
    private CollectionDeviceMapperManager collectionDeviceMapperManager;
    /**
     * 注入collectionGroupMapperManager
     */
    @Resource
    private CollectionGroupMapperManager collectionGroupMapperManager;

    /**
     * 新增资源组
     * @param reqDTO CollectionGroupReqDTO
     * @return CollectionGroupResDTO
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public CollectionGroupResDTO save(CollectionGroupReqDTO reqDTO) {

        CollectionGroup collectionGroup = new CollectionGroup();
        collectionGroup.setCreateTime(new Date());
        BeanUtils.copyProperties(reqDTO, collectionGroup);

        collectionGroupMapper.insertSelective(collectionGroup);

        CollectionGroupResDTO resDTO = new CollectionGroupResDTO();
        BeanUtils.copyProperties(collectionGroup, resDTO);
        setTime(resDTO, collectionGroup);

        if (reqDTO.getChildren() != null) {
            List<CollectionDeviceReqDTO> collectionDeviceReqDTOList = reqDTO.getChildren();
            List<CollectionDevice> collectionDeviceList = BeanCopyUtil.convertList(collectionDeviceReqDTOList, CollectionDevice.class);
            collectionDeviceMapper.batchInsert(collectionDeviceList);

            List<CollectionDeviceResDTO> collectionDeviceResDTOList = BeanCopyUtil.convertList(collectionDeviceList, CollectionDeviceResDTO.class);

            resDTO.setChildren(collectionDeviceResDTOList);
        }

        return resDTO;

    }

    /**
     * 依据主键删除资源组
     * @param id 主键
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteById(Integer id) {

        collectionGroupMapper.deleteByPrimaryKey(id);

        Condition condition = new Condition(CollectionDevice.class);
        condition.createCriteria().andCondition("group_id = '" + id + "'");
        collectionDeviceMapper.deleteByCondition(condition);

    }

    /**
     * 修改资源组
     * @param updateDTO 前端传入DTO
     * @return CollectionGroupResDTO
     */
    @Override
    public CollectionGroupResDTO update(CollectionGroupReqDTO updateDTO) {

        CollectionGroup collectionGroup = new CollectionGroup();
        collectionGroup.setUpdateTime(new Date());
        BeanUtils.copyProperties(updateDTO, collectionGroup);

        collectionGroupMapper.updateByPrimaryKey(collectionGroup);

        CollectionGroupResDTO resDTO = new CollectionGroupResDTO();
        BeanUtils.copyProperties(collectionGroup, resDTO);
        setTime(resDTO, collectionGroup);

        Condition condition = new Condition(CollectionDevice.class);
        condition.createCriteria().andCondition("group_id = '" + updateDTO.getGroupId() + "'");
        List<CollectionDevice> collectionDeviceList = collectionDeviceMapper.selectByCondition(condition);
        List<CollectionDeviceResDTO> collectionDeviceResDTOList = BeanCopyUtil.convertList(collectionDeviceList, CollectionDeviceResDTO.class);
        resDTO.setChildren(collectionDeviceResDTOList);


        return resDTO;

    }

    /**
     * 依据id查询资源组
     * @param id 主键
     * @return CollectionGroupResDTO
     */
    @Override
    public CollectionGroupResDTO findById(Integer id) {

        CollectionGroup collectionGroup = collectionGroupMapper.selectByPrimaryKey(id);
        CollectionGroupResDTO resDTO = new CollectionGroupResDTO();
        BeanUtils.copyProperties(collectionGroup, resDTO);
        setTime(resDTO, collectionGroup);

        Condition condition = new Condition(CollectionDevice.class);
        condition.createCriteria().andCondition("group_id = '" + id + "'");
        List<CollectionDevice> collectionDeviceList = collectionDeviceMapper.selectByCondition(condition);
        List<CollectionDeviceResDTO> collectionDeviceResDTOList = BeanCopyUtil.convertList(collectionDeviceList, CollectionDeviceResDTO.class);

        resDTO.setChildren(collectionDeviceResDTOList);
        return resDTO;

    }

    /**
     * 资源组模糊搜索
     * @param name 关键字
     * @return List<CollectionGroupResDTO>
     */
    @Override
    public  List<CollectionGroupResDTO> searchKey(String name) {
        List<CollectionGroup> collectionGroupList = collectionGroupMapper.searchKey(name);
        //对应显示资源组下面的监控点，收藏资源按group_id装入对应的Map中
        //获取所有有group_id的资源
        Condition condition = new Condition(CollectionGroup.class);
        condition.createCriteria().andCondition("group_id is not null");
        List<CollectionDevice> collectionDeviceList = collectionDeviceMapper.selectByCondition(condition);
        //收藏资源按group_id装入对应的Map中
        Map<String, List<CollectionDeviceResDTO>> deviceListMap = collectionDeviceMapperManager.listToMap(collectionDeviceList);
        //遍历收藏组，将视频资源装载进dto中
        List<CollectionGroupResDTO> collectionGroupResDTOList = BeanCopyUtil.convertList(collectionGroupList, CollectionGroupResDTO.class);
        collectionGroupResDTOList.forEach(c -> c.setChildren(deviceListMap.get(c.getGroupId().toString())));
        return BeanCopyUtil.convertList(collectionGroupList, CollectionGroupResDTO.class);
    }

    /**
     * 获取资源组及所属监控点资源
     * @return List<CollectionGroupResDTO>
     */
    @Override
    public List<CollectionGroupResDTO> listAll() {
      //获取所有的资源组
      List<CollectionGroup> collectionGroupList = collectionGroupMapper.selectAll();
      //获取所有有group_id的资源
      Condition condition = new Condition(CollectionGroup.class);
      condition.createCriteria().andCondition("group_id is not null");
      List<CollectionDevice> collectionDeviceList = collectionDeviceMapper.selectByCondition(condition);
        //从资源树获取摄像机在线转态
        List<ResourceTreeNode> resourceTreeNodes = collectionDeviceMapperManager.getResourceTree();
        resourceTreeNodes.forEach(e -> {
            String id = e.getId();
            int status = e.getOnlineStatus();
            collectionDeviceList.forEach(d -> {
                if (d.getId().equals(id)) {
                    d.setOnlineStatus(status);
                }
            });
        });
      //收藏资源按group_id装入对应的Map中
      Map<String, List<CollectionDeviceResDTO>> deviceListMap = collectionDeviceMapperManager.listToMap(collectionDeviceList);
      //遍历收藏组，将视频资源装载进dto中
      List<CollectionGroupResDTO> collectionGroupResDTOList = BeanCopyUtil.convertList(collectionGroupList, CollectionGroupResDTO.class);
      collectionGroupResDTOList.forEach(c -> {
          c.setChildren(deviceListMap.get(c.getGroupId().toString()));
          if (c.getChildren() == null) {
              c.setChildren(new ArrayList<>());
          }
      });
      return collectionGroupResDTOList;
    }

    /**
     * createTime和updateTime设置时间戳
     * @param collectionGroupResDTO:返回前端DTO
     * @param collectionGroup:从数据库获取的Model
     * @return CollectionGroupResDTO
     */
    @Override
    public CollectionGroupResDTO setTime(CollectionGroupResDTO collectionGroupResDTO, CollectionGroup collectionGroup) {
        if (null != collectionGroup.getCreateTime()) {
            collectionGroupResDTO.setCreateTime(collectionGroup.getCreateTime().getTime());
        }
        if (null != collectionGroup.getUpdateTime()) {
            collectionGroupResDTO.setUpdateTime(collectionGroup.getUpdateTime().getTime());
        }
        return collectionGroupResDTO;
    }
    public List<CollectionGroupResDTO> selectbp(int currentpage,int pagesize){
        PageHelper.startPage(currentpage, pagesize);
        List<CollectionGroup> collectionGroupList = collectionGroupMapper.selectAll();
        List<CollectionGroupResDTO> collectionGroupResDTOList = BeanCopyUtil.convertList(collectionGroupList, CollectionGroupResDTO.class);
        PageInfo<CollectionGroupResDTO> data = new PageInfo<CollectionGroupResDTO>(collectionGroupResDTOList);
        return data.getList();
    }

    public List<CollectionGroupResDTO> selectbpp(int currentpage,int pagesize){
        List<CollectionGroup> collectionGroupList = collectionGroupMapper.selectAll();
        List<CollectionGroupResDTO> collectionGroupResDTOList = BeanCopyUtil.convertList(collectionGroupList, CollectionGroupResDTO.class);
        int first = (currentpage - 1) * pagesize;
        int last = currentpage * pagesize;
        return collectionGroupResDTOList.subList(first, last);

    }
}

