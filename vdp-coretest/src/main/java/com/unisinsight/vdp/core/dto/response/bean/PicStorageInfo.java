/*
 * www.unisinsight.com Inc.
 * Copyright (c) 2018 All Rights Reserved
 */
package com.unisinsight.vdp.core.dto.response.bean;

import java.util.List;

/**
 * 请求三方接口返回的数据全部封装
 *
 * @author liujiaji [HZ.liujiaji@h3c.com]
 * @date 2018/10/29 08:34
 * @since 1.0
 */
public class PicStorageInfo {

    private Integer remainCapacity;

    private List<PicStorageStrategy> storageConfigEntityList;


    public Integer getRemainCapacity() {
        return remainCapacity;
    }

    public void setRemainCapacity(Integer remainCapacity) {
        this.remainCapacity = remainCapacity;
    }

    public List<PicStorageStrategy> getStorageConfigEntityList() {
        return storageConfigEntityList;
    }

    public void setStorageConfigEntityList(List<PicStorageStrategy> storageConfigEntityList) {
        this.storageConfigEntityList = storageConfigEntityList;
    }


    @Override
    public String toString() {
        return "PicStorageInfo{" +
                "remainCapacity=" + remainCapacity +
                ", storageConfigEntityList=" + storageConfigEntityList +
                '}';
    }
}