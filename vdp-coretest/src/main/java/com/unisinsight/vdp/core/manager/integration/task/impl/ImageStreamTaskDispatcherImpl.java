/*
 * www.unisinsight.com Inc.
 * Copyright (c) 2018 All Rights Reserved
 */
package com.unisinsight.vdp.core.manager.integration.task.impl;

import com.google.common.collect.Lists;
import com.unisinsight.framework.common.exception.RestException;
import com.unisinsight.vdp.common.constants.IAConstant;
import com.unisinsight.vdp.common.constants.MDAConstant;
import com.unisinsight.vdp.common.ia.request.IAImageTaskNewReq;
import com.unisinsight.vdp.common.ia.request.IATaskDeleteReq;
import com.unisinsight.vdp.common.ia.request.IATaskQueryReq;
import com.unisinsight.vdp.common.ia.response.IATaskDeleteRes;
import com.unisinsight.vdp.common.ia.response.IATaskNewRes;
import com.unisinsight.vdp.common.ia.response.IATaskQueryRes;
import com.unisinsight.vdp.common.mda.request.ImageTaskNewReq;
import com.unisinsight.vdp.common.mda.response.ImageTaskNewRes;
import com.unisinsight.vdp.core.common.enums.AnalysisTaskStatusEnum;
import com.unisinsight.vdp.core.common.enums.AnalysisTaskTypeEnum;

import com.unisinsight.vdp.core.manager.integration.task.AbstractTaskDispatcher;
import com.unisinsight.vdp.core.manager.integration.task.RestTemplateWrapper;
import com.unisinsight.vdp.core.model.AnalysisTask;
import org.apache.logging.log4j.util.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * description
 *
 * @author daisike [dai.sike@h3c.com]
 * @date 2018/11/03 10:03
 * @since 1.0
 */
@Component
public class ImageStreamTaskDispatcherImpl extends AbstractTaskDispatcher {

	private static final Logger logger = LoggerFactory.getLogger(ImageStreamTaskDispatcherImpl.class);

	private static final String DATE_FORMAT = "yyyyMMddHHmmss";

	private static final String END_DATE = "29991231235959";

	/**
	 * MDAConstant.FACE_IMAGE_TASK /mock/176/api/mda/v1/task
	 */
	private static final String MDA_IMAGE_RELATIVE_PATH = MDAConstant.FACE_IMAGE_TASK;

	@Value("${ia.url.face}")
	private String iaFaceUrl;

	@Value("ia.url.vehicle")
	private String iaVehicle;

	@Value("${mda.url}")
	private String mdaUrl;

	@Autowired
	private RestTemplateWrapper restTemplateWrapper;

	@Override
	public void create(List<AnalysisTask> analysisTaskList) {
		final Integer taskType = analysisTaskList.get(0).getType();
		final String taskMessage = AnalysisTaskTypeEnum.getByCode(taskType).message();
		analysisTaskList.forEach(analysisTask -> {
			// 1 查询算法配置
			Integer[] algorithmIdList = new Integer[1];
			algorithmIdList[0] = new Integer(10001);

			// 2 调用ia
			IAImageTaskNewReq iaImageTaskNewReq = new IAImageTaskNewReq();
			iaImageTaskNewReq.setCameraId(analysisTask.getDeviceId());
			iaImageTaskNewReq.setAlgorithmIdList(algorithmIdList);
			String url;

			/*// fixme mock 删除
			if (AnalysisTaskTypeEnum.IMAGE_STREAM.code().equals(taskType)) {
				// 人脸图片流
				relativePath = "/mock/166/api/iax/v1/face-engine/picture-process/task";
			} else {
				// 车辆二次分析
				relativePath = "/mock/161/api/iax/v1/vehicle-engine/picture-process/task";
			}*/

			if (AnalysisTaskTypeEnum.IMAGE_STREAM.code().equals(taskType)) {
				// 人脸图片流
				url = this.iaFaceUrl + IAConstant.FACE_IMAGE_TASK_CREATE;
			} else { // 车辆二次分析
				url = this.iaVehicle + IAConstant.VEHICLE_IMAGE_TASK_CREATE;
			}
			IATaskNewRes iaTaskNewRes = null;
			try {
				iaTaskNewRes = restTemplateWrapper.doPost(url, iaImageTaskNewReq, IATaskNewRes.class,
						"创建IA" + taskMessage);
			} catch (Exception e) {
				if (!(e instanceof RestException)) {
					logger.error("请求外部平台微服务【{}】出现未知异常, 异常信息:", taskMessage, e);
				}
				analysisTask.setStatus(AnalysisTaskStatusEnum.FAIL.code());
			}
			boolean callIASuccess = handleIATaskNewRes(iaTaskNewRes, analysisTask, taskMessage);

			// 3 成功后调用mda
			if (callIASuccess) {
				ImageTaskNewReq imageTaskNewReq = buildMDAReq(analysisTask);
				ImageTaskNewRes imageTaskNewRes = null;
				try {
					imageTaskNewRes = restTemplateWrapper.doPost(this.mdaUrl + MDA_IMAGE_RELATIVE_PATH, imageTaskNewReq,
							ImageTaskNewRes.class, "创建MDA" + taskMessage);
				} catch (Exception e) {
					analysisTask.setStatus(AnalysisTaskStatusEnum.FAIL.code());
				}

				String iaTaskId = analysisTask.getOuterTaskId();
				if (imageTaskNewRes != null && Strings.isNotBlank(imageTaskNewRes.getId())) {
					analysisTask.setOuterTaskId(iaTaskId + "," + imageTaskNewRes.getId());
					logger.info("调用外部微服务创建【{}】任务完成,outerId=[{}], 设备标识deviceId={},deviceName={}", taskMessage,
							analysisTask.getOuterTaskId(), analysisTask.getDeviceId(), analysisTask.getDeviceName());
				} else {
					logger.warn("调用[MDA]微服务创建【{}】任务失败,返回任务id为空, 设备标识deviceId={},deviceName={}", taskMessage,
							analysisTask.getDeviceId(), analysisTask.getDeviceName());
					analysisTask.setStatus(AnalysisTaskStatusEnum.FAIL.code());
				}
			}

		});

	}

	private ImageTaskNewReq buildMDAReq(AnalysisTask analysisTask) {
		ImageTaskNewReq imageTaskNewReq = new ImageTaskNewReq();
		imageTaskNewReq.setTollgateId(analysisTask.getDeviceId());
		if (AnalysisTaskTypeEnum.IMAGE_STREAM.code().equals(analysisTask.getType())) {
			imageTaskNewReq.setType(0);
		} else {
			imageTaskNewReq.setType(1);
		}
		SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
		imageTaskNewReq.setBeginTime(dateFormat.format(new Date()));
		imageTaskNewReq.setEndTime(END_DATE);
		return imageTaskNewReq;
	}

	@Override
	public AnalysisTaskStatusEnum query(Integer taskType, String outerId) {
		final String taskMessage = AnalysisTaskTypeEnum.getByCode(taskType).message();
		outerId = outerId.substring(0, outerId.indexOf(","));
		String url;

		// fixme mock 删除
		/*	if (AnalysisTaskTypeEnum.IMAGE_STREAM.code().equals(taskType)) {
				// 人脸图片流
				relativePath = "/mock/166/api/iax/v1/face-engine/picture-process/task/filter-query";
			} else {
				// 车辆二次分析
				relativePath = "/mock/161/api/iax/v1/vehicle-engine/picture-process/fileter-query";
			}*/

		if (AnalysisTaskTypeEnum.IMAGE_STREAM.code().equals(taskType)) {
			url = this.iaFaceUrl + IAConstant.FACE_IMAGE_TASK_QUERY;
		} else {
			url = this.iaVehicle + IAConstant.VEHICLE_IMAGE_TASK_QUERY;
		}

		IATaskQueryReq iaTaskQueryReq = buildIATaskQueryReq(outerId);
		IATaskQueryRes iaTaskQueryRes;
		try {
			iaTaskQueryRes = restTemplateWrapper.doPost(url, iaTaskQueryReq, IATaskQueryRes.class,
					"查询IA" + taskMessage);
		} catch (Exception e) {
			// 不用再做多余的打印，只需返回失败即可
			return AnalysisTaskStatusEnum.FAIL;
		}
		return handleIATaskQueryRes(iaTaskQueryRes, outerId, taskMessage);
	}

	@Override
	public Map<String, List<String>> delete(Integer taskType, List<String> outerIdList) {
		final String taskMessage = AnalysisTaskTypeEnum.getByCode(taskType).message();
		Map<String, List<String>> deleteResultMap = new HashMap<>(2);
		List<String> successList = Lists.newArrayList();
		List<String> failList = Lists.newArrayList();
		// 当前不提供批量接口 遍历调用删除
		for (String outerId : outerIdList) {
			String[] taskIds = outerId.split(",");
			String iaTaskId = taskIds[0];
			String mdaTaskId = taskIds[1];
			String url;

			/*// fixme mock 删除
			if (AnalysisTaskTypeEnum.IMAGE_STREAM.code().equals(taskType)) {
				// 人脸图片流
				relativePath = "/mock/166/api/iax/v1/face-engine/picture-process/task/batch-delete";
			} else {
				// 车辆二次分析
				relativePath = "/mock/161/api/iax/v1/vehicle-engine/picture-process/task/batch-delete";
			}
			*/
			if (AnalysisTaskTypeEnum.IMAGE_STREAM.code().equals(taskType)) {
				url = this.iaFaceUrl + IAConstant.FACE_IMAGE_TASK_QUERY;
			} else {
				url = this.iaVehicle + IAConstant.VEHICLE_IMAGE_TASK_QUERY;
			}

			IATaskDeleteReq iaTaskDeleteReq = new IATaskDeleteReq();
			String[] iaTaskIds = { iaTaskId };
			iaTaskDeleteReq.setTaskIdList(iaTaskIds);
			IATaskDeleteRes iaTaskDeleteRes = null;
			try {
				iaTaskDeleteRes = restTemplateWrapper.doPost(url, iaTaskDeleteReq, IATaskDeleteRes.class,
						"删除IA" + taskMessage);
			} catch (Exception e) {
				failList.add(outerId);
			}
			boolean deleteIATaskSuccess = (null != iaTaskDeleteRes && null != iaTaskDeleteRes.getSuccess()
					&& iaTaskDeleteRes.getSuccess().length > 0);
			if (deleteIATaskSuccess) {
				// 调用mda删除任务;
				restTemplateWrapper.doDelete(this.mdaUrl + MDA_IMAGE_RELATIVE_PATH + "/" + mdaTaskId,
						"删除MDA" + taskMessage);
				successList.add(outerId);
			}
		}
		deleteResultMap.put("success", successList);
		deleteResultMap.put("fail", failList);
		return deleteResultMap;
	}

}
