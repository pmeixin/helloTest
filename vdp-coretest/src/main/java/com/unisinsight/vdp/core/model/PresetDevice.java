package com.unisinsight.vdp.core.model;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
* 预置点实体
* @author unisinsight  [KF.hujunA@h3c.com]
* @date   2018/10/13 15:27
* @since  1.0
*/
@Table(name = "preset_device")
public class PresetDevice {
    /**
     * 预设点id
     */
    @Id
    @Column(name = "preset_device_id")
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
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 创建人id
     */
    @Column(name = "create_user_code")
    private String createUserCode;

    /**
     * 创建人名称
     */
    @Column(name = "create_user_name")
    private String createUserName;

    /**
     * 修改时间
     */
    @Column(name = "update_time")
    private Date updateTime;

    /**
     * 修改人id
     */
    @Column(name = "update_user_code")
    private String updateUserCode;

    /**
     * 修改人名称
     */
    @Column(name = "update_user_name")
    private String updateUserName;


    /**
     * 获取预设点id
     *
     * @return preset_device_id - 预设点id
     */
    public Integer getPresetDeviceId() {
        return presetDeviceId;
    }

    /**
     * 设置预设点id
     *
     * @param presetDeviceId 预设点id
     */
    public void setPresetDeviceId(Integer presetDeviceId) {
        this.presetDeviceId = presetDeviceId;
    }

    /**
     * 获取设备id
     *
     * @return id - 设备id
     */
    public String getId() {
        return id;
    }

    /**
     * 设置设备id
     *
     * @param id 设备id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 获取预设点名称
     *
     * @return name - 预设点名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置预设点名称
     *
     * @param name 预设点名称
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
     * 获取创建人id
     *
     * @return create_user_id - 创建人id
     */
    public String getCreateUserCode() {
        return createUserCode;
    }

    /**
     * 设置创建人id
     *
     * @param createUserCode 创建人id
     */
    public void setCreateUserCode(String createUserCode) {
        this.createUserCode = createUserCode;
    }
    /**
     * 获取创建人名称
     *
     * @return create_user_name - 创建人名称
     */
    public String getCreateUserName() {
        return createUserName;
    }

    /**
     * 设置创建人名称
     *
     * @param createUserName 创建人名称
     */
    public void setCreateUserName(String createUserName) {
        this.createUserName = createUserName;
    }

    /**
     * 获取修改时间
     *
     * @return update_time - 修改时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置修改时间
     *
     * @param updateTime 修改时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 获取修改人id
     *
     * @return update_user_id - 修改人id
     */
    public String getUpdateUserCode() {
        return updateUserCode;
    }

    /**
     * 设置修改人id
     *
     * @param updateUserCode 修改人id
     */
    public void setUpdateUserCode(String updateUserCode) {
        this.updateUserCode = updateUserCode;
    }

    /**
     * 获取修改人名称
     *
     * @return update_user_name - 修改人名称
     */
    public String getUpdateUserName() {
        return updateUserName;
    }

    /**
     * 设置修改人名称
     *
     * @param updateUserName 修改人名称
     */
    public void setUpdateUserName(String updateUserName) {
        this.updateUserName = updateUserName;
    }


}