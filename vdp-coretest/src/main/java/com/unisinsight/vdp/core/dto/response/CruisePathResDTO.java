/*
 * www.unisinsight.com Inc.
 * Copyright (c) 2018 All Rights Reserved
 */
package com.unisinsight.vdp.core.dto.response;
import java.io.Serializable;
import java.util.List;

/**
 * 巡航路径ResDTO
 *
 * @author hujun [KF.hujunA@h3c.com]
 * @date 2018/10/11 09:54
 * @since 1.0
 */
public class CruisePathResDTO implements Serializable {

    private static final long serialVersionUID = 5946990829443803794L;
    /**
     * 巡航路径主键id
     */
    private Integer cruisePathId;

    /**
     * 设备id

     */
    private String id;

    /**
     * 巡航路径名称
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

    /**
     * 巡航路径有多个预设点
     */
    private List<CruisePresetResDTO> children;

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

    public Long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
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

    public String getCreateUserName() {
        return createUserName;
    }

    public void setCreateUserName(String createUserName) {
        this.createUserName = createUserName;
    }

    public String getUpdateUserName() {
        return updateUserName;
    }

    public void setUpdateUserName(String updateUserName) {
        this.updateUserName = updateUserName;
    }

    public List<CruisePresetResDTO> getChildren() {
        return children;
    }

    public void setChildren(List<CruisePresetResDTO> children) {
        this.children = children;
    }

    @Override
    public String toString() {
        return "CruisePathResDTO{" + "cruisePathId=" + cruisePathId + ", id='" + id + '\''
                + ", name='" + name + '\'' + ", createTime=" + createTime + ", createUserCode=" + createUserCode
                + ", createUserName='" + createUserName + '\'' + ", updateTime=" + updateTime
                + ", updateUserCode=" + updateUserCode + ", updateUserName='" + updateUserName + '\''
                + ", children=" + children + '}';
    }
}
