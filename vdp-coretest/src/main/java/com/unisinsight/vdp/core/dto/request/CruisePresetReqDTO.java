/*
 * www.unisinsight.com Inc.
 * Copyright (c) 2018 All Rights Reserved
 */
package com.unisinsight.vdp.core.dto.request;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 关键点ReqDTO
 *
 * @author hujun [KF.hujunA@h3c.com]
 * @date 2018/10/11 16:18
 * @since 1.0
 */
public class CruisePresetReqDTO implements Serializable {

    private static final long serialVersionUID = -6467003420671921657L;
    /**
     * 关键点id
     */
    @Range(min = 1, max = Integer.MAX_VALUE,  message = "范围必须在1-2147483647之间")
    private Integer cruisePresetId;

    /**
     * 预设点id
     */
    @Range(min = 1, max = Integer.MAX_VALUE,  message = "范围必须在1-2147483647之间")
    private Integer presetDeviceId;

    /**
     * 设备名称
     */
    @Length(min = 1, max = 50, message = "长度必须在1-50之间")
    private String name;

    /**
     * 巡航路径id
     */
    @Range(min = 1, max = Integer.MAX_VALUE,  message = "范围必须在1-2147483647之间")
    private Integer cruisePathId;

    /**
     * 巡航速度
     */
    @Range(min = 1, max = Short.MAX_VALUE,  message = "范围必须在1~32767之间")
    @NotNull(message = "巡航速度不能为空")
    private Short cruiseSpeed;

    /**
     * 巡航时间
     */
    @Range(min = 1, max = Short.MAX_VALUE,  message = "范围必须在1~32767之间")
    @NotNull(message = "巡航时间不能为空")
    private Short cruiseTime;

    public Integer getCruisePresetId() {
        return cruisePresetId;
    }

    public void setCruisePresetId(Integer cruisePresetId) {
        this.cruisePresetId = cruisePresetId;
    }

    public Integer getPresetDeviceId() {
        return presetDeviceId;
    }

    public void setPresetDeviceId(Integer presetDeviceId) {
        this.presetDeviceId = presetDeviceId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        return "CruisePresetReqDTO{" + "cruisePresetId=" + cruisePresetId + ", presetDeviceId=" + presetDeviceId
                + ", name='" + name + '\'' + ", cruisePathId=" + cruisePathId + ", cruiseSpeed=" + cruiseSpeed
                + ", cruiseTime=" + cruiseTime + '}';
    }
}
