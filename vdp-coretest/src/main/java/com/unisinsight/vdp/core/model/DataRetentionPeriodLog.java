package com.unisinsight.vdp.core.model;

import java.util.Date;
import javax.persistence.*;

/**
 * 数据留存期时间修改日志
 * @author liujiaji [HZ.liujiaji@h3c.com]
 * @date 2018/10/24 17:18
 * @since 1.0
 */
@Table(name = "data_retention_period_log")
public class DataRetentionPeriodLog {

    /**
     * 日志id
     */
    @Id
    private Short id;

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
    @Column(name = "face_snap")
    private Integer faceSnap;

    /**
     * 抓拍人体留存期时间
     */
    private Integer person;

    /**
     * 车辆告警留存期时间
     */
    @Column(name = "vehicle_notification")
    private Integer vehicleNotification;

    /**
     * 人脸告警留存期时间
     */
    @Column(name = "face_notification")
    private Integer faceNotification;

    /**
     * 更新的用户id
     */
    @Column(name = "update_user_id")
    private Integer updateUserId;

    /**
     * 更新的用户姓名
     */
    @Column(name = "update_user_name")
    private String updateUserName;

    /**
     * 更新时间
     */
    @Column(name = "update_time")
    private Date updateTime;

    /**
     * @return id
     */
    public Short getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Short id) {
        this.id = id;
    }

    /**
     * @return type
     */
    public Integer getType() {
        return type;
    }

    /**
     * @param type
     */
    public void setType(Integer type) {
        this.type = type;
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

    public Integer getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(Integer updateUserId) {
        this.updateUserId = updateUserId;
    }

    public String getUpdateUserName() {
        return updateUserName;
    }

    public void setUpdateUserName(String updateUserName) {
        this.updateUserName = updateUserName;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "DataRetentionPeriodLog{" +
                "id=" + id +
                ", type=" + type +
                ", vehicle=" + vehicle +
                ", faceSnap=" + faceSnap +
                ", person=" + person +
                ", vehicleNotification=" + vehicleNotification +
                ", faceNotification=" + faceNotification +
                ", updateUserId=" + updateUserId +
                ", updateUserName='" + updateUserName + '\'' +
                ", updateTime=" + updateTime +
                '}';
    }
}