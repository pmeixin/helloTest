/*
 * www.unisinsight.com Inc.
 * Copyright (c) 2018 All Rights Reserved
 */
package com.unisinsight.vdp.core.redis;

import com.unisinsight.vdp.common.vms.response.bean.ResourceTreeNode;
import com.unisinsight.vdp.core.BaseTest;
import com.unisinsight.vdp.core.common.utils.RedisUtils;
import com.unisinsight.vdp.core.service.DeviceResourcesService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * description
 *
 * @author daisike [dai.sike@h3c.com]
 * @date 2018/10/19 15:44
 * @since 1.0
 */
public class RedisTest extends BaseTest {

	@Autowired
	private DeviceResourcesService deviceResourcesService;

	@Autowired
	private RedisUtils redisUtils;

	@Test
	public void redisTest() {
		List<ResourceTreeNode> resourceTreeNodes = deviceResourcesService.getResourceTree("0,1", null, true);
		redisUtils.setWithExpire("vms_tree_01_1",resourceTreeNodes, 6000, TimeUnit.SECONDS);
		List<ResourceTreeNode> cacheNodes = (List<ResourceTreeNode>) redisUtils.get("vms_tree_01_1");
		System.out.println(cacheNodes);

	}

}
