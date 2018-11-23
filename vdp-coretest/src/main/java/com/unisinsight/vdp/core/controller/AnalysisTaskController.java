/*
 * www.unisinsight.com Inc.
 * Copyright (c) 2018 All Rights Reserved
 */
package com.unisinsight.vdp.core.controller;

import com.unisinsight.framework.common.base.PageResult;
import com.unisinsight.framework.common.core.annotation.Log;
import com.unisinsight.framework.common.exception.BaseErrorCode;
import com.unisinsight.framework.common.exception.BaseException;
import com.unisinsight.framework.common.utils.User;
import com.unisinsight.framework.common.utils.UserHandler;
import com.unisinsight.vdp.core.common.enums.AnalysisTaskTypeEnum;
import com.unisinsight.vdp.core.common.utils.Constants;
import com.unisinsight.vdp.core.dto.request.AnalysisTaskBatchDeleteReqDTO;
import com.unisinsight.vdp.core.dto.request.AnalysisTaskReqDTO;
import com.unisinsight.vdp.core.dto.response.AnalysisTaskBatchDeleteResDTO;
import com.unisinsight.vdp.core.dto.response.AnalysisTaskResDTO;
import com.unisinsight.vdp.core.service.AnalysisTaskService;
import org.apache.logging.log4j.util.Strings;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.Map;

/**
 * 解析任务配置
 *
 * @author daisike [dai.sike@unisinsight.com]
 * @date 2018/10/09
 * @since 1.0
 */
@RestController
@RequestMapping("/api/vdp/v1/core/analysis-task")
public class AnalysisTaskController {

	/**
	 * 解析任务逻辑处理注入
	 */
	@Resource
	private AnalysisTaskService analysisTaskService;

	/**
	 * 新增解析任务
	 * 
	 * @param analysisTaskReqDTO
	 * @return AnalysisTaskResDTO
	 */
	@PostMapping
	@Log(module = Constants.LOGGER_MODEL, action = "解析任务新增")
	public AnalysisTaskResDTO add(@RequestBody @Valid AnalysisTaskReqDTO analysisTaskReqDTO) {
		User user = UserHandler.getUser();
		if (null != user) {
			analysisTaskReqDTO.setCreator(user.getUserName());
		}
		Integer taskType = analysisTaskReqDTO.getType();
		if (null == AnalysisTaskTypeEnum.getByCode(taskType)) {
			throw BaseException.of(BaseErrorCode.INVALID_PARAM_ERROR.of(), "type必填,0:人脸图片流,1:人脸视频流,2:视频结构化,3:车辆实时图片流");
		}
		return analysisTaskService.save(analysisTaskReqDTO);
	}

	/**
	 * 批量删除解析任务（包含单个删除)
	 * 
	 * @param batchDeleteReqDTO
	 * @return AnalysisTaskBatchDeleteResDTO
	 */
	@PostMapping("/batch")
	@Log(module = Constants.LOGGER_MODEL, action = "解析任务批量删除")
	public AnalysisTaskBatchDeleteResDTO deleteList(@RequestBody @Valid AnalysisTaskBatchDeleteReqDTO batchDeleteReqDTO) {

		Integer taskType = batchDeleteReqDTO.getType();
		if (null == AnalysisTaskTypeEnum.getByCode(taskType)) {
			throw BaseException.of(BaseErrorCode.INVALID_PARAM_ERROR.of(), "type必填,0:人脸图片流,1:人脸视频流,2:视频结构化,4:车辆实时图片流");
		}
		return analysisTaskService.deleteList(batchDeleteReqDTO.getIds(), taskType);
	}

	/**
	 * 解析任务分页查询
	 * 
	 * @param params
	 * @return PageResult
	 */
	@GetMapping()
	@Log(module = Constants.LOGGER_MODEL, action = "解析任务列表查询")
	public PageResult queryList(@RequestParam Map<String, String> params) {
		String taskType = params.get("type");
		if (Strings.isBlank(taskType)) {
			throw BaseException.of(BaseErrorCode.INVALID_PARAM_ERROR.of(), "type必填,0:人脸图片流,1:人脸视频流,2:视频结构化,3:车俩实时图片流");
		}
		return analysisTaskService.queryList(params);
	}

}
