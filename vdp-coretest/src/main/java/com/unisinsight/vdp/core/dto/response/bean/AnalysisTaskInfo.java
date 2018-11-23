/*
 * www.unisinsight.com Inc.
 * Copyright (c) 2018 All Rights Reserved
 */
package com.unisinsight.vdp.core.dto.response.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * description
 *
 * @author daisike [dai.sike@unisinsight.com]
 * @date 2018/10/09 17:29
 * @since 1.0
 */
public class AnalysisTaskInfo implements Serializable {

	private static final long serialVersionUID = -2442633768111985317L;

	/**
	 * 任务id
	 */
	private String id;

	/**
	 * 任务类型 0：图片流(人脸抓拍机) 1：视频流(普通IPC)
	 */
	private Integer type;

	/**
	 * 0: 创建成功 1：创建中 2：创建失败
	 */
	private Integer status;

	/**
	 * 任务创建人
	 */
	private String creator;

	/**
	 * vms设备唯一标识
	 */
	private String deviceId;

	/**
	 * 设备实际名称
	 */
	private String deviceName;

	/**
	 * 任务创建时间
	 */
	private Date createTime;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public String getDeviceName() {
		return deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Override
	public String toString() {
		return "AnalysisTaskInfo{" + "id='" + id + '\'' + ", type=" + type + ", status=" + status + ", creator='"
				+ creator + '\'' + ", deviceId='" + deviceId + '\'' + ", deviceName='" + deviceName + '\''
				+ ", createTime=" + createTime + '}';
	}
}
