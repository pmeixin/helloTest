/*
 * www.unisinsight.com Inc.
 * Copyright (c) 2018 All Rights Reserved
 */
package com.unisinsight.vdp.core.dto.response.bean;

/**
 * description
 *
 * @author liujiaji [liu.jiaji@unisinsight.com]
 * @date 2018/11/06 15:31
 * @since 1.0
 */
public class PicStorageStrategy {

    /**
     * 图片数据类型
     */
    private Integer dataType;

    /**
     * 存储IP地址
     */
    private String storageIp;

    /**
     * 存储端口
     */
    private Integer storagePort;

    /**
     * 容量
     */
    private Integer capacity;

    /**
     * 存储策略
     */
    private Integer strategy;

    /**
     * 存储路径
     */
    private String storagePath;

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

    public String getStoragePath() {
        return storagePath;
    }

    public void setStoragePath(String storagePath) {
        this.storagePath = storagePath;
    }


    @Override
    public String toString() {
        return "PicStorageStrategy{" +
                ", dataType=" + dataType +
                ", storageIp='" + storageIp + '\'' +
                ", storagePort=" + storagePort +
                ", capacity=" + capacity +
                ", strategy=" + strategy +
                ", storagePath='" + storagePath + '\'' +
                '}';
    }
}