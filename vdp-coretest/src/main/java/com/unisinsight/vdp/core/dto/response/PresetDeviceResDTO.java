/*
 * www.unisinsight.com Inc.
 * Copyright (c) 2018 All Rights Reserved
 */
package com.unisinsight.vdp.core.dto.response;

import java.io.Serializable;

/**
 * 预置点ResDTO
 *
 * @author hujun [KF.hujunA@h3c.com]
 * @date 2018/10/11 09:55
 * @since 1.0
 */
public class PresetDeviceResDTO implements Serializable {

    private static final long serialVersionUID = 7136212233594811264L;
    /**
     * 预设点id
     */
    private Integer presetDeviceId;

    /**
     * 设备id
     */
    private String id;

    /**
     * 预设点名称
     */
    private String name;

    /**
     * 创建时间
     */
    private Long createTime;

    /**
     * 创建人id
     */
    private String createUserCode;

    /**
     * 创建人名称
     */
    private String createUserName;

    /**
     * 修改时间
     */
    private Long updateTime;

    /**
     * 修改人id
     */
    private String updateUserCode;

    /**
     * 修改人名称
     */
    private String updateUserName;

    public Integer getPresetDeviceId() {
        return presetDeviceId;
    }

    public void setPresetDeviceId(Integer presetDeviceId) {
        this.presetDeviceId = presetDeviceId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public String getCreateUserCode() {
        return createUserCode;
    }

    public void setCreateUserCode(String createUserCode) {
        this.createUserCode = createUserCode;
    }

    public String getCreateUserName() {
        return createUserName;
    }

    public void setCreateUserName(String createUserName) {
        this.createUserName = createUserName;
    }

    public Long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdateUserCode() {
        return updateUserCode;
    }

    public void setUpdateUserCode(String updateUserCode) {
        this.updateUserCode = updateUserCode;
    }

    public String getUpdateUserName() {
        return updateUserName;
    }

    public void setUpdateUserName(String updateUserName) {
        this.updateUserName = updateUserName;
    }

    @Override
    public String toString() {
        return "PresetDeviceResDTO{" + "presetDeviceId=" + presetDeviceId + ", id='" + id + '\''
                + ", name='" + name + '\'' + ", createTime=" + createTime + ", createUserCode='" + createUserCode + '\''
                + ", createUserName='" + createUserName + '\'' + ", updateTime=" + updateTime
                + ", updateUserCode='" + updateUserCode + '\'' + ", updateUserName='" + updateUserName + '\'' + '}';
    }
}
