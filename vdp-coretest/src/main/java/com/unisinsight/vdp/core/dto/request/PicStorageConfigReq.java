/*
 * www.unisinsight.com Inc.
 * Copyright (c) 2018 All Rights Reserved
 */
package com.unisinsight.vdp.core.dto.request;

/**
 * 请求三方接口数据封装
 *
 * @author liujiaji [liu.jiaji@unisinsight.com]
 * @date 2018/11/07 11:34
 * @since 1.0
 */
public class PicStorageConfigReq {

    /**
     * 数据类型
     */
    private Integer data_type;
    /**
     * 容量
     */
    private Integer capacity;
    /**
     * 策略
     */
    private Integer strategy;
    /**
     * 端口
     */
    private Integer storage_port;
    /**
     * ip
     */
    private String storage_ip;

    public Integer getData_type() {
        return data_type;
    }

    public void setData_type(Integer data_type) {
        this.data_type = data_type;
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

    public Integer getStorage_port() {
        return storage_port;
    }

    public void setStorage_port(Integer storage_port) {
        this.storage_port = storage_port;
    }

    public String getStorage_ip() {
        return storage_ip;
    }

    public void setStorage_ip(String storage_ip) {
        this.storage_ip = storage_ip;
    }

    @Override
    public String toString() {
        return "PicStorageConfigReq{" +
                "data_type=" + data_type +
                ", capacity=" + capacity +
                ", strategy=" + strategy +
                ", storage_port=" + storage_port +
                ", storage_ip='" + storage_ip + '\'' +
                '}';
    }
}