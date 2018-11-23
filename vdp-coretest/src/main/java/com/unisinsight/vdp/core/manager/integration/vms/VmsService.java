/*
 * www.unisinsight.com Inc.
 * Copyright (c) 2018 All Rights Reserved
 */
package com.unisinsight.vdp.core.manager.integration.vms;

import com.unisinsight.vdp.common.vms.request.ChannelResourcesReq;
import com.unisinsight.vdp.common.vms.response.bean.ChannelDevice;
import com.unisinsight.vdp.common.vms.response.bean.DetailChannelDevice;
import com.unisinsight.vdp.common.vms.response.bean.ResourceTreeNode;

import java.util.List;

/**
 * description
 *
 * @author daisike [dai.sike@unisinsight.com]
 * @date 2018/10/08 16:10
 * @since 1.0
 */
public interface VmsService {

    /**
     * 获取资源树
     * @param treeType
     *            资源树类型，需要的通道组织树节点 （0：业务组织，1：视频输入通道，2：解码通道，3：报警输入通道，4：报警输出通道，
     *            5，卡口通道，6：设备节点，10：动环通道），可选择多种类型，中间用逗号隔开 通常传 0,1 即可获取资源树所有结点
     * @param deviceType
     *            设备类型，此参数在treeType中有6的时候才生效（获取哪一种设备节点)
     *            0：获取所有设备节点，1：编码器，2：解码器，3：报警主机，5：卡口设备） 如果不需要设备节点，直接输入" "
     * @param refresh 更新redis缓存
     * @return 资源树列表
     */
    List<ResourceTreeNode> getResourceTree(String treeType, String deviceType, boolean refresh);

    /**
     * 获取播放地址
     * @param cameraId 通道编码
     * @return 播放地址链接
     */
    String getRealPlayUrl(String cameraId);

    /**
     * 获取通道列表（依设备编码），
     * @param  deviceId 设备id
     * @return 通道列表
     */
    List<ChannelDevice> getChannelsByDeviceId(String deviceId);

    /**
     * 获取通道资源列表
     * @param channelResourcesReq
     * @return
     */
    List<DetailChannelDevice> getChannelResources(ChannelResourcesReq channelResourcesReq);


}
