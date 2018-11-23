/*
 * www.unisinsight.com Inc.
 * Copyright (c) 2018 All Rights Reserved
 */
package com.unisinsight.vdp.core.manager.integration.task;

import com.unisinsight.vdp.common.ia.inner.IATaskQueryResult;
import com.unisinsight.vdp.common.ia.inner.Page;
import com.unisinsight.vdp.common.ia.inner.Sort;
import com.unisinsight.vdp.common.ia.inner.TaskQueryCondition;
import com.unisinsight.vdp.common.ia.request.IATaskQueryReq;
import com.unisinsight.vdp.common.ia.response.IATaskNewRes;
import com.unisinsight.vdp.common.ia.response.IATaskQueryRes;
import com.unisinsight.vdp.core.common.enums.AnalysisTaskStatusEnum;
import com.unisinsight.vdp.core.enums.IATaskStatusEnum;
import com.unisinsight.vdp.core.model.AnalysisTask;
import org.apache.logging.log4j.util.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * description
 *
 * @author daisike [dai.sike@h3c.com]
 * @date 2018/11/06 16:07
 * @since 1.0
 */
public abstract class AbstractTaskDispatcher implements TaskDispatcher {

	private static final Logger logger = LoggerFactory.getLogger(AbstractTaskDispatcher.class);

	protected boolean handleIATaskNewRes(IATaskNewRes iaTaskNewRes, AnalysisTask analysisTask, String message) {
		if (null != iaTaskNewRes && Strings.isNotBlank(iaTaskNewRes.getTaskId())) {
			String externalId = iaTaskNewRes.getTaskId();
			analysisTask.setOuterTaskId(externalId);
			return true;
		}
		logger.warn("调用[IA]微服务创建【{}】解析任务【失败】,返回IA任务标识为空, 设备标识为【deviceId={},deviceName={}】", message,
				analysisTask.getDeviceId(), analysisTask.getDeviceName());
		analysisTask.setStatus(AnalysisTaskStatusEnum.FAIL.code());
		return false;

	}

	protected AnalysisTaskStatusEnum handleIATaskQueryRes(IATaskQueryRes iaTaskQueryRes, String outerId,
			String taskMessage) {

		boolean querySuccess = (null != iaTaskQueryRes && null != iaTaskQueryRes.getData()
				&& iaTaskQueryRes.getData().length > 0) && outerId.equals(iaTaskQueryRes.getData()[0].getTaskId());
		if (querySuccess) {
			String taskStatus = iaTaskQueryRes.getData()[0].getTaskStatus();
			IATaskStatusEnum iaTaskStatusEnum = IATaskStatusEnum.getByName(taskStatus);
			if (iaTaskStatusEnum != null) {
				logger.warn("调用[IA]微服务【{}】解析任务[{}]【完成】, 任务状态为:{}", taskMessage, outerId, taskStatus);
				return AnalysisTaskStatusEnum.getByCode(iaTaskStatusEnum.code());
			}
		}
		logger.warn("调用[IA]微服务【{}】解析任务[{}]失败或者为空,返回结果:{}", taskMessage, outerId, iaTaskQueryRes);
		return null;
	}

	protected IATaskQueryReq buildIATaskQueryReq(String outerId) {
		IATaskQueryReq iaTaskQueryReq = new IATaskQueryReq();

		Sort sort = new Sort();
		sort.setKey("task_id");
		sort.setValue("asc");
		iaTaskQueryReq.setSort(sort);

		Page page = new Page();
		page.setOffset(0);
		page.setLimit(1);
		page.setQueryCount(0);
		iaTaskQueryReq.setPage(page);

		List<TaskQueryCondition> queryConditions = new ArrayList<>(1);
		iaTaskQueryReq.setCondition(queryConditions);
		TaskQueryCondition condition = new TaskQueryCondition();
		queryConditions.add(condition);
		condition.setKey("task_id");
		condition.setOperator("equal");
		condition.setValue(outerId);
		return iaTaskQueryReq;
	}

}
