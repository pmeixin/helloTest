/*
 * www.unisinsight.com Inc.
 * Copyright (c) 2018 All Rights Reserved
 */
package com.unisinsight.vdp.core.controller;
import com.unisinsight.framework.common.exception.BaseErrorCode;
import com.unisinsight.framework.common.exception.BaseException;
import com.unisinsight.vdp.core.dto.request.CruisePathReqDTO;
import com.unisinsight.vdp.core.dto.response.CruisePathResDTO;
import com.unisinsight.vdp.core.service.CruisePathService;
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
 *巡航路径Controller
 * @author hujun [KF.hujunA@h3c.com]
 * @date 2018/10/11 10:12
 * @since 1.0
 */
@RestController
@RequestMapping("/api/vdp/v1/core/collection/cruise-path")
@CrossOrigin
public class CruisPathController {
    /**
     * 注入cruisePathService
     */
    @Resource
    private CruisePathService cruisePathService;

    /**
     * 新增巡航路径
     * @param cruisePathReqDTO 资源组传参
     * @return 返回参数
     */
    @PostMapping
    public CruisePathResDTO add(@RequestBody @Valid CruisePathReqDTO cruisePathReqDTO) {
        //传参校验:三种情况：1、空 2、空格 3、漏传 4、长度超范围
        if (0 == cruisePathReqDTO.getId().trim().length() || 0 == cruisePathReqDTO.getName().trim().length()) {
            throw BaseException.of(BaseErrorCode.INVALID_PARAM_ERROR.of(), "巡航名称或设备id不能为空");
        }
        //验证子节点下的巡航速度和时间不能为空
        cruisePathReqDTO.getChildren().forEach(c -> {
            if (null == c.getCruiseSpeed() || null == c.getCruiseTime()) {
                throw BaseException.of(BaseErrorCode.INVALID_PARAM_ERROR.of(), "巡航速度或时间不能为空");
            }
            if (c.getCruiseSpeed() <= 0 || c.getCruiseSpeed() > Short.MAX_VALUE || c.getCruiseTime() <= 0 || c.getCruiseTime() > Short.MAX_VALUE) {
                throw BaseException.of(BaseErrorCode.INVALID_PARAM_ERROR.of(), "巡航速度或时间范围必须在1~32767之间");
            }
        });
        return cruisePathService.save(cruisePathReqDTO);
    }

    /**
     * 通过主键删除
     * @param id x巡航路径id
     */
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        //参数校验
        if (null == id || id <= 0 || id > Integer.MAX_VALUE) {
            throw BaseException.of(BaseErrorCode.INVALID_PARAM_ERROR.of(), "巡航路径id不能空且范围必须在1-2147483647之间");
        }
        cruisePathService.deleteById(id);
    }

    /**
     * 巡航路径更新
     * @param cruisePathReqDTO 资源组传参
     * @return CruisePathResDTO
     */
    @PutMapping
    public CruisePathResDTO update(@RequestBody  @Valid CruisePathReqDTO cruisePathReqDTO) {
        //校验传入参数--巡航路径id
        if (null == cruisePathReqDTO.getCruisePathId() || cruisePathReqDTO.getCruisePathId() > Integer.MAX_VALUE ||  cruisePathReqDTO.getCruisePathId() <= 0) {
            throw BaseException.of(BaseErrorCode.INVALID_PARAM_ERROR.of(), "巡航路径id不能为空且范围必须在1-2147483647之间");
        }
        //传参校验:三种情况：1、空 2、空格 3、漏传 4、长度超范围--设备id和巡航名称
        if (0 == cruisePathReqDTO.getId().trim().length() || 0 == cruisePathReqDTO.getName().trim().length()) {
            throw BaseException.of(BaseErrorCode.INVALID_PARAM_ERROR.of(), "name或id参数格式不正确");
        }
        //验证子节点下的巡航速度和时间不能为空
        cruisePathReqDTO.getChildren().forEach(c -> {
            if (null == c.getCruiseSpeed() || null == c.getCruiseTime()) {
                throw BaseException.of(BaseErrorCode.INVALID_PARAM_ERROR.of(), "巡航速度或时间不能我空");
            }
        });
        return cruisePathService.update(cruisePathReqDTO);
    }

    /**
     * 获取所有的巡航路径
     * @return List<CruisePathResDTO>
     */
    @GetMapping("/list")
    public List<CruisePathResDTO> list() {
        return cruisePathService.listAll();
    }

    /**
     * 巡航路径通过设备ID查找
     * @param id 设备id
     * @return  List<CruisePathResDTO>
     */
    @GetMapping("/{id}")
    public List<CruisePathResDTO> findById(@PathVariable String id) {
        return cruisePathService.findById(id);
    }
}
