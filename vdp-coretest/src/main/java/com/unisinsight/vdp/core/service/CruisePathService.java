/*
 * www.unisinsight.com Inc.
 * Copyright (c) 2018 All Rights Reserved
 */
package com.unisinsight.vdp.core.service;
import com.unisinsight.vdp.core.dto.request.CruisePathReqDTO;
import com.unisinsight.vdp.core.dto.response.CruisePathResDTO;
import com.unisinsight.vdp.core.model.CruisePath;
import java.util.List;

/**
 * 巡航路径Service
 *
 * @author hujun [KF.hujunA@h3c.com]
 * @date 2018/10/11 10:15
 * @since 1.0
 */
public interface CruisePathService {

    /**
     * 新增
     * @param reqDTO {@link CruisePathReqDTO}
     * @return CruisePathResDTO
     */
    CruisePathResDTO save(CruisePathReqDTO reqDTO);

    /**
     * 更新
     * @param updateDTO {@link CruisePathReqDTO}
     * @return {@link CruisePathResDTO}
     */
    CruisePathResDTO update(CruisePathReqDTO updateDTO);

    /**
     * 通过主键删除
     * @param id 主键
     */
    void deleteById(Integer id);

    /**
     * 依据设备id查询巡航路径
     * @param id 设备id
     * @return List<CruisePathResDTO>
     */
    List<CruisePathResDTO> findById(String id);

    /**
     * createTime和updateTime设置时间戳
     * @param cruisePathResDTO 返回前端DTO
     * @param cruisePath 从数据库获取的Model
     * @return CollectionResDTO
     */
    CruisePathResDTO setTime(CruisePathResDTO cruisePathResDTO, CruisePath cruisePath);

    /**
     * 获取所有的预置点
     * @return List<PresetDeviceResDTO>
     **/
    List<CruisePathResDTO> listAll();
}
