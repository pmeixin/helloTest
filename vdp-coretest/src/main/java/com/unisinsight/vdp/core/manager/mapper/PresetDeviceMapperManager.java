/*
 * www.unisinsight.com Inc.
 * Copyright (c) 2018 All Rights Reserved
 */
package com.unisinsight.vdp.core.manager.mapper;
import com.unisinsight.vdp.core.common.constant.CruisePathConstant;
import com.unisinsight.vdp.core.mapper.PresetDeviceMapper;
import com.unisinsight.vdp.core.model.CruisePreset;
import com.unisinsight.vdp.core.model.PresetDevice;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.entity.Condition;
import javax.annotation.Resource;
import java.util.List;

/**
 * 预设点校验MapperManager
 *
 * @author hujun [KF.hujunA@h3c.com]
 * @date 2018/10/11 11:27
 * @since 1.0
 */
@Component
public class PresetDeviceMapperManager extends AbstractMapperManager<PresetDevice, Integer> {
    /**
     * 注入presetDeviceMapper
     */
    @Resource
    private PresetDeviceMapper presetDeviceMapper;
    /**
     * 查询数据库判断预设点是否重名
     * @param name 预置点名称
     * @return true 预设点重名，false 预设点不重名
     */
    public boolean validPresetName(String name) {
        return presetDeviceMapper.findByName(name) != null;
    }

    /**
     * 查询数据库中预置点是否存在
     * @param id 预置点id
     * @return true 预设点存在，false 预设点不存在
     */
    public boolean validPresetId(Integer id) {
        return presetDeviceMapper.selectByPrimaryKey(id) != null;
    }

    /**
     * 查询数据库中设备id是否存在
     * @param id 设备id
     * @return true 预设点存在，false 预设点不存在
     */
    public boolean validId(String id) {
        Condition condition = new Condition(CruisePreset.class);
        condition.createCriteria().andCondition("id = '" + id + "'");
        return !presetDeviceMapper.selectByCondition(condition).isEmpty();
    }

    /**
     * 验证关键点是否少于2
     *
     * @return true 关键点不少于两个，false 关键点少于两个
     */
    public boolean validCruisePresetLessTwo() {
        List<PresetDevice> presetDeviceList = presetDeviceMapper.selectAll();
        return presetDeviceList.size() > CruisePathConstant.LESS_CRUISE_PRESET;
    }
}
