/*
 * www.unisinsight.com Inc.
 * Copyright (c) 2018 All Rights Reserved
 */
package com.unisinsight.vdp.core.controller;

import com.unisinsight.framework.common.exception.BaseErrorCode;
import com.unisinsight.framework.common.exception.BaseException;
import com.unisinsight.vdp.core.dto.request.PresetDeviceReqDTO;
import com.unisinsight.vdp.core.dto.response.PresetDeviceResDTO;
import com.unisinsight.vdp.core.service.PresetDeviceService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
 * 预设点Controller
 *
 * @author hujun [KF.hujunA@h3c.com]
 * @date 2018/10/12 10:14
 * @since 1.0
 */
@RestController
@RequestMapping("/api/vdp/v1/core/collection/preset-device")
@CrossOrigin
public class PresetDeviceController {
    /**
     * 注入presetDeviceService
     */
    @Resource
    private PresetDeviceService presetDeviceService;

    /**
     * 新增预设点
     * @param presetDeviceReqDTO 资源组传参
     * @return 返回参数
     */
    @PostMapping
    public PresetDeviceResDTO add(@RequestBody @Valid PresetDeviceReqDTO presetDeviceReqDTO) {
        return presetDeviceService.save(presetDeviceReqDTO);
    }

    /**
     * 通过主键删除
     * @param id x巡航路径id
     */
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        //参数校验
        if (null == id || id <= 0 || id > Integer.MAX_VALUE) {
            throw BaseException.of(BaseErrorCode.INVALID_PARAM_ERROR.of(), "预置点id不能空且范围必须在1-2147483647之间");
        }
        presetDeviceService.deleteById(id);
    }

    /**
     * 预置点更新
     * @param presetDeviceReqDTO 资源组传参
     * @return PresetDeviceResDTO
     */
    @PutMapping
    public PresetDeviceResDTO update(@RequestBody @Valid PresetDeviceReqDTO presetDeviceReqDTO) {
        //检验输入参数
        if (null == presetDeviceReqDTO.getPresetDeviceId()) {
            throw BaseException.of(BaseErrorCode.INVALID_PARAM_ERROR.of(), "预设点不能为空");
        }
        return presetDeviceService.update(presetDeviceReqDTO);
    }

    /**
     * 获取所有的预置点
     * @return List<PresetDeviceResDTO>
     */
    @GetMapping("/list")
    public List<PresetDeviceResDTO> list() {
        return presetDeviceService.listAll();
    }

    /**
     * 预置点通过设备ID查找
     * @param id 设备id
     * @return List<PresetDeviceResDTO>
     */
    @GetMapping("/{id}")
    public List<PresetDeviceResDTO> findById(@PathVariable  String id) {
        return presetDeviceService.findById(id);
    }
}
