/*
 * www.unisinsight.com Inc.
 * Copyright (c) 2018 All Rights Reserved
 */
package com.unisinsight.vdp.core.manager.integration.task;

import com.unisinsight.vdp.core.common.enums.AnalysisTaskStatusEnum;
import com.unisinsight.vdp.core.model.AnalysisTask;

import java.util.List;
import java.util.Map;

/**
 * description
 *
 * @author daisike [dai.sike@h3c.com]
 * @date 2018/11/03 10:35
 * @since 1.0
 */
public interface TaskDispatcher {

	/**
	 * 新建视频流（人脸视频流&视频结构化）任务
	 * 
	 * @param analysisTaskList
	 */
	void create(List<AnalysisTask> analysisTaskList);

	/**
	 * 查询IA视频流 任务
	 * 
	 * @param outerId
	 * @param taskType
	 * @return
	 */
	AnalysisTaskStatusEnum query(Integer taskType, String outerId);

	/**
	 * 删除IA视频流 任务
	 *
	 * @param outerIdList
	 * @param taskType
	 * @return
	 */
	Map<String, List<String>> delete(Integer taskType, List<String> outerIdList);

}
