/*
 * www.unisinsight.com Inc.
 * Copyright (c) 2018 All Rights Reserved
 */
package com.unisinsight.vdp.core.dto.response;

import java.io.Serializable;

/**
 * 收藏视频资源ResDTO
 *
 * @author wangshuai [KF.wangshuaiB@h3c.com]
 * @date 2018/09/18
 * @since 1.0
 */
public class CollectionDeviceResDTO implements Serializable {
    private static final long serialVersionUID = -6506188462342415118L;

    /**
     * 收藏的设备id
     */
    private Long collectionDeviceId;

    /**
     * 节点Id，唯一标识
     */
    private String id;

    /**
     * 节点所属组织编码
     */
    private String orgCode;

    /**
     * 节点名称
     */
    private String name;

    /**
     * 通道类型：1:视频输入通道；2：解码通道；3：报警输入通道
     */
    private String unitType;

    /**
     * 节点所属设备编码，返回节点为通道节点时有值，其他为空
     */
    private String deviceCode;

    /**
     * 设备或通道在线状态，1：在线；其他：离线；组织节点为空此属性为空
     */
    private Integer onlineStatus;

    /**
     * 通道是否启用，1：启用；2:，其他：关闭；组织节点和设备节 点属性为空
     */
    private String isStart;

    /**
     * 摄像机类型，1：枪机；2：球机；3：半球机；组织节点和设备，节点此属性为空
     */
    private String cameraType;

    /**
     * 节点类型，0：组织节点；1：设备节点；2：通道节点
     */
    private String nodeType;

    /**
     * 设备型号，1：编码器；2：解码器；3：报警主机；5：卡口设备；组织节点忽略为0
     */
    private String category;

    /**
     * 设备型号，1：DVR；2：IPC；3：MDVR；4：NVR；5：AVR；组织节点和通道为0
     */
    private String deviceType;

    /**
     * 经度，返回节点为通道节点时有值，其他为空
     */
    private String longitude;

    /**
     * 纬度，返回节点为通道节点时有值，其他为空
     */
    private String latitude;

    /**
     * 资源组id
     */
    private Integer groupId;

    /**
     * 创建时间
     */
    private Long createTime;

    /**
     * 更新时间
     */
    private Long updateTime;

    /**
     * 创建用户id
     */
    private String createUserCode;

    /**
     * 创建用户名称
     */
    private String createUserName;

    /**
     * 更新人用户id
     */
    private String updateUserCode;

    /**
     * 更新用户名称
     */
    private String updateUserName;

    /**
     * ip地址
     */
    private String ip;

    /**
     * 安装位置信息
     */
    private String installAddr;

    /**
     * 安装地点行政区划代码
     */
    private String placeCode;

    /**
     * 视频插件OCX标示
     */
    private String uId;

    public Long getCollectionDeviceId() {
        return collectionDeviceId;
    }

    public void setCollectionDeviceId(Long collectionDeviceId) {
        this.collectionDeviceId = collectionDeviceId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    public String getDeviceCode() {
        return deviceCode;
    }

    public void setDeviceCode(String deviceCode) {
        this.deviceCode = deviceCode;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getInstallAddr() {
        return installAddr;
    }

    public void setInstallAddr(String installAddr) {
        this.installAddr = installAddr;
    }

    public String getPlaceCode() {
        return placeCode;
    }

    public void setPlaceCode(String placeCode) {
        this.placeCode = placeCode;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUnitType() {
        return unitType;
    }

    public void setUnitType(String unitType) {
        this.unitType = unitType;
    }

    public Integer getOnlineStatus() {
        return onlineStatus;
    }

    public void setOnlineStatus(Integer onlineStatus) {
        this.onlineStatus = onlineStatus;
    }

    public String getIsStart() {
        return isStart;
    }

    public void setIsStart(String isStart) {
        this.isStart = isStart;
    }

    public String getCameraType() {
        return cameraType;
    }

    public void setCameraType(String cameraType) {
        this.cameraType = cameraType;
    }

    public String getNodeType() {
        return nodeType;
    }

    public void setNodeType(String nodeType) {
        this.nodeType = nodeType;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
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
    /**
     * OCX get方法
     * @return uId
     */
    public String getuId() {
        return uId;
    }
    /**
     * OCX set方法
     * @param uId OCX标示
     */
    public void setuId(String uId) {
        this.uId = uId;
    }

    @Override
    public String toString() {
        return "CollectionDeviceResDTO{" + "collectionDeviceId=" + collectionDeviceId + ", id='" + id + '\''
                + ", orgCode='" + orgCode + '\'' + ", name='" + name + '\'' + ", unitType='" + unitType + '\''
                + ", deviceCode='" + deviceCode + '\'' + ", onlineStatus='" + onlineStatus + '\''
                + ", isStart='" + isStart + '\'' + ", cameraType='" + cameraType + '\''
                + ", nodeType='" + nodeType + '\'' + ", category='" + category + '\'' + ", deviceType='" + deviceType + '\''
                + ", longitude='" + longitude + '\'' + ", latitude='" + latitude + '\'' + ", groupId=" + groupId
                + ", createTime=" + createTime + ", updateTime=" + updateTime + ", createUserCode='" + createUserCode + '\''
                + ", createUserName='" + createUserName + '\'' + ", updateUserCode='" + updateUserCode + '\''
                + ", updateUserName='" + updateUserName + '\'' + ", ip='" + ip + '\'' + ", installAddr='" + installAddr + '\''
                + ", placeCode='" + placeCode + '\'' + ", uId='" + uId + '}';
    }
}
