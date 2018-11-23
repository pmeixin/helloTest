/*
 * www.unisinsight.com Inc.
 * Copyright (c) 2018 All Rights Reserved
 */
package com.unisinsight.vdp.core.dto.request;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * 巡航路径ReqDTO
 * @author hujun [KF.hujunA@h3c.com]
 * @date 2018/10/11 09:40
 * @since 1.0
 */
public class CruisePathReqDTO implements Serializable {
    private static final long serialVersionUID = 8772117105171871473L;
    /**
     * 巡航路径主键id
     */
    @Range(min = 1, max = Integer.MAX_VALUE,  message = "范围必须在1-2147483647之间")
    private Integer cruisePathId;

    /**
     * 关键点id
     */
    @Range(min = 1, max = Integer.MAX_VALUE,  message = "范围必须在1-2147483647之间")
    private Integer cruisePresetId;

    /**
     * 设备id
     */
    @NotNull(message = "巡航路径id不能为空")
    @Length(min = 1, max = 32, message = "长度必须在1-32之间")
    private String id;

    /**
     * 巡航路径名称
     */
    @Length(min = 1, max = 50, message = "长度必须在1-50之间")
    @NotNull(message = "巡航路径名称不能为空")
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

    /**
     * 巡航路径有多个关键点
     */
    private List<CruisePresetReqDTO> children;

    public Integer getCruisePathId() {
        return cruisePathId;
    }

    public void setCruisePathId(Integer cruisePathId) {
        this.cruisePathId = cruisePathId;
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

    public String getUpdateUserName() {
        return updateUserName;
    }

    public void setUpdateUserName(String updateUserName) {
        this.updateUserName = updateUserName;
    }

    public List<CruisePresetReqDTO> getChildren() {
        return children;
    }

    public void setChildren(List<CruisePresetReqDTO> children) {
        this.children = children;
    }

    public String getCreateUserCode() {
        return createUserCode;
    }

    public void setCreateUserCode(String createUserCode) {
        this.createUserCode = createUserCode;
    }

    public String getUpdateUserCode() {
        return updateUserCode;
    }

    public void setUpdateUserCode(String updateUserCode) {
        this.updateUserCode = updateUserCode;
    }

    public Integer getCruisePresetId() {
        return cruisePresetId;
    }

    public void setCruisePresetId(Integer cruisePresetId) {
        this.cruisePresetId = cruisePresetId;
    }

    @Override
    public String toString() {
        return "CruisePathReqDTO{" + "cruisePathId=" + cruisePathId + ", cruisePresetId=" + cruisePresetId
                + ", id='" + id + '\'' + ", name='" + name + '\'' + ", createTime=" + createTime
                + ", createUserCode='" + createUserCode + '\'' + ", createUserName='" + createUserName + '\''
                + ", updateTime=" + updateTime + ", updateUserCode='" + updateUserCode + '\''
                + ", updateUserName='" + updateUserName + '\'' + ", children=" + children + '}';
    }
}
