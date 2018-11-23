/*
 * www.unisinsight.com Inc.
 * Copyright (c) 2018 All Rights Reserved
 */
package com.unisinsight.vdp.core.dto.response;
import java.io.Serializable;

/**
 * 关键点ResDTO
 *
 * @author hujun [KF.hujunA@h3c.com]
 * @date 2018/10/11 16:27
 * @since 1.0
 */
public class CruisePresetResDTO implements Serializable {
    private static final long serialVersionUID = 6897479910661892072L;

    /**
     * 预设点id
     */
    private Integer presetDeviceId;

    /**
     * 巡航路径id
     */
    private Integer cruisePathId;

    /**
     * 巡航速度
     */
    private Short cruiseSpeed;

    /**
     * 巡航时间
     */
    private Short cruiseTime;

    public Integer getPresetDeviceId() {
        return presetDeviceId;
    }

    public void setPresetDeviceId(Integer presetDeviceId) {
        this.presetDeviceId = presetDeviceId;
    }

    public Integer getCruisePathId() {
        return cruisePathId;
    }

    public void setCruisePathId(Integer cruisePathId) {
        this.cruisePathId = cruisePathId;
    }

    public Short getCruiseSpeed() {
        return cruiseSpeed;
    }

    public void setCruiseSpeed(Short cruiseSpeed) {
        this.cruiseSpeed = cruiseSpeed;
    }

    public Short getCruiseTime() {
        return cruiseTime;
    }

    public void setCruiseTime(Short cruiseTime) {
        this.cruiseTime = cruiseTime;
    }

    @Override
    public String toString() {
        return "CruisePresetResDTO{" + "presetDeviceId=" + presetDeviceId + ", cruisePathId=" + cruisePathId
                + ", cruiseSpeed=" + cruiseSpeed + ", cruiseTime=" + cruiseTime + '}';
    }
}
