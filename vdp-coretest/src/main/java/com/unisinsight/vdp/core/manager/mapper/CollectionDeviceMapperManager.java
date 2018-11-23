/*
 * www.unisinsight.com Inc.
 * Copyright (c) 2018 All Rights Reserved
 */
package com.unisinsight.vdp.core.manager.mapper;
import com.google.common.collect.Lists;
import com.unisinsight.vdp.common.vms.response.bean.ResourceTreeNode;
import com.unisinsight.vdp.core.controller.DeviceResourcesController;
import com.unisinsight.vdp.core.dto.response.CollectionDeviceResDTO;
import com.unisinsight.vdp.core.model.CollectionDevice;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 视频巡逻收藏资源MapperManager
 *
 * @author wangshuai [KF.wangshuaiB@h3c.com]
 * @date 2018/09/25 11:35
 * @since 1.0
 */
@Component
public class CollectionDeviceMapperManager extends AbstractMapperManager<CollectionDevice, Long> {
  /**
   * 注入deviceResourcesController
   */
  @Resource
  private DeviceResourcesController deviceResourcesController;

  /**
   * 展示资源树的不同功能，1：获取所有组织、设备节点的资源树（树状结构），
   * 2：获取设备资源树（含是否已创建任务的标识），3：获取设备资源树节点（平铺结构）。
   * 如果不设置该值，默认情况下返回第一种类型
   */
  private static final Integer FUNCTION_TYPE = 3;

  /**
   * 收藏资源list按group_id装载进对应的map中，deviceList为所有资源组（group_id存在）下的监控点集合
   * @param deviceList List<CollectionDevice>
   * @return map
   */
  public Map<String, List<CollectionDeviceResDTO>> listToMap(List<CollectionDevice> deviceList) {

    //将CollectionDevice装载在Map中，初始化一个map
    Map<String, List<CollectionDeviceResDTO>> deviceListMap = new HashMap<>(2);
    //d为子节点相关信息，单个的CollectionDevice
    deviceList.forEach(d  -> {
    CollectionDeviceResDTO collectionDeviceResDTO = new CollectionDeviceResDTO();
    //DO转DTO----赋值
      BeanUtils.copyProperties(d, collectionDeviceResDTO);
      //如果group_id不为空，在对应的list中直接添加，若为空，直接关联添加进map
      if (null != deviceListMap.get(d.getGroupId().toString())) {
        deviceListMap.get(d.getGroupId().toString()).add(collectionDeviceResDTO);
      } else {
        List<CollectionDeviceResDTO> collectionDeviceResList = Lists.newArrayList();
        collectionDeviceResList.add(collectionDeviceResDTO);
        deviceListMap.put(d.getGroupId().toString(), collectionDeviceResList);
      }
     });
    return deviceListMap;
  }


  /**
   * 获取资源树
   * @return 平铺的资源树
   */
  public List<ResourceTreeNode> getResourceTree() {
    return deviceResourcesController.getResourceTree("0,1", "0", FUNCTION_TYPE, true);
  }
}
