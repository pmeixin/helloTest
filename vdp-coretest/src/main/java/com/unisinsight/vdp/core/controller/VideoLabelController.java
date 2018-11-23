/*
 * www.unisinsight.com Inc.
 * Copyright (c) 2018 All Rights Reserved
 */
package com.unisinsight.vdp.core.controller;
import com.unisinsight.framework.common.exception.BaseErrorCode;
import com.unisinsight.framework.common.exception.BaseException;
import com.unisinsight.vdp.core.common.constant.VideoLabelConstant;
import com.unisinsight.vdp.core.dto.request.VideoLabelReqDTO;
import com.unisinsight.vdp.core.dto.response.VideoLabelResDTO;
import com.unisinsight.vdp.core.service.VideoLabelService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tk.mybatis.mapper.util.StringUtil;
import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
 * 视频录像回放进度条标记controller
 *
 * @author wangshuai [wang.shuai@unisinsight.com]
 * @date 2018/10/30
 * @since 1.0
 */
@RestController
@RequestMapping("/api/vdp/v1/core/video/label")
public class VideoLabelController {
    /**
     * 多岁的
     */
    @Resource
    private VideoLabelService videoLabelService;

    /**
     * 新增录像回放标记
     * @param videoLabelReqDTO 前端DTO
     * @return VideoLabelResDTO
     */
    @PostMapping
    public VideoLabelResDTO add(@RequestBody @Valid VideoLabelReqDTO videoLabelReqDTO) {
        if (StringUtil.isEmpty(videoLabelReqDTO.getId()) || videoLabelReqDTO.getId().length() < 1 || videoLabelReqDTO.getId().length() > VideoLabelConstant.ID_LENGTH) {
            throw BaseException.of(BaseErrorCode.INVALID_PARAM_ERROR.of(), "设备id不能为空，且长度范围为1-32位");
        }
        if (null == videoLabelReqDTO.getStartTime()) {
            throw BaseException.of(BaseErrorCode.INVALID_PARAM_ERROR.of(), "起始时间不能为空");
        }
        if (null == videoLabelReqDTO.getType()) {
            throw BaseException.of(BaseErrorCode.INVALID_PARAM_ERROR.of(), "标记类型不能为空");
        }
        if (VideoLabelConstant.TYPE2.equals(videoLabelReqDTO.getType()) && null == videoLabelReqDTO.getEndTime()) {
            throw BaseException.of(BaseErrorCode.INVALID_PARAM_ERROR.of(), "标记类型为锁时，结束时间不能为空");
        }
        return videoLabelService.save(videoLabelReqDTO);
    }

    /**
     * 依据主键删除标记标签
     * @param id 主键
     */
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        if (null == id || id < 1 || id > Long.MAX_VALUE) {
            throw BaseException.of(BaseErrorCode.INVALID_PARAM_ERROR.of(), "标记id范围为1-9223372036854775807");
         }
        videoLabelService.deleteById(id);
    }

    /**
     * 修改录像回放标签
     * @param videoLabelReqDTO 前端修改参数
     * @return VideoLabelResDTO
     */
    @PutMapping
    public VideoLabelResDTO  update(@RequestBody @Valid VideoLabelReqDTO videoLabelReqDTO) {
        if (null == videoLabelReqDTO.getLabelId()) {
            throw BaseException.of(BaseErrorCode.INVALID_PARAM_ERROR.of(), "标记id不能为空");
        }
        if (StringUtil.isEmpty(videoLabelReqDTO.getMark())) {
            throw BaseException.of(BaseErrorCode.INVALID_PARAM_ERROR.of(), "标记内容不能为空");
        }
        return videoLabelService.update(videoLabelReqDTO);
    }

    /**
     * 根据设备Id获取视频录像回放标记
     * @param id 设备Id
     * @param currentTime 当前时间
     * @return List<VideoLabelResDTO>
     */
    @GetMapping("/list")
    public List<VideoLabelResDTO> detail(@RequestParam(name = "id") String id, @RequestParam(name = "current_time", required = false) Long currentTime) {

        if (StringUtil.isEmpty(id) || id.length() < 1 || id.length() > VideoLabelConstant.ID_LENGTH) {
            throw BaseException.of(BaseErrorCode.INVALID_PARAM_ERROR.of(), "设备id不能为空,且长度在1-32位之间");
        }
        return videoLabelService.findById(id, currentTime);
    }

}
