/*
 * www.unisinsight.com Inc.
 * Copyright (c) 2018 All Rights Reserved
 */
package com.unisinsight.vdp.core.manager.integration.task.impl;

import com.google.common.collect.Lists;
import com.unisinsight.framework.common.exception.BaseException;
import com.unisinsight.vdp.common.constants.IAConstant;
import com.unisinsight.vdp.common.ia.inner.FailInfo;
import com.unisinsight.vdp.common.ia.inner.SourcePram;
import com.unisinsight.vdp.common.ia.request.*;
import com.unisinsight.vdp.common.ia.response.IATaskDeleteRes;
import com.unisinsight.vdp.common.ia.response.IATaskNewRes;
import com.unisinsight.vdp.common.ia.response.IATaskQueryRes;
import com.unisinsight.vdp.common.mda.response.PicStorageConfigurationResDTO;
import com.unisinsight.vdp.core.common.enums.AnalysisTaskStatusEnum;
import com.unisinsight.vdp.core.common.enums.AnalysisTaskTypeEnum;
import com.unisinsight.vdp.core.enums.TaskErrorCode;
import com.unisinsight.vdp.core.manager.integration.task.AbstractTaskDispatcher;
import com.unisinsight.vdp.core.manager.integration.task.RestTemplateWrapper;
import com.unisinsight.vdp.core.manager.integration.vms.VmsService;
import com.unisinsight.vdp.core.model.AnalysisTask;
import com.unisinsight.vdp.core.model.PicStorageConfiguration;
import com.unisinsight.vdp.core.service.PicStorageConfigurationService;
import org.apache.logging.log4j.util.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * description
 *
 * @author daisike [dai.sike@h3c.com]
 * @date 2018/11/03 10:36
 * @since 1.0
 */
@Component
public class VideoStreamTaskDispatcherImpl extends AbstractTaskDispatcher {

	private static final Logger logger = LoggerFactory.getLogger(VideoStreamTaskDispatcherImpl.class);

	@Value("${ia.url.structure}")
	private String iaStructureUrl;

	@Value("${ia.url.face}")
	private String iaFaceUrl;

	@Autowired
	private VmsService vmsService;

	@Autowired
	private PicStorageConfigurationService storageService;

	@Autowired
	private RestTemplateWrapper restTemplateWrapper;

	@Override
	public void create(List<AnalysisTask> analysisTaskList) {

		final Integer taskType = analysisTaskList.get(0).getType();
		final String taskMessage = "创建" + AnalysisTaskTypeEnum.getByCode(taskType).message();
		analysisTaskList.forEach(analysisTask -> {

			// 1调用vms平台获取实时流播放地址
			// String realPlayUrl = vmsService.getRealPlayUrl(analysisTask.getDeviceId());
			// fixme
			String realPlayUrl = "rtsp://admin:admin@192.168.4.59:554";
			if (Strings.isBlank(realPlayUrl)) {
				analysisTask.setStatus(AnalysisTaskStatusEnum.FAIL.code());
			} else {

				// 2从数据库数据库获取存储配置
				List<PicStorageConfigurationResDTO> storageConfigList = getStorageConfig(taskType);

				// 3查询算法配置
				Integer[] algorithmIdList = null;
				algorithmIdList = new Integer[3];
				algorithmIdList[0] = 1;
				algorithmIdList[1] = 2;
				algorithmIdList[2] = 3;

				// 4调用IA创建视频解析任务
				IATaskNewBaseReq iaTaskNewReq = buildIATaskNewReq(analysisTask.getDeviceId(), realPlayUrl,
						storageConfigList, algorithmIdList, taskType);
				/*String url = AnalysisTaskTypeEnum.VIDEO_STREAM.code().equals(taskType)
						? this.iaFaceUrl + "/mock/166/api/iax/v1/face-engine/video-process/task"
						: this.iaStructureUrl + "/mock/156/api/iax/v1/structure-engine/video-process/task";*/
				String url = AnalysisTaskTypeEnum.VIDEO_STREAM.code().equals(taskType)
						? this.iaFaceUrl + IAConstant.FACE_VIDEO_TASK_CREATE
						: this.iaStructureUrl + IAConstant.VIDEO_STRUCTURE_TASK_CREATE;

				IATaskNewRes iaTaskNewRes = null;
				try {
					iaTaskNewRes = restTemplateWrapper.doPost(url, iaTaskNewReq, IATaskNewRes.class, taskMessage);
				} catch (Exception e) {
					analysisTask.setStatus(AnalysisTaskStatusEnum.FAIL.code());
				}
				handleIATaskNewRes(iaTaskNewRes, analysisTask, taskMessage);
			}
		});

	}

	private List<PicStorageConfigurationResDTO> getStorageConfig(Integer taskType) {
		// 1 人脸-场景图；2 人脸-特写图；3车辆-场景图 4 车辆-特写图 5 人员-场景图 6 人员-特写图
		/*	List<Integer> configTypeList = Lists.newArrayList();
			if (AnalysisTaskTypeEnum.VIDEO_STREAM.code().equals(taskType)) {
				configTypeList.add(1);
				configTypeList.add(2);
			} else {
				configTypeList.add(3);
				configTypeList.add(4);
				configTypeList.add(5);
				configTypeList.add(6);
			}
			return storageService.getConfigsByTypes(configTypeList);*/

		List<Integer> configTypeList = Lists.newArrayList();
		if (AnalysisTaskTypeEnum.VIDEO_STREAM.code().equals(taskType)) {
			List<PicStorageConfigurationResDTO> configurationResDTOS = new ArrayList<>();
			PicStorageConfigurationResDTO p1 = new PicStorageConfigurationResDTO();
			configurationResDTOS.add(p1);
			p1.setDataType(1);
			p1.setStorageIp("192.168.135.46");
			p1.setStoragePort(9001);
			p1.setStoragePath("aix_storage_root_fvs");

			PicStorageConfigurationResDTO p2 = new PicStorageConfigurationResDTO();
			configurationResDTOS.add(p2);
			p2.setDataType(2);
			p2.setStorageIp("192.168.135.46");
			p2.setStoragePort(9001);
			p2.setStoragePath("aix_fvs_pic_root_dir");
			return configurationResDTOS;
		} else {
			List<PicStorageConfigurationResDTO> configurationResDTOS = new ArrayList<>();
			PicStorageConfigurationResDTO p1 = new PicStorageConfigurationResDTO();
			configurationResDTOS.add(p1);
			p1.setDataType(3);
			p1.setStorageIp("192.168.135.46");
			p1.setStoragePort(9001);
			p1.setStoragePath("aix_storage_root_fvs");

			PicStorageConfigurationResDTO p2 = new PicStorageConfigurationResDTO();
			configurationResDTOS.add(p2);
			p2.setDataType(4);
			p2.setStorageIp("192.168.135.46");
			p2.setStoragePort(9001);
			p2.setStoragePath("aix_fvs_pic_root_dir");

			PicStorageConfigurationResDTO p3 = new PicStorageConfigurationResDTO();
			configurationResDTOS.add(p3);
			p3.setDataType(5);
			p3.setStorageIp("192.168.135.46");
			p3.setStoragePort(9001);
			p3.setStoragePath("aix_fvs_pic_root_dir");

			PicStorageConfigurationResDTO p4 = new PicStorageConfigurationResDTO();
			configurationResDTOS.add(p4);
			p4.setDataType(6);
			p4.setStorageIp("192.168.135.46");
			p4.setStoragePort(9001);
			p4.setStoragePath("aix_fvs_pic_root_dir");
			return configurationResDTOS;
		}

	}

	@Override
	public AnalysisTaskStatusEnum query(Integer taskType, String outerId) {

		final String taskMessage = "查询" + AnalysisTaskTypeEnum.getByCode(taskType).message();

		// fixme mock地址 remove
		/*String url = AnalysisTaskTypeEnum.VIDEO_STREAM.code().equals(taskType)
				? this.iaFaceUrl + "/mock/166/api/iax/v1/face-engine/video-process/task/filter-query"
				: this.iaStructureUrl + "/mock/156/api/iax/v1/structure-engine/video-process/task/filter-query";*/
		String url = AnalysisTaskTypeEnum.VIDEO_STREAM.code().equals(taskType)
				? this.iaFaceUrl + IAConstant.FACE_VIDEO_TASK_QUERY
				: this.iaStructureUrl + IAConstant.VIDEO_STRUCTURE_TASK_QUERY;

		IATaskQueryReq iaTaskQueryReq = buildIATaskQueryReq(outerId);
		IATaskQueryRes iaTaskQueryRes;
		try {
			iaTaskQueryRes = restTemplateWrapper.doPost(url, iaTaskQueryReq, IATaskQueryRes.class, taskMessage);
		} catch (Exception e) {
			return AnalysisTaskStatusEnum.FAIL;
		}

		return handleIATaskQueryRes(iaTaskQueryRes, outerId, taskMessage);
	}

	@Override
	public Map<String, List<String>> delete(Integer taskType, List<String> outerIdList) {

		final String taskMessage = AnalysisTaskTypeEnum.getByCode(taskType).message();
		IATaskDeleteReq iaTaskDeleteReq = new IATaskDeleteReq();
		String[] outerIds = new String[outerIdList.size()];
		iaTaskDeleteReq.setTaskIdList(outerIdList.toArray(outerIds));
		IATaskDeleteRes iaTaskDeleteRes;

		String url = AnalysisTaskTypeEnum.VIDEO_STREAM.code().equals(taskType)
				? this.iaFaceUrl + IAConstant.FACE_VIDEO_TASK_DELETE
				: this.iaStructureUrl + IAConstant.VIDEO_STRUCTURE_TASK_DELETE;

		// fixme mock地址 remove
		/*String url = AnalysisTaskTypeEnum.VIDEO_STREAM.code().equals(taskType)
				? this.iaFaceUrl + "/mock/166/api/iax/v1/face-engine/video-process/task/batch-delete"
				: this.iaStructureUrl + "/mock/156/api/iax/v1/structure-engine/video-process/task/batch-delete";*/
		try {
			iaTaskDeleteRes = restTemplateWrapper.doPost(url, iaTaskDeleteReq, IATaskDeleteRes.class, taskMessage);
		} catch (Exception e) {
			throw BaseException.of(TaskErrorCode.TASK_DELETE_FAIL.of());
		}

		HashMap<String, List<String>> iaResult = new HashMap<>(2);
		// 数组长度不会变化 这里可用Arrays.asList
		if (null != iaTaskDeleteRes.getSuccess() && iaTaskDeleteRes.getSuccess().length > 0) {
			List<String> successList = Arrays.asList(iaTaskDeleteRes.getSuccess());
			iaResult.put("success", successList);
		}
		if (null != iaTaskDeleteRes.getFail() && iaTaskDeleteRes.getFail().length > 0) {
			List<String> failList = new ArrayList<>(iaTaskDeleteRes.getFail().length);
			for (FailInfo failInfo : iaTaskDeleteRes.getFail()) {
				failList.add(failInfo.getTaskId());
				logger.warn("调用[IA]微服务批量删除【{}】解析任务[{}]失败,失败信息:[{},{}]", taskMessage, failInfo.getTaskId(),
						failInfo.getErrorCode(), failInfo.getMessage());
			}
			iaResult.put("fail", failList);
		}
		return iaResult;

	}

	private IATaskNewBaseReq buildIATaskNewReq(String deviceId, String realPlayUrl,
			List<PicStorageConfigurationResDTO> storageConfigList, Integer[] algorithmIdList, Integer taskType) {
		IATaskNewBaseReq iaTaskNewReq = AnalysisTaskTypeEnum.VIDEO_STREAM.code().equals(taskType)
				? new IAFaceVideoTaskReq()
				: new IAStructureVideoTaskReq();

		// 1 人脸-场景图；2 人脸-特写图；3车辆-场景图 4 车辆-特写图 5 人员-场景图 6 人员-特写图
		for (PicStorageConfigurationResDTO config : storageConfigList) {
			switch (config.getDataType()) {
				case 1:
					((IAFaceVideoTaskReq) iaTaskNewReq).setStorageFullPicPath(config.getStoragePath());
					break;
				case 2:
					((IAFaceVideoTaskReq) iaTaskNewReq).setStoragePartPicPath(config.getStoragePath());
					break;
				case 3:
					((IAStructureVideoTaskReq) iaTaskNewReq).setStorageVehicleFullPicPath(config.getStoragePath());
					break;
				case 4:
					((IAStructureVideoTaskReq) iaTaskNewReq).setStorageVehiclePartPicPath(config.getStoragePath());
					break;
				case 5:
					((IAStructureVideoTaskReq) iaTaskNewReq).setStoragePersonFullPicPath(config.getStoragePath());
					break;
				case 6:
					((IAStructureVideoTaskReq) iaTaskNewReq).setStoragePersonPartPicPath(config.getStoragePath());
					break;
				default:
			}
		}
		iaTaskNewReq.setStorageIp(storageConfigList.get(0).getStorageIp());
		iaTaskNewReq.setStoragePort(storageConfigList.get(0).getStoragePort());
		iaTaskNewReq.setCameraId(deviceId);
		iaTaskNewReq.setSourceType("rtsp");
		SourcePram sourcePram = new SourcePram();
		sourcePram.setUrl(realPlayUrl);
		iaTaskNewReq.setSourceParameter(sourcePram);
		iaTaskNewReq.setAlgorithmIdList(algorithmIdList);
		return iaTaskNewReq;
	}
}
