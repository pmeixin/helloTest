package com.unisinsight.vdp.core.model;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
* java类作用描述 监控点对象
* @author unisinsight  [KF.hujunA@h3c.com]
* @date   2018/10/8 19:27
* @since  1.0
*/
@Table(name = "collection_device")
public class CollectionDevice {
    /**
     * 收藏的设备id
     */
    @Id
    @Column(name = "collection_device_id")
    private Long collectionDeviceId;

    /**
     * 节点Id，唯一标识
     */
    @Column(name = "id")
    private String id;

    /**
     * 节点所属组织编码
     */
    @Column(name = "org_code")
    private String orgCode;

    /**
     * 节点名称
     */
    @Column(name = "name")
    private String name;

    /**
     * 通道类型：1:视频输入通道；2：解码通道；3：报警输入通道
     */
    @Column(name = "unit_type")
    private String unitType;

    /**
     * 节点所属设备编码，返回节点为通道节点时有值，其他为空
     */
    @Column(name = "device_code")
    private String deviceCode;

    /**
     * 设备或通道在线状态，1：在线；其他：离线；组织节点为空此属性为空
     */
    @Column(name = "online_status")
    private int onlineStatus;

    /**
     * 通道是否启用，1：启用；2:，其他：关闭；组织节点和设备节 点属性为空
     */
    @Column(name = "is_start")
    private String isStart;

    /**
     * 摄像机类型，1：枪机；2：球机；3：半球机；组织节点和设备，节点此属性为空
     */
    @Column(name = "camera_type")
    private String cameraType;

    /**
     * 节点类型，0：组织节点；1：设备节点；2：通道节点
     */
    @Column(name = "node_type")
    private String nodeType;

    /**
     * 设备型号，1：编码器；2：解码器；3：报警主机；5：卡口设备；组织节点忽略为0
     */
    private String category;

    /**
     * 设备型号，1：DVR；2：IPC；3：MDVR；4：NVR；5：AVR；组织节点和通道为0
     */
    @Column(name = "device_type")
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
     * 收藏组Id
     */
    @Column(name = "group_id")
    private Integer groupId;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 更新时间
     */
    @Column(name = "update_time")
    private Date updateTime;

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
     * 更新人用户id
     */
    @Column(name = "update_user_code")
    private String updateUserCode;

    /**
     * 更新用户名称
     */
    @Column(name = "update_user_name")
    private String updateUserName;

    /**
     * ip地址
     */
    @Column(name = "ip")
    private String ip;

    /**
     * 安装位置信息
     */
    @Column(name = "install_addr")
    private String installAddr;

    /**
     * 安装地点行政区划代码
     */
    @Column(name = "place_code")
    private String placeCode;

    /**
     * 视频插件OCX标示
     */
    @Column(name = "u_id")
    private String uId;

    /**资源点id
     * @return collection_device_id
     */
    public Long getCollectionDeviceId() {
        return collectionDeviceId;
    }
    /**
     * 资源点id
     * @param collectionDeviceId 资源点id
     */
    public void setCollectionDeviceId(Long collectionDeviceId) {
        this.collectionDeviceId = collectionDeviceId;
    }

    /**
     * 获取节点Id，唯一标识
     *
     * @return id - 节点Id，唯一标识
     */
    public String getId() {
        return id;
    }

    /**
     * 设置节点Id，唯一标识
     *
     * @param id 节点Id，唯一标识
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 获取节点所属组织编码
     *
     * @return orgCode - 节点所属组织编码
     */
    public String getOrgCode() {
        return orgCode;
    }
    /**
     * 设置节点所属组织编码
     *
     * @param orgCode 节点所属组织编码
     */
    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    /**
     * 获取节点名称
     *
     * @return name - 节点名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置节点名称
     *
     * @param name 节点名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取通道类型：1:视频输入通道；2：解码通道；3：报警输入通道
     *
     * @return unit_type - 通道类型：1:视频输入通道；2：解码通道；3：报警输入通道
     */
    public String getUnitType() {
        return unitType;
    }

    /**
     * 设置通道类型：1:视频输入通道；2：解码通道；3：报警输入通道
     *
     * @param unitType 通道类型：1:视频输入通道；2：解码通道；3：报警输入通道
     */
    public void setUnitType(String unitType) {
        this.unitType = unitType;
    }

    /**
     * 获取节点所属设备编码，返回节点为通道节点时有值，其他为空
     *
     * @return deviceCode - 节点所属设备编码，返回节点为通道节点时有值，其他为空
     */
    public String getDeviceCode() {
        return deviceCode;
    }
    /**
     * 设置节点所属设备编码，返回节点为通道节点时有值，其他为空
     *
     * @param deviceCode 节点所属设备编码，返回节点为通道节点时有值，其他为空
     */
    public void setDeviceCode(String deviceCode) {
        this.deviceCode = deviceCode;
    }

    /**
     * 获取设备或通道在线状态，1：在线；其他：离线；组织节点为空此属性为空
     *
     * @return online_status - 设备或通道在线状态，1：在线；其他：离线；组织节点为空此属性为空
     */
    public int getOnlineStatus() {
        return onlineStatus;
    }


/**
     * 设置设备或通道在线状态，1：在线；其他：离线；组织节点为空此属性为空
     *
     * @param onlineStatus 设备或通道在线状态，1：在线；其他：离线；组织节点为空此属性为空
     */
    public void setOnlineStatus(int onlineStatus) {
    this.onlineStatus = onlineStatus;
}

    /**
     * 获取通道是否启用，1：启用；2:，其他：关闭；组织节点和设备节 点属性为空
     *
     * @return is_start - 通道是否启用，1：启用；2:，其他：关闭；组织节点和设备节 点属性为空
     */
    public String getIsStart() {
        return isStart;
    }

    /**
     * 设置通道是否启用，1：启用；2:，其他：关闭；组织节点和设备节 点属性为空
     *
     * @param isStart 通道是否启用，1：启用；2:，其他：关闭；组织节点和设备节 点属性为空
     */
    public void setIsStart(String isStart) {
        this.isStart = isStart;
    }

    /**
     * 获取摄像机类型，1：枪机；2：球机；3：半球机；组织节点和设备，节点此属性为空
     *
     * @return camera_type - 摄像机类型，1：枪机；2：球机；3：半球机；组织节点和设备，节点此属性为空
     */
    public String getCameraType() {
        return cameraType;
    }

    /**
     * 设置摄像机类型，1：枪机；2：球机；3：半球机；组织节点和设备，节点此属性为空
     *
     * @param cameraType 摄像机类型，1：枪机；2：球机；3：半球机；组织节点和设备，节点此属性为空
     */
    public void setCameraType(String cameraType) {
        this.cameraType = cameraType;
    }

    /**
     * 获取节点类型，0：组织节点；1：设备节点；2：通道节点
     *
     * @return node_type - 节点类型，0：组织节点；1：设备节点；2：通道节点
     */
    public String getNodeType() {
        return nodeType;
    }

    /**
     * 设置节点类型，0：组织节点；1：设备节点；2：通道节点
     *
     * @param nodeType 节点类型，0：组织节点；1：设备节点；2：通道节点
     */
    public void setNodeType(String nodeType) {
        this.nodeType = nodeType;
    }

    /**
     * 获取设备型号，1：编码器；2：解码器；3：报警主机；5：卡口设备；组织节点忽略为0
     *
     * @return category - 设备型号，1：编码器；2：解码器；3：报警主机；5：卡口设备；组织节点忽略为0
     */
    public String getCategory() {
        return category;
    }

    /**
     * 设置设备型号，1：编码器；2：解码器；3：报警主机；5：卡口设备；组织节点忽略为0
     *
     * @param category 设备型号，1：编码器；2：解码器；3：报警主机；5：卡口设备；组织节点忽略为0
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * 获取设备型号，1：DVR；2：IPC；3：MDVR；4：NVR；5：AVR；组织节点和通道为0
     *
     * @return device_type - 设备型号，1：DVR；2：IPC；3：MDVR；4：NVR；5：AVR；组织节点和通道为0
     */
    public String getDeviceType() {
        return deviceType;
    }

    /**
     * 设置设备型号，1：DVR；2：IPC；3：MDVR；4：NVR；5：AVR；组织节点和通道为0
     *
     * @param deviceType 设备型号，1：DVR；2：IPC；3：MDVR；4：NVR；5：AVR；组织节点和通道为0
     */
    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    /**
     * 获取经度，返回节点为通道节点时有值，其他为空
     *
     * @return longitude - 经度，返回节点为通道节点时有值，其他为空
     */
    public String getLongitude() {
        return longitude;
    }

    /**
     * 设置经度，返回节点为通道节点时有值，其他为空
     *
     * @param longitude 经度，返回节点为通道节点时有值，其他为空
     */
    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    /**
     * 获取纬度，返回节点为通道节点时有值，其他为空
     *
     * @return latitude - 纬度，返回节点为通道节点时有值，其他为空
     */
    public String getLatitude() {
        return latitude;
    }

    /**
     * 设置纬度，返回节点为通道节点时有值，其他为空
     *
     * @param latitude 纬度，返回节点为通道节点时有值，其他为空
     */
    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    /**
     * 获取收藏组Id
     *
     * @return group_id - 收藏组Id
     */
    public Integer getGroupId() {
        return groupId;
    }

    /**
     * 设置收藏组Id
     *
     * @param groupId 收藏组Id
     */
    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
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
     * 获取更新用户名称
     *
     * @return update_user_name - 更新用户名称
     */
    public String getUpdateUserName() {
        return updateUserName;
    }

    /**
     * 设置更新用户名称
     *
     * @param updateUserName 更新用户名称
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

    public String getuId() {
        return uId;
    }

    public void setuId(String uId) {
        this.uId = uId;
    }
}