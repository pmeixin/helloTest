/*
 * www.unisinsight.com Inc.
 * Copyright (c) 2018 All Rights Reserved
 */
package com.unisinsight.vdp.core.dto.request;

import java.io.Serializable;

/**
 * 图片数据云存储地址、端口、容量、覆盖策略DTO
 *
 * @author liujiaji [HZ.liujiaji@h3c.com]
 * @date 2018/10/25 08:40
 * @since 1.0
 */
public class PicStorageConfigurationReqDTO implements Serializable {

    private static final long serialVersionUID = 5554918960105619841L;

    /**
     * 图片数据类型
     */
    private Integer dataType;

    /**
     * 云地址
     */
    private String storageIp;

    /**
     * 端口
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





    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getDataType() {
        return dataType;
    }

    public void setDataType(Integer dataType) {
        this.dataType = dataType;
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


    @Override
    public String toString() {
        return "PicStorageConfigurationReqDTO{" +
                "dataType=" + dataType +
                ", storageIp='" + storageIp + '\'' +
                ", storagePort=" + storagePort +
                ", capacity=" + capacity +
                ", strategy=" + strategy +
                '}';
    }
}