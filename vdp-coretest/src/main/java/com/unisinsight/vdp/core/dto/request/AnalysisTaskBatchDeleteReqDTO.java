/*
 * www.unisinsight.com Inc.
 * Copyright (c) 2018 All Rights Reserved
 */
package com.unisinsight.vdp.core.dto.request;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

/**
 * description
 *
 * @author daisike [dai.sike@unisinsight.com]
 * @date 2018/10/11 15:30
 * @since 1.0
 */
public class AnalysisTaskBatchDeleteReqDTO implements Serializable {

	private static final long serialVersionUID = 8737220038114829996L;

    /**
     * 任务id列表
     */
	@NotNull(message = "批量删除任务至少传入一个任务id")
	@Size(min = 1, message = "批量删除任务至少传入一个任务id")
	private List<String> ids;

	/**
	 * 任务类型 0：图片流(人脸抓拍机) 1：视频流(普通IPC) 2:结构化  3:车辆实时图片流
	 */
	@NotNull(message = "任务类型不能为空，取值为0,1,2,3")
	private Integer type;

	public List<String> getIds() {
		return ids;
	}

	public void setIds(List<String> ids) {
		this.ids = ids;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
}
