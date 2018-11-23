/*
 * www.unisinsight.com Inc.
 * Copyright (c) 2018 All Rights Reserved
 */
package com.unisinsight.vdp.core.service;
import com.unisinsight.vdp.core.dto.request.VideoLabelReqDTO;
import com.unisinsight.vdp.core.dto.response.VideoLabelResDTO;
import com.unisinsight.vdp.core.model.VideoLabel;

import java.util.List;


/**
 * 视频录像回放service
 *
 * @author wangshuai [wang.shuai@unisinsight.com]
 * @date 2018/10/30
 * @since 1.0
 */
public interface VideoLabelService  {

/**
     * 新增
     * @param reqDTO 录像标识请求参数
     * @return VideoLabelResDTO
     */
    VideoLabelResDTO save(VideoLabelReqDTO reqDTO);


    /**
     * 通过主键删除
     * @param id 视频标识id
     */
    void deleteById(Long id);

    /**
    * 更新
    * @param updateDTO 视频回放参数
    * @return VideoLabelResDTO
    */
    VideoLabelResDTO update(VideoLabelReqDTO updateDTO);
    /**
     * 通过ID查找
     * @param id 视频标识id
     * @param currentTime 当前时间
     * @return List<VideoLabelResDTO>
     */
    List<VideoLabelResDTO> findById(String id, Long currentTime);


    /**
     * createTime和updateTime设置时间戳
     * @param videoLabelResDTO 返回前端DTO
     * @param videoLabel 从数据库获取的Model
     * @return VideoLabelResDTO
     */
    VideoLabelResDTO setTime(VideoLabelResDTO videoLabelResDTO, VideoLabel videoLabel);

    /**
     * doList转DTOList，并设置时间戳类型字段
     * @param videoLabelList 视频标签model
     * @return List<VideoLabelResDTO>
     **/
    List<VideoLabelResDTO> doToDtoList(List<VideoLabel> videoLabelList);
}
