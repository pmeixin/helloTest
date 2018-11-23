/*
 * www.unisinsight.com Inc.
 * Copyright (c) 2018 All Rights Reserved
 */
package com.unisinsight.vdp.core.controller;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;

import com.google.common.collect.Maps;
import com.unisinsight.framework.common.base.PageResult;
import com.unisinsight.vdp.core.BaseTest;
import com.unisinsight.vdp.core.dto.request.Device;
import com.unisinsight.vdp.core.dto.request.AnalysisTaskReqDTO;
import com.unisinsight.vdp.core.dto.response.AnalysisTaskResDTO;
import com.unisinsight.vdp.core.manager.integration.vms.VmsService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import javax.annotation.Resource;

import java.util.List;
import java.util.Map;

/**
 * description
 *
 * @author daisike [dai.sike@h3c.com]
 * @date 2018/09/19 11:57
 * @since 1.0
 */
public class AnalysisTaskControllerTest extends BaseTest{

	@Resource
	private AnalysisTaskController AnalysisTaskController;

	@Autowired
	private VmsService vmsService;

	@Test
	public void testAddFaceImageTask() {

		AnalysisTaskReqDTO analysisTaskReqDTO = new AnalysisTaskReqDTO();
		analysisTaskReqDTO.setType(0);
		analysisTaskReqDTO.setCreator("zhangsan");
		List<Device> deviceList = Lists.newArrayList();

		Device device = new Device();
		device.setDeviceId("1000000");
		device.setDeviceName("人脸卡口抓拍机");
		deviceList.add(device);

		analysisTaskReqDTO.setResList(deviceList);
		System.out.println(JSON.toJSONString(analysisTaskReqDTO));
		AnalysisTaskResDTO AnalysisTaskResDTO = AnalysisTaskController.add(analysisTaskReqDTO);
		System.out.println(JSON.toJSONString(AnalysisTaskResDTO));

	}

	@Test
	public void testAddFaceVideoTask() {
		AnalysisTaskReqDTO analysisTaskReqDTO = new AnalysisTaskReqDTO();
		analysisTaskReqDTO.setType(1);
		analysisTaskReqDTO.setCreator("lisi");
		List<Device> deviceList = Lists.newArrayList();

		Device device = new Device();
		device.setDeviceId("1000005$1$0$0");
		device.setDeviceName("大华重庆IPC10607_1");
		deviceList.add(device);

		analysisTaskReqDTO.setResList(deviceList);
		String s = JSON.toJSONString(analysisTaskReqDTO);
		System.out.println(JSON.toJSONString(analysisTaskReqDTO));
		AnalysisTaskResDTO AnalysisTaskResDTO = AnalysisTaskController.add(analysisTaskReqDTO);
		System.out.println(JSON.toJSONString(AnalysisTaskResDTO));

	}



	@Test
	public void testQueryPage() {
		System.out.println(JSON.toJSONString(AnalysisTaskController.queryList(queryParams())));
	}

	@Test
	public void TestBatchDelete() {
		//AnalysisTaskController.deleteList(null);

	}

	private Map<String, String> queryParams() {
		Map<String, String> params = Maps.newHashMap();
		params.put("page_num", "1");
		params.put("page_size", "3");
		params.put("device_name", "高科");
		params.put("start_create_time", "1537520407000");
		// params.put("end_create_time", "1537866581000");
		// params.put("status","1");
		params.put("type", "0");
		return params;
	}

	@Test
	public void test(){
		String s = vmsService.getRealPlayUrl("");
		System.out.println(s);
	}

}
