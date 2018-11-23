/*
 * www.unisinsight.com Inc.
 * Copyright (c) 2018 All Rights Reserved
 */
package com.unisinsight.vdp.core.service;

import com.unisinsight.vdp.core.dto.request.DataRetentionPeriodReqDTO;
import com.unisinsight.vdp.core.dto.response.DataRetentionPeriodResDTO;

/**
 * 留存期时间本地数据service
 *
 * @author liujiaji [liu.jiaji@unisinsight.com]
 * @date 2018/11/09 09:38
 * @since 1.0
 */
public interface DataRetentionPeriodService {


    /**
     * 新增/修改留存期信息到数据库
     * @param reqDTO
     */
    void saveOrUpdate(DataRetentionPeriodReqDTO reqDTO);


    /**
     * 更新数据留存期时间
     * @param reqDTO
     */
    void updateRetentionPeriod(DataRetentionPeriodReqDTO reqDTO);


    /**
     * 根据数据类型获取配置信息
     * @param dataType
     * @return
     */
    DataRetentionPeriodResDTO getPeriodByType(String dataType);

    /**
     * 删除所有数据
     */
    void deleteAll();

    /**
     * 前端传回数据验证
     * @param reqDTO
     * @return
     */
    void dataValid(DataRetentionPeriodReqDTO reqDTO);

}