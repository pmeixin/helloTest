/*
 * www.unisinsight.com Inc.
 * Copyright (c) 2018 All Rights Reserved
 */
package com.unisinsight.vdp.core.dto.request;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * 预设点ReqDTO
 *
 * @author hujun [KF.hujunA@h3c.com]
 * @date 2018/10/11 09:41
 * @since 1.0
 */
public class PresetDeviceReqDTO implements Serializable {
    private static final long serialVersionUID = -7791322931259955108L;
    /**
     * 预设点id
     */
    @Range(min = 1, max = Integer.MAX_VALUE,  message = "范围必须在1-2147483647之间")
    private Integer presetDeviceId;

    /**
     * 设备id
     */
    @Length(min = 1, max = 32, message = "长度必须在1-32之间")
    @NotBlank(message = "预设点id不能为空")
    private String id;

    /**
     * 预设点名称
     */
    @NotBlank(message = "预设点名称不能为空")
    @Size(min = 1, max = 50, message = "长度必须在1-50之间")
    private String name;

    /**
     * 创建时间
     */
    @Range(min = 1, max = Long.MAX_VALUE,  message = "范围必须在1-2147483647之间")
    private Long createTime;

    /**
     * 创建人id
     */
    @Length(min = 1, max = 16, message = "长度必须在1-16之间")
    private String createUserCode;

    /**
     * 创建人名称
     */
    @Length(min = 1, max = 16, message = "长度必须在1-16之间")
    private String createUserName;

    /**
     * 修改时间
     */
    @Range(min = 1, max = Long.MAX_VALUE,  message = "范围必须在1-2147483647之间")
    private Long updateTime;

    /**
     * 修改人id
     */
    @Length(min = 1, max = 16, message = "长度必须在1-16之间")
    private String updateUserCode;

    /**
     * 修改人名称
     */
    @Length(min = 1, max = 16, message = "长度必须在1-16之间")
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
        return "PresetDeviceReqDTO{" + "presetDeviceId=" + presetDeviceId + ", id='" + id + '\''
                + ", name='" + name + '\'' + ", createTime=" + createTime + ", createUserCode='" + createUserCode + '\''
                + ", createUserName='" + createUserName + '\'' + ", updateTime=" + updateTime
                + ", updateUserCode='" + updateUserCode + '\'' + ", updateUserName='" + updateUserName + '\'' + '}';
    }
}
