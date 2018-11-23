/*
 * www.unisinsight.com Inc.
 * Copyright (c) 2018 All Rights Reserved
 */
package com.unisinsight.vdp.core.service.impl;
import com.unisinsight.framework.common.exception.BaseException;
import com.unisinsight.framework.common.utils.BeanCopyUtil;
import com.unisinsight.vdp.core.common.enums.VideoErrorCode;
import com.unisinsight.vdp.core.dto.request.PresetDeviceReqDTO;
import com.unisinsight.vdp.core.dto.response.PresetDeviceResDTO;
import com.unisinsight.vdp.core.manager.mapper.PresetDeviceMapperManager;
import com.unisinsight.vdp.core.mapper.CruisePresetMapper;
import com.unisinsight.vdp.core.mapper.PresetDeviceMapper;
import com.unisinsight.vdp.core.model.CruisePreset;
import com.unisinsight.vdp.core.model.PresetDevice;
import com.unisinsight.vdp.core.service.PresetDeviceService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Condition;
import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 预置点实现类
 *
 * @author hujun [KF.hujunA@h3c.com]
 * @date 2018/10/11 10:33
 * @since 1.0
 */
@Service
public class PresetDeviceServiceImpl implements PresetDeviceService {

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
     * 注入cruisePresetMapper
     */
    @Resource
    private CruisePresetMapper cruisePresetMapper;
    /**
     * 新增预设点
     * @param reqDTO {@link PresetDeviceReqDTO}
     * @return {@link PresetDeviceResDTO}
     */
    @Override
    public PresetDeviceResDTO save(PresetDeviceReqDTO reqDTO) {
        PresetDevice presetDevice = new PresetDevice();
        //检验是否重名
        if (presetDeviceMapperManager.validPresetName(reqDTO.getName())) {
            throw BaseException.of(VideoErrorCode.PRESET_NAME_HAS_EXIST.of());
        }
        BeanUtils.copyProperties(reqDTO, presetDevice);
        presetDevice.setCreateTime(new Date());
        presetDeviceMapper.insertSelective(presetDevice);
        return setTime(BeanCopyUtil.convert(presetDevice, PresetDeviceResDTO.class), presetDevice);
    }

    /**
     * 依据主键删除
     * @param id 主键
     */
    @Override
    public void deleteById(Integer id) {
        Condition condition = new Condition(CruisePreset.class);
        condition.createCriteria().andCondition("preset_device_id = '" + id + "'");
        if (!cruisePresetMapper.selectByCondition(condition).isEmpty()) {
            throw BaseException.of(VideoErrorCode.PRESETDEVICE_DELETE_EXCEPTION.of());
        }
        presetDeviceMapperManager.deleteById(id);
    }

    /**
     * 更新
     * @param updateDTO {@link PresetDeviceReqDTO}
     * @return PresetDeviceResDTO
     */
    @Override
    public PresetDeviceResDTO update(PresetDeviceReqDTO updateDTO) {
        //检验预设点是否存在
        if (!presetDeviceMapperManager.validPresetId(updateDTO.getPresetDeviceId())) {
            throw BaseException.of(VideoErrorCode.PRESET_NOT_EXIST.of());
        }
        //修改的名称不能同名
        Condition conditionName = new Condition(PresetDevice.class);
        conditionName.createCriteria().andCondition("name = '" + updateDTO.getName() + "'");
        if (!presetDeviceMapper.selectByCondition(conditionName).isEmpty()) {
            throw BaseException.of(VideoErrorCode.PRESET_NAME_HAS_EXIST.of());
        }
        PresetDevice presetDevice = new PresetDevice();
        presetDevice.setUpdateTime(new Date());
        BeanUtils.copyProperties(updateDTO, presetDevice);
        //更新数据
        presetDeviceMapper.updateByPrimaryKeySelective(presetDevice);
        //设置时间
       return setTime(BeanCopyUtil.convert(presetDevice, PresetDeviceResDTO.class), presetDevice);
    }

    /**
     * 依据设备id查询预置点
     * @param id 设备id
     * @return List<PresetDeviceResDTO>
     */
    @Override
    public List<PresetDeviceResDTO> findById(String id) {
        //检验设备id是否存在
        if (!presetDeviceMapperManager.validId(id)) {
            throw BaseException.of(VideoErrorCode.ID_NOT_EXIST.of());
        }
        List<PresetDevice> presetDeviceList = presetDeviceMapper.findById(id);
        return BeanCopyUtil.convertList(presetDeviceList, PresetDeviceResDTO.class);
    }
    /**
     * 获取所有预置点资源
     * @return List<PresetDeviceResDTO>
     */
    @Override
    public List<PresetDeviceResDTO> listAll() {
        List<PresetDevice> presetDeviceList = presetDeviceMapper.selectAll();
        return doToDtoList(presetDeviceList);
    }

    /**
     * createTime和updateTime设置时间戳
     * @param presetDeviceResDTO:返回前端DTO
     * @param presetDevice:从数据库获取的Model
     * @return PresetDeviceResDTO
     */
    @Override
    public PresetDeviceResDTO setTime(PresetDeviceResDTO presetDeviceResDTO, PresetDevice presetDevice) {
        if (null != presetDevice.getCreateTime()) {
            presetDeviceResDTO.setCreateTime(presetDevice.getCreateTime().getTime());
        }
        if (null != presetDevice.getUpdateTime()) {
            presetDeviceResDTO.setUpdateTime(presetDevice.getUpdateTime().getTime());
        }
        return presetDeviceResDTO;
    }

    /**
     * doList转DTOList，并设置时间戳类型字段
     * @param presetDevices 预设点集合
     * @return  List<PresetDeviceResDTO>
     */
    @Override
    public List<PresetDeviceResDTO> doToDtoList(List<PresetDevice> presetDevices) {
        return  presetDevices.stream().map(model -> {
            PresetDeviceResDTO presetDeviceResDTO =  BeanCopyUtil.convert(model, PresetDeviceResDTO.class);
            return setTime(presetDeviceResDTO, model);
        }).collect(Collectors.toList());
    }
}
