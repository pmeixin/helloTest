/*
 * www.unisinsight.com Inc.
 * Copyright (c) 2018 All Rights Reserved
 */
package com.unisinsight.vdp.core.dto.response;
import java.io.Serializable;
import java.util.List;

/**
 * 视频收藏组ResDTO
 *
 * @author wangshuai [KF.wangshuaiB@h3c.com]
 * @date 2018/09/18
 * @since 1.0
 */
public class CollectionGroupResDTO implements Serializable {

    private static final long serialVersionUID = 16310047252817915L;
    /**
     * 资源组id
     */
    private Integer groupId;

    /**
     * 资源组分组名称
     */
    private String name;

    /**
     * 创建时间
     */
    private Long createTime;

    /**
     * 用户id
     */
    private String createUserCode;

    /**
     * 用户名称
     */
    private String createUserName;

    /**
     * 修改时间
     */
    private Long updateTime;

    /**
     * 修改用户id
     */
    private String updateUserCode;

    /**
     * 修改用户名称
     */
    private String updateUserName;

    /**
     * 视频组资源
     */
    private List<CollectionDeviceResDTO> children;

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
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

    public List<CollectionDeviceResDTO> getChildren() {
        return children;
    }

    public void setChildren(List<CollectionDeviceResDTO> children) {
        this.children = children;
    }

    @Override
    public String toString() {
        return "CollectionGroupResDTO{" + "groupId=" + groupId + ", name='" + name + '\''
                + ", createTime=" + createTime + ", createUserCode='" + createUserCode + '\''
                + ", createUserName='" + createUserName + '\'' + ", updateTime=" + updateTime
                + ", updateUserCode='" + updateUserCode + '\'' + ", updateUserName='" + updateUserName + '\''
                + ", children=" + children + '}';
    }
}
