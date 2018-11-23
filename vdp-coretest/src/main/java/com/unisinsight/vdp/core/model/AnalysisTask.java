package com.unisinsight.vdp.core.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "analysis_task")
public class AnalysisTask {
    /**
     * 任务唯一ID
     */
    @Id
    private String id;

    /**
     * 其他微服务(IA或IMS)返回ID
     */
    @Column(name = "outer_task_id")
    private String outerTaskId;

    /**
     * 任务名称
     */
    private String name;

    /**
     * 任务类型 0：图片流(人脸抓拍机) 1：视频流(普通IPC) 2:结构化解析
     */
    private Integer type;

    /**
     * 0: 创建失败 1：创建成功 2：创建中
     */
    private Integer status;

    /**
     * 任务创建人
     */
    private String creator;

    /**
     * 备注,描述
     */
    private String memo;

    /**
     * vms设备唯一标识
     */
    @Column(name = "device_id")
    private String deviceId;

    /**
     * 设备实际名称
     */
    @Column(name = "device_name")
    private String deviceName;

    /**
     * 任务创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 任务更新时间
     */
    @Column(name = "update_time")
    private Date updateTime;

    /**
     * 获取任务唯一ID
     *
     * @return id - 任务唯一ID
     */
    public String getId() {
        return id;
    }

    /**
     * 设置任务唯一ID
     *
     * @param id 任务唯一ID
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 获取其他微服务(IA或IMS)返回ID
     *
     * @return outer_task_id - 其他微服务(IA或IMS)返回ID
     */
    public String getOuterTaskId() {
        return outerTaskId;
    }

    /**
     * 设置其他微服务(IA或IMS)返回ID
     *
     * @param outerTaskId 其他微服务(IA或IMS)返回ID
     */
    public void setOuterTaskId(String outerTaskId) {
        this.outerTaskId = outerTaskId;
    }

    /**
     * 获取任务名称
     *
     * @return name - 任务名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置任务名称
     *
     * @param name 任务名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取任务类型 0：图片流(人脸抓拍机) 1：视频流(普通IPC) 2:结构化解析
     *
     * @return type - 任务类型 0：图片流(人脸抓拍机) 1：视频流(普通IPC) 2:结构化解析
     */
    public Integer getType() {
        return type;
    }

    /**
     * 设置任务类型 0：图片流(人脸抓拍机) 1：视频流(普通IPC) 2:结构化解析
     *
     * @param type 任务类型 0：图片流(人脸抓拍机) 1：视频流(普通IPC) 2:结构化解析
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * 获取0: 创建失败 1：创建成功 2：创建中
     *
     * @return status - 0: 创建失败 1：创建成功 2：创建中
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置0: 创建失败 1：创建成功 2：创建中
     *
     * @param status 0: 创建失败 1：创建成功 2：创建中
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取任务创建人
     *
     * @return creator - 任务创建人
     */
    public String getCreator() {
        return creator;
    }

    /**
     * 设置任务创建人
     *
     * @param creator 任务创建人
     */
    public void setCreator(String creator) {
        this.creator = creator;
    }

    /**
     * 获取备注,描述
     *
     * @return memo - 备注,描述
     */
    public String getMemo() {
        return memo;
    }

    /**
     * 设置备注,描述
     *
     * @param memo 备注,描述
     */
    public void setMemo(String memo) {
        this.memo = memo;
    }

    /**
     * 获取vms设备唯一标识
     *
     * @return device_id - vms设备唯一标识
     */
    public String getDeviceId() {
        return deviceId;
    }

    /**
     * 设置vms设备唯一标识
     *
     * @param deviceId vms设备唯一标识
     */
    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    /**
     * 获取设备实际名称
     *
     * @return device_name - 设备实际名称
     */
    public String getDeviceName() {
        return deviceName;
    }

    /**
     * 设置设备实际名称
     *
     * @param deviceName 设备实际名称
     */
    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    /**
     * 获取任务创建时间
     *
     * @return create_time - 任务创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置任务创建时间
     *
     * @param createTime 任务创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取任务更新时间
     *
     * @return update_time - 任务更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置任务更新时间
     *
     * @param updateTime 任务更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}