/*
 * www.unisinsight.com Inc.
 * Copyright (c) 2018 All Rights Reserved
 */

package com.unisinsight.vdp.core.service;

import com.unisinsight.vdp.common.vms.response.bean.ChannelDevice;
import com.unisinsight.vdp.common.vms.response.bean.DetailChannelDevice;
import com.unisinsight.vdp.common.vms.response.bean.ResourceTreeNode;
import com.unisinsight.vdp.core.dto.request.ChannelResourceDTO;

import java.util.List;

/**
 * ResourceTreeService
 * @author Li Gaoyang [li.gaoyang@unisinsight.com]
 * @date 2018-09-21 09:35:36
 * @since 1.0
 */
public interface DeviceResourcesService {

	/**
	 * 获取所有组织、设备节点
	 * @param treeType
	 *            需要的通道组织树节点（0：业务组织，1：视频输入通道，2：解码通道，3：报警输入通道，4：报警输出通道，
	 *            5，卡口通道，6：设备节点，10：动环通道），可选择多种类型，中间用逗号隔开，例如："0,1,2"
	 * @param deviceType
	 *            设备类型，此参数在treeType中有6的时候才生效（获取哪一种设备节点，
	 *            0：获取所有设备节点，1：编码器，2：解码器，3：报警主机，5：卡口设备） 如果不需要设备节点，直接输入" "
	 * @param refresh 更新redis缓存
	 * @return 所有节点列表
	 */
	List<ResourceTreeNode> getResourceTreeNodes(String treeType, String deviceType, boolean refresh);

	/**
	 * 获取设备资源树（含是否已创建任务的标识）
	 * @param treeType 同上
	 * @param deviceType 同上
	 * @param refresh 同上
	 * @return 设备资源树
	 */
	List<ResourceTreeNode> getResourceTree(String treeType, String deviceType, boolean refresh);

	/**
	 * 获取设备资源树节点
	 * @param treeType 同上
	 * @param deviceType 同上
	 * @param refresh 同上
	 * @return 设备资源树节点
	 */
	List<ResourceTreeNode> getFilterResourceTree(String treeType, String deviceType, boolean refresh);

	/**
	 * 根据设备id获取通道列表
	 * @param deviceId 设备id
	 * @return 返回资源通道列表
	 */
	List<ChannelDevice> getChannelsByDeviceId(String deviceId);

	/**
	 * 获取通道列表
	 * @param channelResourceDTO
	 * @return
	 */
    List<DetailChannelDevice> getChannelResources(ChannelResourceDTO channelResourceDTO);
}
