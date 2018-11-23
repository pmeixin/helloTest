/*
 * www.unisinsight.com Inc.
 * Copyright (c) 2018 All Rights Reserved
 */
package com.unisinsight.vdp.core.controller;

import com.unisinsight.framework.common.core.annotation.Log;
import com.unisinsight.vdp.core.common.utils.Constants;
import com.unisinsight.vdp.core.dto.request.DataRetentionPeriodReqDTO;
import com.unisinsight.vdp.core.dto.response.DataRetentionPeriodResDTO;
import com.unisinsight.vdp.core.model.DataRetentionPeriodLog;
import com.unisinsight.vdp.core.service.DataRetentionPeriodLogService;
import com.unisinsight.vdp.core.service.DataRetentionPeriodService;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

import static com.unisinsight.vdp.core.service.impl.DataRetentionPeriodServiceImpl.DATA_REDIS_KEY;

/**
 * 数据存留期时间controller
 *
 * @author liujiaji [HZ.liujiaji@h3c.com]
 * @date 2018/10/25 10:43
 * @since 1.0
 */
@RestController
@RequestMapping("/api/vdp/v1/core/data-retention-period")
public class DataRetentionPeriodController {


    @Resource
    private DataRetentionPeriodLogService logService;

    @Resource
    private DataRetentionPeriodService periodService;


    /**
     * 数据留存时间修改
     *
     * @param reqDTO
     * @return
     */
    @PutMapping
    @Log(module = Constants.LOGGER_MODEL, action = "数据留存时间修改")
    public DataRetentionPeriodResDTO updateRetentionPeriod(@RequestBody @Valid DataRetentionPeriodReqDTO reqDTO) {
        // 修改数据返回日志
        periodService.updateRetentionPeriod(reqDTO);
        // 保存操作日志
        DataRetentionPeriodLog log = new DataRetentionPeriodLog();
        BeanUtils.copyProperties(reqDTO, log);
        logService.save(log);
        DataRetentionPeriodResDTO resDTO = new DataRetentionPeriodResDTO();
        BeanUtils.copyProperties(reqDTO, resDTO);
        return resDTO;
    }

    /**
     * 根据类型获取留存期时间配置数据
     *
     * @return
     */
    @GetMapping("/{type}")
    @Log(module = Constants.LOGGER_MODEL, action = "根据数据类型获取留存期时间配置数据")
    public DataRetentionPeriodResDTO listAllData(@PathVariable(value = "type") Integer type) {
        return periodService.getPeriodByType(DATA_REDIS_KEY + type);
    }
}