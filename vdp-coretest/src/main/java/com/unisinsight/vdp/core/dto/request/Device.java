/*
 * www.unisinsight.com Inc.
 * Copyright (c) 2018 All Rights Reserved
 */
package com.unisinsight.vdp.core.dto.request;

import java.io.Serializable;

/**
 * VMS设备
 * @author daisike [dai.sike@unisinsight.com]
 * @date 2018/09/18 21:05
 * @since 1.0
 */
public class Device implements Serializable {

    private static final long serialVersionUID = -6656706476612303811L;

    /**
     * vms设备唯一标识
     */
    private String deviceId;

    /**
     * 设备实际名称
     */
    private String deviceName;

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    @Override
    public String toString() {
        return "Device{" +
                "deviceId='" + deviceId + '\'' +
                ", deviceName='" + deviceName + '\'' +
                '}';
    }
}
