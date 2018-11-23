/*
 * www.unisinsight.com Inc.
 * Copyright (c) 2018 All Rights Reserved
 */
package com.unisinsight.vdp.core.dto.response.bean;

import java.io.Serializable;

/**
 * 请求修改留存期时间接口返回的数据封装
 *
 * @author liujiaji [HZ.liujiaji@h3c.com]
 * @date 2018/10/25 16:22
 * @since 1.0
 */
public class DataRetentionPeriodInfo implements Serializable {

    private static final long serialVersionUID = -7226442599767927400L;

    /**
     * 数据类型
     */
    private Integer type;

    /**
     * 修改的天数
     */
    private Integer value;

    /**
     * 修改的服务
     */
    private String service;


    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    @Override
    public String toString() {
        return "DataRetentionPeriodInfo{" +
                "type=" + type +
                ", value=" + value +
                ", service='" + service + '\'' +
                '}';
    }
}