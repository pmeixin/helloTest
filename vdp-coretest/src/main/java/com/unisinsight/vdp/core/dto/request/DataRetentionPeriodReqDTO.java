/*
 * www.unisinsight.com Inc.
 * Copyright (c) 2018 All Rights Reserved
 */
package com.unisinsight.vdp.core.dto.request;

import java.io.Serializable;

/**
 * 数据留存期时间修改ReqDTO（时间统一单位：天）
 *
 * @author liujiaji [HZ.liujiaji@h3c.com]
 * @date 2018/10/24 15:58
 * @since 1.0
 */
public class DataRetentionPeriodReqDTO implements Serializable {

    private static final long serialVersionUID = 5842223158158508971L;

    /**
     * 数据类型
     */
    private Integer type;

    /**
     * 抓拍车辆留存期时间
     */
    private Integer vehicle;

    /**
     * 抓拍人脸留存期时间
     */
    private Integer faceSnap;

    /**
     * 抓拍人体留存期时间
     */
    private Integer person;

    /**
     * 车辆告警留存期时间
     */
    private Integer vehicleNotification;

    /**
     * 人脸告警留存期时间
     */
    private Integer faceNotification;


    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getVehicle() {
        return vehicle;
    }

    public void setVehicle(Integer vehicle) {
        this.vehicle = vehicle;
    }

    public Integer getFaceSnap() {
        return faceSnap;
    }

    public void setFaceSnap(Integer faceSnap) {
        this.faceSnap = faceSnap;
    }

    public Integer getPerson() {
        return person;
    }

    public void setPerson(Integer person) {
        this.person = person;
    }

    public Integer getVehicleNotification() {
        return vehicleNotification;
    }

    public void setVehicleNotification(Integer vehicleNotification) {
        this.vehicleNotification = vehicleNotification;
    }

    public Integer getFaceNotification() {
        return faceNotification;
    }

    public void setFaceNotification(Integer faceNotification) {
        this.faceNotification = faceNotification;
    }

    @Override
    public String toString() {
        return "DataRetentionPeriodReqDTO{" +
                "type=" + type +
                ", vehicle=" + vehicle +
                ", faceSnap=" + faceSnap +
                ", person=" + person +
                ", vehicleNotification=" + vehicleNotification +
                ", faceNotification=" + faceNotification +
                '}';
    }
}