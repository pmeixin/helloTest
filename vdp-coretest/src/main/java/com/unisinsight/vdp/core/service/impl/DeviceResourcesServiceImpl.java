/*
 * www.unisinsight.com Inc.
 * Copyright (c) 2018 All Rights Reserved
 */

package com.unisinsight.vdp.core.service.impl;
import com.unisinsight.vdp.common.vms.request.ChannelResourcesReq;
import com.unisinsight.vdp.common.vms.request.bean.Condition;
import com.unisinsight.vdp.common.vms.request.bean.Pagination;

import com.unisinsight.vdp.common.vms.response.bean.ChannelDevice;
import com.unisinsight.vdp.common.vms.response.bean.DetailChannelDevice;
import com.unisinsight.vdp.common.vms.response.bean.ResourceTreeNode;
import com.unisinsight.vdp.core.dto.request.ChannelResourceDTO;
import com.unisinsight.vdp.core.manager.integration.vms.VmsService;
import com.unisinsight.vdp.core.service.AnalysisTaskService;
import com.unisinsight.vdp.core.service.DeviceResourcesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 资源树服务的实现
 * @author Li Gaoyang [li.gaoyang@unisinsight.com]
 * @date 2018-09-21 10:03:24
 * @since 1.0
 */
@Service
public class DeviceResourcesServiceImpl implements DeviceResourcesService {

    /**
     * 业务组织树类型
     */
    private static final int BUSINESS_ORGANIZATION_TREE_TYPE = 0;

    /**
     * 设置HashMap初始容量
     */
    private static final int HASH_MAP_ORIGINAL_CAPACITY = 16;

    /**
     * 解析任务调用
     */
    @Autowired
    private AnalysisTaskService analysisTaskService;

    /**
     * 调用底层vms服务
     */
    @Autowired
    private VmsService vmsService;

    @Override
    public List<ResourceTreeNode> getResourceTreeNodes(String unitType, String deviceType, boolean refresh) {
        return vmsService.getResourceTree(unitType, deviceType, refresh);
    }

    @Override
    public List<ResourceTreeNode> getResourceTree(String unitType, String deviceType, boolean refresh) {
        return buildTree(vmsService.getResourceTree(unitType, deviceType, refresh));
    }

    @Override
    public List<ResourceTreeNode> getFilterResourceTree(String unitType, String deviceType, boolean refresh) {
        //获取已建成功设备
        List<String> createdDevices = analysisTaskService.getCreatedDeviceList();
        List<ResourceTreeNode> resourceTreeNodes = vmsService.getResourceTree(unitType, deviceType, refresh);

        resourceTreeNodes.forEach(node -> {
            //判断非组织结点
            if (node.getNodeType() != BUSINESS_ORGANIZATION_TREE_TYPE) {
                   //设置1 标识设备已建成功
                   if (createdDevices.contains(node.getId())) {
                     node.setCreated(Integer.valueOf(1));
                   } else {
                       node.setCreated(Integer.valueOf(0));
                   }
            }
        });
        return buildTree(resourceTreeNodes);
    }

    @Override
    public List<ChannelDevice> getChannelsByDeviceId(String deviceId) {
        return vmsService.getChannelsByDeviceId(deviceId);
    }

    @Override
    public List<DetailChannelDevice> getChannelResources(ChannelResourceDTO channelResourceDTO){

        ChannelResourcesReq resoucesReq = new ChannelResourcesReq();
        resoucesReq.setUnitType(channelResourceDTO.getUnitType());

        Condition condition = new Condition();
        condition.setKeyWord(channelResourceDTO.getKeyWord());
        condition.setOnlineStatus(channelResourceDTO.getOnlineStatus());
        condition.setIsMark(channelResourceDTO.getMark());
        resoucesReq.setCondition(condition);

        Pagination pagination = new Pagination();
        pagination.setPageNo(channelResourceDTO.getPageNum());
        pagination.setPageSize(channelResourceDTO.getPageSize());
        pagination.setOrder(channelResourceDTO.getOrder());
        resoucesReq.setPagination(pagination);
        return vmsService.getChannelResources(resoucesReq);
    }

    /**
     * 生成树状结构
     * 构建树形结构，考虑到list中还会存在遍历情况，暂不用base包中的实现
     * @param resTreeNodes 待生成树状结构的节点
     * @return 返回树形结构
     */
    private List<ResourceTreeNode> buildTree(List<ResourceTreeNode> resTreeNodes) {
        if (null != resTreeNodes && !resTreeNodes.isEmpty()) {
            Map<String, ResourceTreeNode> nodeMap = new HashMap<>(HASH_MAP_ORIGINAL_CAPACITY);
            ResourceTreeNode root = null;
            resTreeNodes.forEach(node -> nodeMap.put(node.getId(), node));
            for (ResourceTreeNode resTreeNode : resTreeNodes) {
                if (null == resTreeNode.getOrgCode()) {
                    root = resTreeNode;
                } else {
                    ResourceTreeNode pNode = nodeMap.get(resTreeNode.getOrgCode());
                    if (null != pNode) {
                        if (pNode.getChildren() == null) {
                            pNode.setChildren(new ArrayList<>());
                        }
                        pNode.getChildren().add(resTreeNode);
                    }
                }
            }
            List<ResourceTreeNode> rootList = new ArrayList<>();
            rootList.add(root);
            if (root == null) {
                return resTreeNodes;
            }
            return rootList;
        }
        return null;
    }

}
