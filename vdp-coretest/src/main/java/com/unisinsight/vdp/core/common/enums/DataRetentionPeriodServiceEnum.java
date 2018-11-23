/*
 * www.unisinsight.com Inc.
 * Copyright (c) 2018 All Rights Reserved
 */
package com.unisinsight.vdp.core.common.enums;

/**
 * 数据存留期服务eunm
 *
 * @author liujiaji [liu.jiaji@unisinsight.com]
 * @date 2018/11/03 10:31
 * @since 1.0
 */
public enum  DataRetentionPeriodServiceEnum {

    VEHICLE("Vehicle","抓拍车辆"),FACESNAP("FaceSnap","抓拍人脸"),PERSON("Person","抓拍人体"),VEHICLE_NOTIFICATION("VehicleNotification","车辆告警"),FACE_NOTIFICATION("FaceNotification","人脸告警");

    private String name;

    private String chinese;

    DataRetentionPeriodServiceEnum(String name, String chinese) {
        this.name = name;
        this.chinese = chinese;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getChinese() {
        return chinese;
    }

    public void setChinese(String chinese) {
        this.chinese = chinese;
    }
}