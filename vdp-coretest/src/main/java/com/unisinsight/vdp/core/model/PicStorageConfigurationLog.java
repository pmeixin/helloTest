/*
 * www.unisinsight.com Inc.
 * Copyright (c) 2018 All Rights Reserved
 */
package com.unisinsight.vdp.core.model;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * 图片云存储地址修改日志
 *
 * @author liujiaji [HZ.liujiaji@h3c.com]
 * @date 2018/10/24 17:18
 * @since 1.0
 */
@Table(name = "pic_storage_configuration_log")
public class PicStorageConfigurationLog {

    /**
     * 日志 id
     */
    @Id
    private Short id;

    /**
     * 被修改的图片数据类型
     */
    @Column(name = "data_type")
    private Integer dataType;

    /**
     * 修改后的云端地址
     */
    @Column(name = "storage_ip")
    private String storageIp;

    /**
     * 修改后的端口
     */
    @Column(name = "storage_port")
    private Integer storagePort;

    /**
     * 修改后的容量
     */
    private Integer capacity;

    /**
     * 修改后的策略
     */
    private Integer strategy;

    /**
     * 剩余容量
     */
    private Integer remainCapacity;

    /**
     * 存储路径
     */
    @Column(name = "storage_path")
    private String storagePath;

    /**
     * 修改人id
     */
    @Column(name = "update_user_id")
    private Integer updateUserId;

    /**
     * 修改人姓名
     */
    @Column(name = "update_user_name")
    private String updateUserName;

    /**
     * 修改时间
     */
    @Column(name = "update_time")
    private Date updateTime;

    public Short getId() {
        return id;
    }

    public void setId(Short id) {
        this.id = id;
    }

    public Integer getDataType() {
        return dataType;
    }

    public void setDataType(Integer dataType) {
        this.dataType = dataType;
    }

    public String getStorageIp() {
        return storageIp;
    }

    public void setStorageIp(String storageIp) {
        this.storageIp = storageIp;
    }

    public Integer getStoragePort() {
        return storagePort;
    }

    public void setStoragePort(Integer storagePort) {
        this.storagePort = storagePort;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public Integer getStrategy() {
        return strategy;
    }

    public void setStrategy(Integer strategy) {
        this.strategy = strategy;
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

    public Integer getRemainCapacity() {
        return remainCapacity;
    }

    public void setRemainCapacity(Integer remainCapacity) {
        this.remainCapacity = remainCapacity;
    }

    public String getStoragePath() {
        return storagePath;
    }

    public void setStoragePath(String storagePath) {
        this.storagePath = storagePath;
    }

    @Override
    public String toString() {
        return "PicStorageConfigurationLog{" +
                "id=" + id +
                ", dataType=" + dataType +
                ", storageIp='" + storageIp + '\'' +
                ", storagePort=" + storagePort +
                ", capacity=" + capacity +
                ", strategy=" + strategy +
                ", remainCapacity=" + remainCapacity +
                ", storagePath='" + storagePath + '\'' +
                ", updateUserId=" + updateUserId +
                ", updateUserName='" + updateUserName + '\'' +
                ", updateTime=" + updateTime +
                '}';
    }
}