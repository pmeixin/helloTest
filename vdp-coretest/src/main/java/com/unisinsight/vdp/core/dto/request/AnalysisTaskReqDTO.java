/*
 * www.unisinsight.com Inc.
 * Copyright (c) 2018 All Rights Reserved
 */
package com.unisinsight.vdp.core.dto.request;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.List;

/**
 * TODO description
 *
 * @author daisike [dai.sike@unisinsight.com]
 * @date 2018/10/09
 * @since 1.0
 */
public class AnalysisTaskReqDTO implements Serializable {

	private static final long serialVersionUID = 6422754517123887505L;

	/**
	 * 任务类型 0：图片流(人脸抓拍机) 1：视频流(普通IPC) 2:结构化
	 */
	@NotNull(message = "任务类型不能为空,取值为0,1,2,3")
	private Integer type;

	/**
	 * 任务创建者
	 */
	private String creator;

	@NotNull(message = "新建任务必须选择至少选择一个资源设备")
	@Size(min = 1, message = "新建任务必须选择至少选择一个资源设备")
	private List<Device> resList;

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public List<Device> getResList() {
		return resList;
	}

	public void setResList(List<Device> resList) {
		this.resList = resList;
	}

	@Override
	public String toString() {
		return "AnalysisTaskReqDTO{" + "type=" + type + ", creator='" + creator + '\'' + ", resList=" + resList + '}';
	}
}
