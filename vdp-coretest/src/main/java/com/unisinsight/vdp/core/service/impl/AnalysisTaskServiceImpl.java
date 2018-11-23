/*
 * www.unisinsight.com Inc.
 * Copyright (c) 2018 All Rights Reserved
 */
package com.unisinsight.vdp.core.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.google.common.collect.Lists;
import com.unisinsight.framework.common.base.PageResult;
import com.unisinsight.framework.common.utils.BeanCopyUtil;
import com.unisinsight.vdp.common.utils.IDUtils;
import com.unisinsight.vdp.core.common.config.NamedThreadFactory;
import com.unisinsight.vdp.core.common.enums.AnalysisTaskStatusEnum;

import com.unisinsight.vdp.core.common.enums.AnalysisTaskTypeEnum;
import com.unisinsight.vdp.core.dto.request.AnalysisTaskReqDTO;
import com.unisinsight.vdp.core.dto.request.Device;
import com.unisinsight.vdp.core.dto.response.AnalysisTaskBatchDeleteResDTO;
import com.unisinsight.vdp.core.dto.response.AnalysisTaskPageResDTO;
import com.unisinsight.vdp.core.dto.response.AnalysisTaskResDTO;
import com.unisinsight.vdp.core.dto.response.bean.AnalysisTaskInfo;

import com.unisinsight.vdp.core.manager.integration.task.TaskDispatcher;
import com.unisinsight.vdp.core.mapper.AnalysisTaskMapper;
import com.unisinsight.vdp.core.model.AnalysisTask;
import com.unisinsight.vdp.core.service.AnalysisTaskService;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.util.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Condition;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.*;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * TODO description
 *
 * @author daisike [dai.sike@unisinsight.com]
 * @date 2018/10/09
 * @since 1.0
 */
@Service
public class AnalysisTaskServiceImpl implements AnalysisTaskService {

	private static final Logger logger = LoggerFactory.getLogger(AnalysisTaskServiceImpl.class);

	private static final Integer DEFAULT_PAGE_NUM = 1;

	private static final Integer DEFAULT_PAGE_SIZE = 10;

	private static final ThreadPoolExecutor FACE_TASK_EXECUTOR = new ThreadPoolExecutor(5, 10, 60L, TimeUnit.SECONDS,
			new LinkedBlockingDeque<>(), new NamedThreadFactory("ANALYSIS-TASK-POOL"));

	@Resource
	private AnalysisTaskMapper analysisTaskMapper;

	@Resource(name = "videoStreamTaskDispatcherImpl")
	private TaskDispatcher videoStreamTaskDispatcher;

	@Resource(name = "imageStreamTaskDispatcherImpl")
	private TaskDispatcher imageStreamTaskDispatcher;

	@Override
	@Transactional(rollbackFor = Exception.class)
	public AnalysisTaskResDTO save(AnalysisTaskReqDTO reqDTO) {
		final Integer taskType = reqDTO.getType();
		List<AnalysisTask> hasSuccessList = Lists.newArrayList();
		List<AnalysisTask> requireNewTaskLst = Lists.newArrayList();

		// 数据入库
		saveTask(reqDTO, hasSuccessList, requireNewTaskLst, taskType);

		// 调用外部平台微服务
		feignInvoke(taskType, requireNewTaskLst);
		// 处理结果更新,构建返回数据
		return handleCreateRes(hasSuccessList, requireNewTaskLst, taskType);
	}

	@Override
	public PageResult queryList(Map<String, String> params) {
		Integer pageNo = (params.get("page_num") == null ? DEFAULT_PAGE_NUM : Integer.valueOf(params.get("page_num")));
		Integer pageSize = (params.get("page_size") == null ? DEFAULT_PAGE_SIZE
				: Integer.valueOf(params.get("page_size")));

		Condition condition = buildQueryParams(params, AnalysisTask.class);

		Page page = PageHelper.startPage(pageNo, pageSize);
		List<AnalysisTask> analysisTaskList = analysisTaskMapper.selectByCondition(condition);
		analysisTaskList.forEach(analysisTask -> {
			if (Strings.isNotBlank(analysisTask.getOuterTaskId())) {
				// 调用下层查询任务状态
				AnalysisTaskStatusEnum taskStatus;
				Integer preStatus = analysisTask.getStatus();
				if (AnalysisTaskTypeEnum.IMAGE_STREAM.code().equals(analysisTask.getType())
						|| AnalysisTaskTypeEnum.VEHICLE_IMAGE_STREAM.code().equals(analysisTask.getType())) {
					// 调用MDA图片流查询服务
					taskStatus = imageStreamTaskDispatcher.query(analysisTask.getType(), analysisTask.getOuterTaskId());
				} else {
					// 调用MDA图片流查询服务
					taskStatus = videoStreamTaskDispatcher.query(analysisTask.getType(), analysisTask.getOuterTaskId());
				}
				if (null != taskStatus) {
					if (!preStatus.equals(taskStatus.code())) {
						analysisTask.setStatus(taskStatus.code());
						analysisTaskMapper.updateByPrimaryKeySelective(analysisTask);
						logger.info("更新【{}】任务状态,[{}]->[{}],taskId={},deviceId={}",
								AnalysisTaskTypeEnum.getByCode(analysisTask.getType()).message(),
								AnalysisTaskStatusEnum.getByCode(preStatus).message(),
								AnalysisTaskStatusEnum.getByCode(analysisTask.getStatus()).message(),
								analysisTask.getDeviceId(), analysisTask.getDeviceId());
					}
				}

			}
		});

		List<AnalysisTaskPageResDTO> resDTOList = BeanCopyUtil.convertList(analysisTaskList,
				AnalysisTaskPageResDTO.class);
		return PageResult.of(resDTOList, page);
	}

	@Override
	public AnalysisTaskBatchDeleteResDTO deleteList(List<String> taskIds, Integer taskType) {
		AnalysisTaskBatchDeleteResDTO batchDeleteResDTO = new AnalysisTaskBatchDeleteResDTO();
		Condition condition = new Condition(AnalysisTask.class);
		condition.createCriteria().andEqualTo("type", taskType).andIn("id", taskIds);
		List<AnalysisTask> analysisTaskList = analysisTaskMapper.selectByCondition(condition);
		if (null != analysisTaskList && analysisTaskList.size() > 0) {
			List<String> outerIdList = Lists.newArrayList();
			analysisTaskList.forEach(analysisTask -> {
				if (Strings.isNotBlank(analysisTask.getOuterTaskId())) {
					outerIdList.add(analysisTask.getOuterTaskId());
				}
			});
			if (outerIdList.size() > 0) {
				Map<String, List<String>> iaDeleteResult;
				if (AnalysisTaskTypeEnum.IMAGE_STREAM.code().equals(taskType)
						|| AnalysisTaskTypeEnum.VEHICLE_IMAGE_STREAM.code().equals(taskType)) {
					// 人脸图片流、车辆图片流
					iaDeleteResult = imageStreamTaskDispatcher.delete(taskType, outerIdList);
				} else {
					// 人脸视频流调用IA完成删除
					iaDeleteResult = videoStreamTaskDispatcher.delete(taskType, outerIdList);

				}
				List<String> successOuterIdList = iaDeleteResult.get("success");
				List<String> failOuterIdList = iaDeleteResult.get("fail");
				if (null != failOuterIdList && failOuterIdList.size() > 0) {
					outerIdList.removeAll(failOuterIdList);
					logger.info("调用外部微服务批量删除【{}】任务成功列表:[{}],失败列表:[{}]",
							AnalysisTaskTypeEnum.getByCode(taskType).message(), successOuterIdList, failOuterIdList);
				}
			}
			List<String> deleteList = new ArrayList<>(analysisTaskList.size());
			List<String> failList = new ArrayList<>();
			analysisTaskList.forEach(analysisTask -> {
				String outerTaskId = analysisTask.getOuterTaskId();
				if (Strings.isBlank(outerTaskId) || outerIdList.contains(outerTaskId)) {
					deleteList.add(analysisTask.getId());
				} else {
					failList.add(analysisTask.getId());
				}
			});

			if (deleteList.size() > 0) {
				Condition deleteCondition = new Condition(AnalysisTask.class);
				deleteCondition.createCriteria().andIn("id", deleteList);
				analysisTaskMapper.deleteByCondition(deleteCondition);
			}
			batchDeleteResDTO.setSuccessIds(deleteList);
			batchDeleteResDTO.setFailIds(failList);

		}
		return batchDeleteResDTO;
	}

	@Override
	public List<String> getCreatedDeviceList() {
		return analysisTaskMapper.getCreatedDevices();
	}

	private Condition buildQueryParams(Map<String, String> params, Class<?> entity) {
		String startCreateTime = params.get("start_create_time");
		String endCreateTime = params.get("end_create_time");
		String deviceName = params.get("device_name");
		String status = params.get("status");
		Condition condition = new Condition(entity);
		Example.Criteria criteria = condition.createCriteria();
		criteria.andEqualTo("type", Integer.parseInt(params.get("type")));

		if (StringUtils.isNotEmpty(startCreateTime) && StringUtils.isEmpty(endCreateTime)) {
			criteria.andGreaterThanOrEqualTo("createTime", new Date(Long.parseLong(startCreateTime)));
		} else if (StringUtils.isNotEmpty(startCreateTime) && StringUtils.isNotEmpty(endCreateTime)) {
			criteria.andBetween("createTime", new Date(Long.parseLong(startCreateTime)),
					new Date(Long.parseLong(endCreateTime)));
		} else if (StringUtils.isEmpty(startCreateTime) && StringUtils.isNotEmpty(endCreateTime)) {
			criteria.andLessThanOrEqualTo("createTime", new Date(Long.parseLong(endCreateTime)));
		}
		if (StringUtils.isNotEmpty(deviceName)) {
			criteria.andLike("deviceName", "%" + deviceName + "%");
		}

		if (StringUtils.isNotEmpty(status)) {
			criteria.andEqualTo("status", Integer.parseInt(status));
		}

		return condition;
	}

	private AnalysisTaskResDTO handleCreateRes(List<AnalysisTask> hasSuccessList, List<AnalysisTask> requireNewTaskLst,
			Integer taskType) {
		AnalysisTaskResDTO resDTO = new AnalysisTaskResDTO();
		if (hasSuccessList.size() > 0) {
			List<AnalysisTaskInfo> existedTasks = new ArrayList<>(hasSuccessList.size());
			hasSuccessList.forEach(hasSuccessDevice -> {
				AnalysisTaskInfo analysisTaskInfo = new AnalysisTaskInfo();
				BeanUtils.copyProperties(hasSuccessDevice, analysisTaskInfo);
				existedTasks.add(analysisTaskInfo);
			});
			resDTO.setExistedTasks(existedTasks);
		}
		if (requireNewTaskLst.size() > 0) {
			List<AnalysisTaskInfo> freshTasks = new ArrayList<>(requireNewTaskLst.size());
			requireNewTaskLst.forEach(finishedTask -> {
				AnalysisTaskInfo analysisTaskInfo = new AnalysisTaskInfo();
				BeanUtils.copyProperties(finishedTask, analysisTaskInfo);
				freshTasks.add(analysisTaskInfo);
			});
			resDTO.setFreshTasks(freshTasks);
		}
		logger.info("完成【{}】解析任务创建,{}", AnalysisTaskTypeEnum.getByCode(taskType).message(), resDTO);
		return resDTO;
	}

	private void feignInvoke(Integer taskType, List<AnalysisTask> requireNewTaskLst) {
		if (requireNewTaskLst.size() > 0) {
			// 线程池 执行依赖微服务调用
			FACE_TASK_EXECUTOR.execute(() -> {
				// 图片流
				if (AnalysisTaskTypeEnum.IMAGE_STREAM.code().equals(taskType)
						|| AnalysisTaskTypeEnum.VEHICLE_IMAGE_STREAM.code().equals(taskType)) {
					// 人脸图片流 & 车辆实时视频流 调用MDA服务创建图片流任务
					imageStreamTaskDispatcher.create(requireNewTaskLst);
				} else {
					// 人脸视频流&视频结构化 调用IA解析平台创建任务
					videoStreamTaskDispatcher.create(requireNewTaskLst);
				}
				// 更新任务状态
				requireNewTaskLst.forEach(finishedTask -> analysisTaskMapper.updateByPrimaryKeySelective(finishedTask));
			});
		}
	}

	private void saveTask(AnalysisTaskReqDTO reqDTO, List<AnalysisTask> hasSuccessList,
			List<AnalysisTask> requireNewTaskLst, Integer taskType) {

		logger.info("新建【{}】解析任务内容:{}", AnalysisTaskTypeEnum.getByCode(taskType).message(), reqDTO);

		List<Device> deviceList = reqDTO.getResList();

		if (null != deviceList && deviceList.size() > 0) {
			deviceList.forEach(device -> {
				AnalysisTask analysisTask = new AnalysisTask();
				BeanUtils.copyProperties(reqDTO, analysisTask);
				analysisTask.setId(IDUtils.newID());
				// 设置状态为 创建中
				analysisTask.setStatus(AnalysisTaskStatusEnum.PROCESSING.code());
				String deviceId = device.getDeviceId();
				analysisTask.setDeviceId(deviceId);
				analysisTask.setDeviceName(device.getDeviceName());

				// 查询设备是否已经有设备(成功或失败)任务存在，以后将由redis替换
				AnalysisTask existedTask = analysisTaskMapper.queryExistedTask(deviceId, taskType);

				// 记录已存在，且状态为成功或处理中任务
				if (null != existedTask) {
					hasSuccessList.add(existedTask);
					logger.warn("设备【{},{}】'已成功'或者'正在'创建{}解析任务，不再重复创建", existedTask.getDeviceId(),
							existedTask.getDeviceName(), AnalysisTaskTypeEnum.getByCode(taskType).message());
				} else {
					// 记录并不存在或状态失败
					analysisTask.setCreateTime(new Date());
					analysisTaskMapper.insertSelective(analysisTask);
					requireNewTaskLst.add(analysisTask);
				}
			});
		}
	}

}
