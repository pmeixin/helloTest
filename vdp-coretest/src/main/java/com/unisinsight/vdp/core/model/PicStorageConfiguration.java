package com.unisinsight.vdp.core.model;

import javax.persistence.*;

@Table(name = "pic_storage_configuration")
public class PicStorageConfiguration {

    @Id
    private Short id;

    /**
     * 图片数据类型
     */
    @Column(name = "data_type")
    private Integer dataType;

    /**
     * 存储IP地址
     */
    @Column(name = "storage_ip")
    private String storageIp;

    /**
     * 存储端口
     */
    @Column(name = "storage_port")
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
    @Column(name = "storage_path")
    private String storagePath;

    /**
     * 剩余容量
     */
    @Column(name = "remain_capacity")
    private Integer remainCapacity;

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

    public Integer getDataType() {
        return dataType;
    }

    public void setDataType(Integer dataType) {
        this.dataType = dataType;
    }

    /**
     * @return storage_ip
     */
    public String getStorageIp() {
        return storageIp;
    }

    /**
     * @param storageIp
     */
    public void setStorageIp(String storageIp) {
        this.storageIp = storageIp;
    }

    /**
     * @return storage_port
     */
    public Integer getStoragePort() {
        return storagePort;
    }

    /**
     * @param storagePort
     */
    public void setStoragePort(Integer storagePort) {
        this.storagePort = storagePort;
    }

    /**
     * @return capacity
     */
    public Integer getCapacity() {
        return capacity;
    }

    /**
     * @param capacity
     */
    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    /**
     * @return strategy
     */
    public Integer getStrategy() {
        return strategy;
    }

    /**
     * @param strategy
     */
    public void setStrategy(Integer strategy) {
        this.strategy = strategy;
    }

    /**
     * @return storage_path
     */
    public String getStoragePath() {
        return storagePath;
    }

    /**
     * @param storagePath
     */
    public void setStoragePath(String storagePath) {
        this.storagePath = storagePath;
    }

    /**
     * @return remain_capacity
     */
    public Integer getRemainCapacity() {
        return remainCapacity;
    }

    /**
     * @param remainCapacity
     */
    public void setRemainCapacity(Integer remainCapacity) {
        this.remainCapacity = remainCapacity;
    }
}