/*
 * www.unisinsight.com Inc.
 * Copyright (c) 2018 All Rights Reserved
 */
package com.unisinsight.vdp.core.controller;
import com.unisinsight.framework.common.exception.BaseErrorCode;
import com.unisinsight.framework.common.exception.BaseException;
import com.unisinsight.framework.common.utils.CheckUtil;
import com.unisinsight.vdp.core.dto.request.CollectionDeviceReqDTO;
import com.unisinsight.vdp.core.dto.response.CollectionDeviceResDTO;
import com.unisinsight.vdp.core.service.CollectionDeviceService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
 * 收藏视频资源Controller
 *
 * @author wangshuai [KF.wangshuaiB@h3c.com]
 * @date 2018/09/18
 * @since 1.0
 */
@RestController
@RequestMapping("/api/vdp/v1/core/collection/device")
@CrossOrigin
public class CollectionDeviceController {
    /**
     * 注入collectionDeviceService
     */
    @Resource
    private CollectionDeviceService collectionDeviceService;

    /**
     * 资源收藏
     * @param collectionDeviceReqDTO {@link CollectionDeviceReqDTO}
     * @return {@link CollectionDeviceResDTO}
     */
    @PostMapping
    public CollectionDeviceResDTO add(@RequestBody @Valid CollectionDeviceReqDTO collectionDeviceReqDTO) {
        return collectionDeviceService.save(collectionDeviceReqDTO);
    }

    /**
     * 资源批量新增-----测试分支
     * @param collectionDeviceReqDTOList 监控点集合
     * @return List<CollectionDeviceResDTO>
     */
    @PostMapping("/batch-add")
    public List<CollectionDeviceResDTO> batchAdd(@RequestBody @Valid List<CollectionDeviceReqDTO> collectionDeviceReqDTOList) {
        //传参校验:三种情况：1、空 2、空格(0 == g.getName().trim().length()) 3、漏传 4、长度超范围
        collectionDeviceReqDTOList.forEach(g -> {
            if (!CheckUtil.isNotNullAndNotEmpty(g.getGroupId()) || !CheckUtil.isNotNullAndNotEmpty(g.getName()) || 0 == g.getName().trim().length())  {
                throw BaseException.of(BaseErrorCode.INVALID_PARAM_ERROR.of(), "资源组id或监控点名称不能为空");
            }
        });
        return collectionDeviceService.batchSave(collectionDeviceReqDTOList);
    }

    /**
     * 依据主键id删除
     * @param id 监控点id
     */
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        //参数校验
        if (null == id || id <= 0 || id > Long.MAX_VALUE) {
            throw BaseException.of(BaseErrorCode.INVALID_PARAM_ERROR.of(), "监控点id不能空且范围必须在1-9223372036854775807之间");
        }
        collectionDeviceService.deleteById(id);
    }

    /**
     * 通过ID查找监控点
     * @param id 主键
     * @return CollectionDeviceResDTO
     */
    @GetMapping("/{id}")
    public CollectionDeviceResDTO detail(@PathVariable Integer id) {
        //参数校验
        if (id <= 0 || id > Integer.MAX_VALUE) {
            throw BaseException.of(BaseErrorCode.INVALID_PARAM_ERROR.of(), "监控点id不能空且范围必须在1-2147483647之间");
        }
        return collectionDeviceService.findById(id);
    }

    /**
     * 视频资源模糊查询
     * @param name 监控点名称
     * @return List<CollectionDeviceResDTO>
     */
    @PostMapping("/search-key")
    public List<CollectionDeviceResDTO> searchKey(String name) {
       return collectionDeviceService.searchKey(name);
    }
}
