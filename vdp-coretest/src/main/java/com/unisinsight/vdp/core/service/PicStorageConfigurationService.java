/*
 * www.unisinsight.com Inc.
 * Copyright (c) 2018 All Rights Reserved
 */
package com.unisinsight.vdp.core.service;

import com.unisinsight.vdp.common.mda.response.PicStorageConfigurationResDTO;
import com.unisinsight.vdp.core.dto.request.PicStorageConfigurationReqDTO;
import com.unisinsight.vdp.core.model.PicStorageConfiguration;

import java.util.List;

/**
 * 本地存储图片存储策略信息
 *
 * @author liujiaji [liu.jiaji@unisinsight.com]
 * @date 2018/11/05 19:04
 * @since 1.0
 */
public interface PicStorageConfigurationService {

    /**
     * 新增/修改图片存储信息到数据库
     * @param configuration
     */
    void saveOrUpdate(PicStorageConfiguration configuration);

    /**
     * 根据前段数据修改
     * @param reqDTO
     */
    PicStorageConfigurationResDTO updateByResDTO(PicStorageConfigurationReqDTO reqDTO);


    /**
     * 根据数据类型获取存储信息
     * @param type
     * @return
     */
    PicStorageConfigurationResDTO getInfoByType(String type);


    /**
     * 根据数据类型删除存储信息
     * @param type
     */
    void deleteByType(Integer type);

    /**
     * 删除所有数据
     */
    void deleteAll();

    /**
     * 修改所有数据的剩余容量
     * @param remainCapacity
     */
    void updateAllRemainCapacity(Integer remainCapacity);

    /**
     * 查询多个数据
     * @param types
     * @return
     */
    List<PicStorageConfigurationResDTO> getConfigsByTypes(List<Integer> types);
}