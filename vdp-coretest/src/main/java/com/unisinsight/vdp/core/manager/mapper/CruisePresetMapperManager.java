/*
 * www.unisinsight.com Inc.
 * Copyright (c) 2018 All Rights Reserved
 */
package com.unisinsight.vdp.core.manager.mapper;
import com.google.common.collect.Lists;
import com.unisinsight.vdp.core.dto.response.CruisePresetResDTO;
import com.unisinsight.vdp.core.model.CruisePreset;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * description
 *
 * @author hujun [KF.hujunA@h3c.com]
 * @date 2018/10/11 16:54
 * @since 1.0
 */
@Component
public class CruisePresetMapperManager extends AbstractMapperManager<CruisePreset, Integer> {

    /**
     * 收藏资源list按group_id装载进对应的map中，deviceList为所有资源组（group_id存在）下的监控点集合
     * @param deviceList 资源组不为空的监控点
     * @return map集合
     */
    public Map<String, List<CruisePresetResDTO>> listToMap(List<CruisePreset> deviceList) {

        //将CollectionDevice装载在Map中，初始化一个map
        Map<String, List<CruisePresetResDTO>> deviceListMap = new HashMap<>(2);
        //d为子节点相关信息，单个的CollectionDevice
        deviceList.forEach(d  -> {
            CruisePresetResDTO cruisePresetResDTO = new CruisePresetResDTO();
            //DO转DTO----赋值
            BeanUtils.copyProperties(d, cruisePresetResDTO);
            //如果group_id不为空，在对应的list中直接添加，若为空，直接关联添加进map
            if (null != deviceListMap.get(d.getCruisePathId().toString())) {
                deviceListMap.get(d.getCruisePathId().toString()).add(cruisePresetResDTO);
            } else {
                List<CruisePresetResDTO> cruisePresetResDTOS = Lists.newArrayList();
                cruisePresetResDTOS.add(cruisePresetResDTO);
                deviceListMap.put(d.getCruisePathId().toString(), cruisePresetResDTOS);
            }
        });
        return deviceListMap;
    }
}
