/*
 * www.unisinsight.com Inc.
 * Copyright (c) 2018 All Rights Reserved
 */
package com.unisinsight.vdp.core.controller;

import com.unisinsight.framework.common.core.annotation.Log;
import com.unisinsight.vdp.common.mda.response.PicStorageConfigurationResDTO;
import com.unisinsight.vdp.core.common.utils.Constants;
import com.unisinsight.vdp.core.dto.request.PicStorageConfigurationReqDTO;
import com.unisinsight.vdp.core.model.PicStorageConfigurationLog;
import com.unisinsight.vdp.core.service.PicStorageConfigurationLogService;
import com.unisinsight.vdp.core.service.PicStorageConfigurationService;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

import java.util.ArrayList;
import java.util.List;

import static com.unisinsight.vdp.common.constants.MDAConstant.PIC_STORAGE_REDIS_PREFIX;
import static com.unisinsight.vdp.core.service.impl.PicStorageConfigurationServiceImpl.PIC_CACHE_KEY;

/**
 * 图片数据存储信息controller
 *
 * @author liujiaji [HZ.liujiaji@h3c.com]
 * @date 2018/10/25 10:43
 * @since 1.0
 */
@RestController
@RequestMapping("/api/vdp/v1/core/pic-storage-configuration")
public class PicStorageConfigurationController {

    @Resource
    private PicStorageConfigurationLogService logService;

    @Resource
    private PicStorageConfigurationService infoService;


    /**
     * 图片存储信息修改
     *
     * @param reqDTO
     * @return
     */
    @PutMapping
    @Log(module = Constants.LOGGER_MODEL, action = "图片存储信息修改")
    public PicStorageConfigurationResDTO updatePicStorageConfiguration(@RequestBody @Valid PicStorageConfigurationReqDTO reqDTO) {
        PicStorageConfigurationResDTO resDTO = infoService.updateByResDTO(reqDTO);
        // 操作成功保存日志
        PicStorageConfigurationLog log = new PicStorageConfigurationLog();
        BeanUtils.copyProperties(reqDTO, log);
        logService.save(log);
        return resDTO;
    }


    /**
     * 根据类型获取图片存储信息
     *
     * @return
     * @throws Exception
     */
    @GetMapping("/{type}")
    @Log(module = Constants.LOGGER_MODEL, action = "根据类型获取图片存储信息")
    public PicStorageConfigurationResDTO getConfigByType(@PathVariable(value = "type") Integer type) {
        return infoService.getInfoByType(PIC_CACHE_KEY + type);
    }

}