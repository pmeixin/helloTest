/*
 * www.unisinsight.com Inc.
 * Copyright (c) 2018 All Rights Reserved
 */
package com.unisinsight.vdp.core.service;
import com.unisinsight.vdp.core.dto.request.PresetDeviceReqDTO;
import com.unisinsight.vdp.core.dto.response.PresetDeviceResDTO;
import com.unisinsight.vdp.core.model.PresetDevice;
import java.util.List;
/**
 * description
 *
 * @author hujun [KF.hujunA@h3c.com]
 * @date 2018/10/11 10:22
 * @since 1.0
 */
public interface PresetDeviceService {
    /**
     * 新增
     * @param reqDTO {@link PresetDeviceReqDTO}
     * @return PresetDeviceResDTO
     */
    PresetDeviceResDTO save(PresetDeviceReqDTO reqDTO);

    /**
     * 通过主键删除
     * @param id 主键id
     */
    void deleteById(Integer id);

    /**
     * 更新
     * @param updateDTO {@link PresetDeviceReqDTO}
     * @return {@link PresetDeviceResDTO}
     */
    PresetDeviceResDTO update(PresetDeviceReqDTO updateDTO);

    /**
     * 依据设备id查询预置点
     * @param id 设备id
     * @return  List<PresetDeviceResDTO>
     */
    List<PresetDeviceResDTO> findById(String id);

    /**
     * createTime和updateTime设置时间戳
     * @param presetDeviceResDTO 返回前端DTO
     * @param presetDevice 从数据库获取的Model
     * @return CollectionResDTO
     */
    PresetDeviceResDTO setTime(PresetDeviceResDTO presetDeviceResDTO, PresetDevice presetDevice);

    /**
     * 获取所有的预置点
     * @return List<PresetDeviceResDTO>
     **/
    List<PresetDeviceResDTO> listAll();

    /**
     * doList转DTOList，并设置时间戳类型字段
     * @param presetDevice 预置点集合
     * @return PresetDeviceResDTO
     **/
    List<PresetDeviceResDTO> doToDtoList(List<PresetDevice> presetDevice);
}
