package com.unisinsight.vdp.core.model;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
* java类作用描述 资源组对象
* @author unisinsight  [KF.hujunA@h3c.com]
* @date   2018/10/8 19:29
* @since  1.0
*/
@Table(name = "collection_group")
public class CollectionGroup {
    /**
     * 分组id
     */
    @Id
    @Column(name = "group_id")
    private Integer groupId;

    /**
     * 资源分组名称
     */
    private String name;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 创建用户id
     */
    @Column(name = "create_user_code")
    private String createUserCode;

    /**
     * 创建用户名称
     */
    @Column(name = "create_user_name")
    private String createUserName;

    /**
     * 更新时间
     */
    @Column(name = "update_time")
    private Date updateTime;

    /**
     * 更新用户id
     */
    @Column(name = "update_user_code")
    private String updateUserCode;

    /**
     * 更新用户名
     */
    @Column(name = "update_user_name")
    private String updateUserName;

    /**
     * 获取分组id
     *
     * @return group_id - 分组id
     */
    public Integer getGroupId() {
        return groupId;
    }

    /**
     * 设置分组id
     *
     * @param groupId 分组id
     */
    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    /**
     * 获取资源分组名称
     *
     * @return name - 资源分组名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置资源分组名称
     *
     * @param name 资源分组名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取创建时间
     *
     * @return create_time - 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取创建用户名称
     *
     * @return create_user_name - 创建用户名称
     */
    public String getCreateUserName() {
        return createUserName;
    }

    /**
     * 设置创建用户名称
     *
     * @param createUserName 创建用户名称
     */
    public void setCreateUserName(String createUserName) {
        this.createUserName = createUserName;
    }

    /**
     * 获取更新时间
     *
     * @return update_time - 更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置更新时间
     *
     * @param updateTime 更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 获取更新用户名
     *
     * @return update_user_name - 更新用户名
     */
    public String getUpdateUserName() {
        return updateUserName;
    }

    /**
     * 设置更新用户名
     *
     * @param updateUserName 更新用户名
     */
    public void setUpdateUserName(String updateUserName) {
        this.updateUserName = updateUserName;
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
}