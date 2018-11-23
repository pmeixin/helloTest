/*
 * www.unisinsight.com Inc.
 * Copyright (c) 2018 All Rights Reserved
 */
package com.unisinsight.vdp.core.service.impl;
import com.unisinsight.framework.common.exception.BaseErrorCode;
import com.unisinsight.framework.common.exception.BaseException;
import com.unisinsight.framework.common.utils.BeanCopyUtil;
import com.unisinsight.vdp.core.common.enums.VideoErrorCode;
import com.unisinsight.vdp.core.dto.request.CruisePathReqDTO;
import com.unisinsight.vdp.core.dto.request.CruisePresetReqDTO;
import com.unisinsight.vdp.core.dto.response.CruisePathResDTO;
import com.unisinsight.vdp.core.dto.response.CruisePresetResDTO;
import com.unisinsight.vdp.core.manager.mapper.CruisePathMapperManager;
import com.unisinsight.vdp.core.manager.mapper.CruisePresetMapperManager;
import com.unisinsight.vdp.core.manager.mapper.PresetDeviceMapperManager;
import com.unisinsight.vdp.core.mapper.CruisePathMapper;
import com.unisinsight.vdp.core.mapper.CruisePresetMapper;
import com.unisinsight.vdp.core.mapper.PresetDeviceMapper;
import com.unisinsight.vdp.core.model.CruisePath;
import com.unisinsight.vdp.core.model.CruisePreset;
import com.unisinsight.vdp.core.model.PresetDevice;
import com.unisinsight.vdp.core.service.CruisePathService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Condition;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 巡航路径实现类
 *
 * @author hujun [KF.hujunA@h3c.com]
 * @date 2018/10/11 10:34
 * @since 1.0
 */
@Service
public class CruisePathServiceImpl implements CruisePathService {
    /**
     * 注入cruisePathMapper
     */
    @Resource
    private CruisePathMapper cruisePathMapper;
    /**
     * 注入cruisePathMapperManager
     */
    @Resource
    private CruisePathMapperManager cruisePathMapperManager;
    /**
     * 注入cruisePresetMapperManager
     */
    @Resource
    private CruisePresetMapperManager cruisePresetMapperManager;
    /**
     * 注入cruisePresetMapper
     */
    @Resource
    private CruisePresetMapper cruisePresetMapper;
    /**
     * 注入presetDeviceMapper
     */
    @Resource
    private PresetDeviceMapper presetDeviceMapper;
    /**
     * 注入presetDeviceMapperManager
     */
    @Resource
    private PresetDeviceMapperManager presetDeviceMapperManager;

    /**
     * 新建巡航路径
     * @param reqDTO {@link CruisePathReqDTO}
     * @return {@link CruisePathResDTO}
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public CruisePathResDTO save(CruisePathReqDTO reqDTO) {
        //预置点id和巡航路径id必须要一样
        List<Integer> presetDeviceIds = new ArrayList<>();
        reqDTO.getChildren().forEach(c -> presetDeviceIds.add(c.getPresetDeviceId()));
        List<PresetDevice> presetDevices = presetDeviceMapper.findByIds(presetDeviceIds);
        presetDevices.forEach(c -> {
            if (!c.getId().equals(reqDTO.getId())) {
                throw BaseException.of(VideoErrorCode.ID_IS_DIFFERENT.of());
            }
        });
        //验证传入的预设点是否存在,反复查数据库效率太低，判断数据库对应的条数相等即可
        if (presetDeviceIds.size() != presetDevices.size()) {
            throw BaseException.of(VideoErrorCode.PRESETDEVICE_NOT_EXIST.of());
        }
        //检验是否重名
        if (cruisePathMapperManager.validCruisePathName(reqDTO.getName())) {
            throw BaseException.of(VideoErrorCode.CRUISEPATH_NAME_HAS_EXIST.of());
        }
        //检验关键点是否少于2个，默认需要至少两个关键点
        if (!presetDeviceMapperManager.validCruisePresetLessTwo()) {
            throw BaseException.of(VideoErrorCode.PRESETDEVICE_MORE_TWO.of());
        }
        //将预置点放入到关键点中
        CruisePreset cruisePreset = new CruisePreset();
        //将PresetDevice装载在Map中，初始化一个map
        Map<String, PresetDevice> deviceMap = new HashMap<>();
        //获取所有有preset_device_id的资源
        Condition condition = new Condition(PresetDevice.class);
        condition.createCriteria().andCondition("preset_device_id is not null");
        List<PresetDevice> presetDeviceList = presetDeviceMapper.selectByCondition(condition);
            reqDTO.getChildren().forEach(e -> {
                int deviceId = e.getPresetDeviceId();
                presetDeviceList.forEach(d -> {
                if (deviceId == d.getPresetDeviceId()) {
                    cruisePreset.setPresetDeviceId(d.getPresetDeviceId());
                    cruisePreset.setName(d.getName());
                    deviceMap.put(d.getPresetDeviceId().toString(), d);
                }
            });
        });
        //获得前端传入的子节点
        List<CruisePresetReqDTO> cruisePresetList = reqDTO.getChildren();
        List<CruisePreset> cruisePresets = BeanCopyUtil.convertList(cruisePresetList, CruisePreset.class);
        //将关键点与巡航路径对应起来
        Map<String, List<CruisePreset>> cruisePresetMap = new HashMap<>(1);
        //数据库插入拿到CruisePathId
        CruisePath cruisePath = new CruisePath();
        BeanUtils.copyProperties(reqDTO, cruisePath);
        cruisePath.setCreateTime(new Date());
        cruisePathMapper.insertSelective(cruisePath);
        //依据CruisePathId对应匹配
        cruisePresets.forEach(c -> c.setCruisePathId(cruisePath.getCruisePathId()));
        cruisePresetMap.put(cruisePath.getCruisePathId().toString(), cruisePresets);
        cruisePresetMapper.insertList(cruisePresets);
        CruisePathResDTO cruisePathResDTO = setTime(BeanCopyUtil.convert(cruisePath, CruisePathResDTO.class), cruisePath);
        //设置children的值,需要将 cruisePreset转为 cruisePresetResDTO
        List<CruisePresetResDTO> cruisePresetResDTOList = BeanCopyUtil.convertList(cruisePresets, CruisePresetResDTO.class);
        cruisePathResDTO.setChildren(cruisePresetResDTOList);
        return cruisePathResDTO;
    }

    /**
     * 巡航路径更新
     * @param updateDTO {@link CruisePathReqDTO}
     * @return CruisePathResDTO
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public CruisePathResDTO update(CruisePathReqDTO updateDTO) {
        //判断巡航路径是否存在
        if (!cruisePathMapperManager.validCruisePathId(updateDTO.getCruisePathId())) {
            throw BaseException.of(VideoErrorCode.CRUISEPATH_NOT_EXIST.of());
        }
        //预置点id和巡航路径id必须要一样
        List<Integer> presetDeviceIds = new ArrayList<>();
        updateDTO.getChildren().forEach(c -> presetDeviceIds.add(c.getPresetDeviceId()));
        List<PresetDevice> presetDevices = presetDeviceMapper.findByIds(presetDeviceIds);
        presetDevices.forEach(c -> {
            if (!c.getId().equals(updateDTO.getId())) {
                throw BaseException.of(VideoErrorCode.ID_IS_DIFFERENT.of());
            }
        });
        //验证传入的预设点是否存在,反复查数据库效率太低，判断数据库对应的条数相等即可
        List<Integer> presetDeviceIdListDTO = new ArrayList<>();
        updateDTO.getChildren().forEach(c -> presetDeviceIdListDTO.add(c.getPresetDeviceId()));
        if (presetDeviceIdListDTO.size() != presetDevices.size()) {
            throw BaseException.of(VideoErrorCode.PRESETDEVICE_NOT_EXIST.of());
        }
        //验证修改的关键点是否存在,反复查数据库效率太低，判断数据库对应的条数相等即可
        List<Integer> cruisepresetListDTO = new ArrayList<>();
        updateDTO.getChildren().forEach(c -> cruisepresetListDTO.add(c.getCruisePresetId()));
        List<CruisePreset> cruisePresetDO = cruisePresetMapper.findByIds(cruisepresetListDTO);
        if (cruisePresetDO.size() != cruisepresetListDTO.size()) {
            throw BaseException.of(VideoErrorCode.CRUISEPRESET_NOT_EXIST.of());
        }
        //检验关键点是否少于2个，默认需要至少两个关键点
        if (!presetDeviceMapperManager.validCruisePresetLessTwo()) {
            throw BaseException.of(VideoErrorCode.PRESETDEVICE_MORE_TWO.of());
        }
        //检验是否重名
        if (cruisePathMapperManager.validCruisePathNameLessTwo(updateDTO.getName())) {
            throw BaseException.of(VideoErrorCode.CRUISEPATH_NAME_HAS_EXIST.of());
        }
        CruisePath cruisePath = new CruisePath();
        BeanUtils.copyProperties(updateDTO, cruisePath);
        cruisePath.setUpdateTime(new Date());
        //更新数据
        cruisePathMapper.updateByPrimaryKey(cruisePath);
        //同时关键点有需要更改
        //获得前端传入的子节点
        List<CruisePresetReqDTO> cruisePresetReqDTOList = updateDTO.getChildren();
        List<CruisePreset> cruisePresets = BeanCopyUtil.convertList(cruisePresetReqDTOList, CruisePreset.class);
        List<Integer> cruisePresetIds = new ArrayList<>();
        updateDTO.getChildren().forEach(c -> cruisePresetIds.add(c.getCruisePresetId()));
        cruisePresetMapper.deleteBatch(cruisePresetIds);
        //给关键点传入巡航路径id
        cruisePresets.forEach(c -> c.setCruisePathId(updateDTO.getCruisePathId()));
        cruisePresetMapper.insertList(cruisePresets);
        CruisePathResDTO cruisePathResDTO = setTime(BeanCopyUtil.convert(cruisePath, CruisePathResDTO.class), cruisePath);
        //设置children的值
        //需要将 cruisePreset转为 cruisePresetResDTO
        List<CruisePresetResDTO> cruisePresetResDTOList = BeanCopyUtil.convertList(cruisePresets, CruisePresetResDTO.class);
        cruisePathResDTO.setChildren(cruisePresetResDTOList);
        return cruisePathResDTO;
    }
    /**
     * 依据主键id删除巡航路径
     * @param id 主键
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteById(Integer id) {
        //删除巡航路径的同时先出下面的关键点
        Condition condition = new Condition(CruisePreset.class);
        condition.createCriteria().andCondition("cruise_path_id = '" + id + "'");
        cruisePresetMapper.deleteByCondition(condition);
        cruisePathMapperManager.deleteById(id);
    }

    /**
     * 依据设备id查找巡航路径
     * @param id 设备id
     * @return  List<CruisePathResDTO>
     */
    @Override
    public List<CruisePathResDTO> findById(String id) {
        //传参校验:三种情况：1、空 2、空格 3、漏传 4、长度超范围
        if (0 == id.trim().length()) {
            throw BaseException.of(BaseErrorCode.INVALID_PARAM_ERROR.of(), "id不能为空");
        }
        //检验设备id是否存在
        if (!cruisePathMapperManager.validId(id)) {
            throw BaseException.of(VideoErrorCode.ID_NOT_EXIST.of());
        }
        List<CruisePath> cruisePathList =  cruisePathMapper.findById(id);
        //获取所有有group_id的资源
        Condition condition = new Condition(CruisePath.class);
        condition.createCriteria().andCondition("cruise_path_id is not null");
        List<CruisePreset> cruisePresetList = cruisePresetMapper.selectByCondition(condition);
        //收藏资源按group_id装入对应的Map中，返回的值为Map<String,List<CruisePresetResDTO>>
        Map<String, List<CruisePresetResDTO >> cruisePresetListMap = cruisePresetMapperManager.listToMap(cruisePresetList);
        //遍历收藏组，将视频资源装载进dto中
        List<CruisePathResDTO> cruisePathResDTOList = BeanCopyUtil.convertList(cruisePathList, CruisePathResDTO.class);
        cruisePathResDTOList.forEach(c -> c.setChildren(cruisePresetListMap.get(c.getCruisePathId().toString())));
        return cruisePathResDTOList;
    }
    /**
     * createTime和updateTime设置时间戳
     * @param cruisePathResDTO:返回前端DTO
     * @param cruisePath:从数据库获取的Model
     * @return CruisePathResDTO
     */
    @Override
    public CruisePathResDTO setTime(CruisePathResDTO cruisePathResDTO, CruisePath cruisePath) {
        if (null != cruisePath.getCreateTime()) {
            cruisePathResDTO.setCreateTime(cruisePath.getCreateTime().getTime());
        }
        if (null != cruisePath.getUpdateTime()) {
            cruisePathResDTO.setUpdateTime(cruisePath.getUpdateTime().getTime());
        }
        return cruisePathResDTO;
    }
    /**
     * 获取所有巡航路径
     * @return List<CruisePathResDTO>
     */
    @Override
    public List<CruisePathResDTO> listAll() {
        List<CruisePath> cruisePathList = cruisePathMapper.selectAll();
        //获取所有有group_id的资源
        Condition condition = new Condition(CruisePath.class);
        condition.createCriteria().andCondition("cruise_path_id is not null");
        List<CruisePreset> cruisePresetList = cruisePresetMapper.selectByCondition(condition);
        //收藏资源按group_id装入对应的Map中，返回的值为Map<String,List<CruisePresetResDTO>>
        Map<String, List<CruisePresetResDTO >> cruisePresetListMap = cruisePresetMapperManager.listToMap(cruisePresetList);
        //遍历收藏组，将视频资源装载进dto中
        List<CruisePathResDTO> cruisePathResDTOList = BeanCopyUtil.convertList(cruisePathList, CruisePathResDTO.class);
        cruisePathResDTOList.forEach(c -> c.setChildren(cruisePresetListMap.get(c.getCruisePathId().toString())));
        return cruisePathResDTOList;
    }
}
