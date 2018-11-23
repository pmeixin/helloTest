/*
 * www.unisinsight.com Inc.
 * Copyright (c) 2018 All Rights Reserved
 */
package com.unisinsight.vdp.core.dto.response;

import java.io.Serializable;
import java.util.Date;

/**
 * description
 *
 * @author daisike [dai.sike@unisinsight.com]
 * @date 2018/10/09 20:45
 * @since 1.0
 */
public class AnalysisTaskPageResDTO implements Serializable {

	private static final long serialVersionUID = -3882945979329589122L;

    /**
     * 任务唯一标识
     */
	private String id;

    /**
     * 任务类型
     */
	private Integer type;

    /**
     * 设备ID
     */
	private String deviceId;

    /**
     * 设备名称
     */
	private String deviceName;

    /**
     * 创建时间
     */
	private Date createTime;

    /**
     * 创建人
     */
	private String creator;

    /**
     * 任务状态
     */
	private Integer status;

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

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "AnalysisTaskPageResDTO{" + "id='" + id + '\'' + ", type=" + type + ", deviceId='" + deviceId + '\''
				+ ", deviceName='" + deviceName + '\'' + ", createTime=" + createTime + ", creator='" + creator + '\''
				+ ", status=" + status + '}';
	}
}
