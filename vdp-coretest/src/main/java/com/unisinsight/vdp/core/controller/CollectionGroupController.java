/*
 * www.unisinsight.com Inc.
 * Copyright (c) 2018 All Rights Reserved
 */
package com.unisinsight.vdp.core.controller;

import com.unisinsight.framework.common.exception.BaseErrorCode;
import com.unisinsight.framework.common.exception.BaseException;
import com.unisinsight.vdp.core.dto.request.CollectionGroupReqDTO;
import com.unisinsight.vdp.core.dto.response.CollectionGroupResDTO;
import com.unisinsight.vdp.core.service.CollectionGroupService;
import org.hibernate.validator.constraints.Range;
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
 * 视频收藏组Controller
 *
 * @author wangshuai [KF.wangshuaiB@h3c.com]
 * @date 2018/09/18
 * @since 1.0
 */
@RestController
@RequestMapping("/api/vdp/v1/core/collection/group")
@CrossOrigin
public class CollectionGroupController {
    /**
     * 注入collectionGroupService
     */
    @Resource
    private CollectionGroupService collectionGroupService;
    /**
     * 新增资源组
     * @param collectionGroupReqDTO 资源组传参
     * @return {@link CollectionGroupResDTO}
     */
    @PostMapping
    public CollectionGroupResDTO add(@RequestBody @Valid CollectionGroupReqDTO collectionGroupReqDTO) {
        return collectionGroupService.save(collectionGroupReqDTO);
    }

    /**
     * 通过主键删除
     * @param id 资源组id
     */
    @DeleteMapping("/{id}")
    @Range(min = 1, max = Integer.MAX_VALUE,  message = "范围必须在1-2147483647之间")
    public void delete(@PathVariable Integer id) {
        //参数校验
        if (null == id || id <= 0 || id > Integer.MAX_VALUE) {
            throw BaseException.of(BaseErrorCode.INVALID_PARAM_ERROR.of(), "资源组id不能空且范围必须在1-2147483647之间");
        }
        collectionGroupService.deleteById(id);
    }

    /**
     * 资源组更新
     * @param collectionGroupReqDTO {@link CollectionGroupReqDTO}
     * @return {@link CollectionGroupResDTO}
     */
    @PutMapping
    public CollectionGroupResDTO update(@RequestBody @Valid CollectionGroupReqDTO collectionGroupReqDTO) {
        //校验资源组名称和id
        if (0 == collectionGroupReqDTO.getName().trim().length() || null  == collectionGroupReqDTO.getGroupId()
                || collectionGroupReqDTO.getGroupId() > Integer.MAX_VALUE || collectionGroupReqDTO.getGroupId() <= 0) {
            throw BaseException.of(BaseErrorCode.INVALID_PARAM_ERROR.of(), "资源组名称和id不能为空且范围必须在1-2147483647之间");
        }
        return collectionGroupService.update(collectionGroupReqDTO);
    }

    /**
     * 通过ID查找
     * @param id 资源组id
     * @return CollectionGroupResDTO
     */
    @GetMapping("/{id}")
    public CollectionGroupResDTO detail(@PathVariable  Integer id) {
        return collectionGroupService.findById(id);
    }

    @GetMapping("/a")
    public List<CollectionGroupResDTO> selectBypage(int currentpage,int pagesize) {
        return collectionGroupService.selectbp(currentpage,pagesize);
    }

    @GetMapping("/b")
    public List<CollectionGroupResDTO> selectBypagee(int currentpage,int pagesize) {
        return collectionGroupService.selectbpp(currentpage,pagesize);
    }





    /**
     * 资源模糊查询
     * @param name 资源组名称
     * @return List<CollectionGroupResDTO>
     */
    @PostMapping("/search-group-key")
    public List<CollectionGroupResDTO> searchKey(String name) {
        return collectionGroupService.searchKey(name);
    }

    /**
     * 获取所有的组与下属资源
     * @return List<CollectionGroupResDTO>
     */
    @GetMapping("/list")
    public List<CollectionGroupResDTO> list() {
        return collectionGroupService.listAll();
    }
}
