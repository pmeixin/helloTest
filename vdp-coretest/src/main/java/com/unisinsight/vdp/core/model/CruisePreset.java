package com.unisinsight.vdp.core.model;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

/**
* 关键点实体
* @author unisinsight  [KF.hujunA@h3c.com]
* @date   2018/10/13 15:26
* @since  1.0
*/
@Table(name = "cruise_preset")
public class CruisePreset {
    /**
     * 主键id
     */
    @Id
    @Column(name = "cruise_preset_id")
    private Integer cruisePresetId;

    /**
     * 预设点id
     */
    @Column(name = "preset_device_id")
    private Integer presetDeviceId;

    /**
     * 设备名称
     */
    private String name;

    /**
     * 巡航路径id
     */
    @Column(name = "cruise_path_id")
    private Integer cruisePathId;

    /**
     * 巡航速度
     */
    @Column(name = "cruise_speed")
    private Short cruiseSpeed;

    /**
     * 巡航时间
     */
    @Column(name = "cruise_time")
    private Short cruiseTime;

    /**
     * 获取主键id
     *
     * @return cruise_preset_id - 主键id
     */
    public Integer getCruisePresetId() {
        return cruisePresetId;
    }

    /**
     * 设置主键id
     *
     * @param cruisePresetId 主键id
     */
    public void setCruisePresetId(Integer cruisePresetId) {
        this.cruisePresetId = cruisePresetId;
    }

    /**
     * 获取预设点id
     *
     * @return preset_device_id - 预设点id
     */
    public Integer getPresetDeviceId() {
        return presetDeviceId;
    }

    /**
     * 设置预设点id
     *
     * @param presetDeviceId 预设点id
     */
    public void setPresetDeviceId(Integer presetDeviceId) {
        this.presetDeviceId = presetDeviceId;
    }

    /**
     * 获取设备名称
     *
     * @return name - 设备名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置设备名称
     *
     * @param name 设备名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取巡航路径id
     *
     * @return cruise_path_id - 巡航路径id
     */
    public Integer getCruisePathId() {
        return cruisePathId;
    }

    /**
     * 设置巡航路径id
     *
     * @param cruisePathId 巡航路径id
     */
    public void setCruisePathId(Integer cruisePathId) {
        this.cruisePathId = cruisePathId;
    }

    /**
     * 获取巡航速度
     *
     * @return cruise_speed - 巡航速度
     */
    public Short getCruiseSpeed() {
        return cruiseSpeed;
    }

    /**
     * 设置巡航速度
     *
     * @param cruiseSpeed 巡航速度
     */
    public void setCruiseSpeed(Short cruiseSpeed) {
        this.cruiseSpeed = cruiseSpeed;
    }

    /**
     * 获取巡航时间
     *
     * @return cruise_time - 巡航时间
     */
    public Short getCruiseTime() {
        return cruiseTime;
    }

    /**
     * 设置巡航时间
     *
     * @param cruiseTime 巡航时间
     */
    public void setCruiseTime(Short cruiseTime) {
        this.cruiseTime = cruiseTime;
    }
}