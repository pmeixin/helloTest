/*
 * www.unisinsight.com Inc.
 * Copyright (c) 2018 All Rights Reserved
 */
package com.unisinsight.vdp.core.dto.request;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.List;

/**
 * 视频收藏组ReqDTO
 *
 * @author wangshuai [KF.wangshuaiB@h3c.com]
 * @date 2018/09/18
 * @since 1.0
 */
public class CollectionGroupReqDTO implements Serializable {
    private static final long serialVersionUID = -2244970206517220985L;
    /**
     * 分组id
     */
    @Range(min = 1, max = Integer.MAX_VALUE,  message = "范围必须在1-2147483647之间")
    private Integer groupId;

    /**
     * 资源组分组名称
     */
    @Length(min = 1, max = 100, message = "长度必须在1-100之间")
    @NotBlank(message = "分组名称不能为空")
    private String name;

    /**
     * 用户id
     */
    private String createUserCode;

    /**
     * 用户名称
     */
    private String createUserName;

    /**
     * 修改用户id
     */
    private String updateUserCode;

    /**
     * 修改用户名称
     */
    private String updateUserName;

    /**
     * 组下面包含多个监控点
     */
    private List<CollectionDeviceReqDTO> children;

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

    public List<CollectionDeviceReqDTO> getChildren() {
        return children;
    }

    public void setChildren(List<CollectionDeviceReqDTO> children) {
        this.children = children;
    }

    @Override
    public String toString() {
        return "CollectionGroupReqDTO{"
                + "groupId=" + groupId
                + ", name='" + name + '\''
                + ", createUserCode='" + createUserCode + '\''
                + ", createUserName='" + createUserName + '\''
                + ", updateUserCode='" + updateUserCode + '\''
                + ", updateUserName='" + updateUserName + '\''
                + ", children=" + children
                + '}';
    }
}
