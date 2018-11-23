/*
 * www.unisinsight.com Inc.
 * Copyright (c) 2018 All Rights Reserved
 */

package com.unisinsight.vdp.core.controller;

import com.unisinsight.framework.common.core.annotation.Log;
import com.unisinsight.framework.common.exception.BaseErrorCode;
import com.unisinsight.framework.common.exception.BaseException;
import com.unisinsight.vdp.common.vms.response.bean.ChannelDevice;
import com.unisinsight.vdp.common.vms.response.bean.DetailChannelDevice;
import com.unisinsight.vdp.common.vms.response.bean.ResourceTreeNode;
import com.unisinsight.vdp.core.common.utils.Constants;
import com.unisinsight.vdp.core.dto.request.ChannelResourceDTO;
import com.unisinsight.vdp.core.service.DeviceResourcesService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;

import javax.annotation.Resource;
import java.util.List;

/**
 * 设备资源控制器，用于获取调度vms设备资源
 * @author Li Gaoyang [li.gaoyang@unisinsight.com]
 * @date 2018-09-21 10:53:26
 * @since 1.0
 */
@RestController
@RequestMapping("/api/vdp/v1/core/vms")
public class DeviceResourcesController {

    /** 默认资源树类型 */
	private static final String DEFAULT_TREE_TYPE = "0,1";

	/** 获取设备资源树 */
	private static final Integer GET_TREE = 1;

	/** 获取设备资源树（含是否已创建任务的标识） */
	private static final Integer GET_FILTER_TREE = 2;

	/** 获取所有组织、设备所有节点(不含树形结构) */
	private static final Integer GET_NODES = 3;

	/** 调用资源树服务 */
	@Resource
	private DeviceResourcesService deviceResourcesService;

	/**
	 * 通过参数来控制得到不同的资源树类型
	 * @param unitType {@inheritDoc}
	 * @param category {@inheritDoc}
	 * @param functionType {@inheritDoc}
     * @param refresh {@inheritDoc}
	 * @return 获取资源树，由functionType控制类型
	 */
	@GetMapping("/resource-tree")
	@Log(module = Constants.LOGGER_MODEL, action = "获取设备资源树")
	public List<ResourceTreeNode> getResourceTree(@RequestParam(value = "unit_type", required = false) String unitType,
			@RequestParam(value = "category", required = false) String category,
			@RequestParam(value = "function_type", required = false) Integer functionType,
			@RequestParam(value = "refresh", required = false) boolean refresh) {
		unitType = StringUtils.isEmpty(unitType) ? DEFAULT_TREE_TYPE : unitType;

		// 获取资源树形结构
		if (null == functionType || GET_TREE.equals(functionType)) {
			return deviceResourcesService.getResourceTree(unitType, category, refresh);
		} else if (GET_FILTER_TREE.equals(functionType)) {
			// 获取设备资源树（含是否已创建任务的标识）
			return deviceResourcesService.getFilterResourceTree(unitType, category, refresh);
		} else if (GET_NODES.equals(functionType)) {
			// 获取所有组织、设备所有节点(不含树形结构)
			return deviceResourcesService.getResourceTreeNodes(unitType, category, refresh);
		}
		throw BaseException.of(BaseErrorCode.INVALID_PARAM_ERROR.getErrorCode(), "functionType参数传值错误");
	}

    /**
     * 根据设备id获取通道资源
     * @param  deviceId 设备id
     * @return 通道资源列表
     */
	@GetMapping("/channels/{id}")
	public List<ChannelDevice> getChannelsByDeviceId(@PathVariable(value = "id") String deviceId) {
		return deviceResourcesService.getChannelsByDeviceId(deviceId);
	}


	/**
	 * 获取通道资源
	 * @param channelResourceDTO
	 * @return
	 */
	@GetMapping("channels")
	public List<DetailChannelDevice> getChannelResources(ChannelResourceDTO channelResourceDTO){
		return deviceResourcesService.getChannelResources(channelResourceDTO);
	}




}
