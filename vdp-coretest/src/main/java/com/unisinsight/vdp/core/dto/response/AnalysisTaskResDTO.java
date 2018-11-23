/*
 * www.unisinsight.com Inc.
 * Copyright (c) 2018 All Rights Reserved
 */
package com.unisinsight.vdp.core.dto.response;

import com.unisinsight.vdp.core.dto.response.bean.AnalysisTaskInfo;

import java.io.Serializable;
import java.util.List;

/**
 * TODO description
 *
 * @author daisike [dai.sike@unisinsight.com]
 * @date 2018/10/09
 * @since 1.0
 */
public class AnalysisTaskResDTO implements Serializable {

	/**
	 * 新增资源列表，若所选设备均已创建任务，则此列表返回为空
	 */
	private List<AnalysisTaskInfo> freshTasks;

	/**
	 * 选择了设备资源，而这些设备资源已存在任务，返回这些已经创建任务的列表。 若所选设备均未建任务，或所建任务均为成功，则此列表返回为空。
	 */
	private List<AnalysisTaskInfo> existedTasks;

	public List<AnalysisTaskInfo> getFreshTasks() {
		return freshTasks;
	}

	public void setFreshTasks(List<AnalysisTaskInfo> freshTasks) {
		this.freshTasks = freshTasks;
	}

	public List<AnalysisTaskInfo> getExistedTasks() {
		return existedTasks;
	}

	public void setExistedTasks(List<AnalysisTaskInfo> existedTasks) {
		this.existedTasks = existedTasks;
	}

	@Override
	public String toString() {
		return "AnalysisTaskResDTO{" + "freshTasks=" + freshTasks + ", existedTasks=" + existedTasks + '}';
	}
}
