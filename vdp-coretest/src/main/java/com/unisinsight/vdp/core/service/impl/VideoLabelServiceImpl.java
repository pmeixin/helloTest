/*
 * www.unisinsight.com Inc.
 * Copyright (c) 2018 All Rights Reserved
 */
package com.unisinsight.vdp.core.service.impl;
import com.unisinsight.framework.common.exception.BaseException;
import com.unisinsight.framework.common.utils.BeanCopyUtil;
import com.unisinsight.framework.common.utils.User;
import com.unisinsight.framework.common.utils.UserHandler;
import com.unisinsight.vdp.core.common.constant.VideoLabelConstant;
import com.unisinsight.vdp.core.common.enums.VideoErrorCode;
import com.unisinsight.vdp.core.common.utils.DateUtils;
import com.unisinsight.vdp.core.dto.request.VideoLabelReqDTO;
import com.unisinsight.vdp.core.dto.response.VideoLabelResDTO;
import com.unisinsight.vdp.core.mapper.VideoLabelMapper;
import com.unisinsight.vdp.core.model.VideoLabel;
import com.unisinsight.vdp.core.service.VideoLabelService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 视频录像回放标签service实现类
 *
 * @author wangshuai [wang.shuai@unisinsight.com]
 * @date 2018/10/30
 * @since 1.0
 */
@Service
public class VideoLabelServiceImpl implements VideoLabelService {

    /**
     * 注入videoLabelMapper
     */
    @Resource
    private VideoLabelMapper videoLabelMapper;

    /**
     * 录像标识新增
     * @param reqDTO 录像标识请求参数
     * @return VideoLabelResDTO
     */
    @Override
    public VideoLabelResDTO save(VideoLabelReqDTO reqDTO) {

        //验证同视频下，锁跟标签不能重复添加
        String startTime = DateUtils.timeStampToDateStr(reqDTO.getStartTime(), DateUtils.YMD_HMSS);
        String endTime = DateUtils.timeStampToDateStr(reqDTO.getEndTime(), DateUtils.YMD_HMSS);
        List<VideoLabel> repeatList = videoLabelMapper.validRepeat(reqDTO.getId(), reqDTO.getType(), startTime, endTime);
        if ( !repeatList.isEmpty()) {
            throw BaseException.of(VideoErrorCode.LABEL_REPEAT.of());
        }

        VideoLabel videoLabel = BeanCopyUtil.convert(reqDTO, VideoLabel.class);
        videoLabel.setCreateTime(new Date());
        videoLabel.setStartTime(DateUtils.timestamp3Date(reqDTO.getStartTime(), DateUtils.YMD_HMSS));
        //判断endTime是否为空
        if (null != reqDTO.getEndTime()) {
            videoLabel.setEndTime(DateUtils.timestamp3Date(reqDTO.getEndTime(), DateUtils.YMD_HMSS));
        }
        //设置用户信息
        User user = UserHandler.getUser();
        if (null != user) {
            videoLabel.setCreateUserCode(user.getUserCode());
            videoLabel.setCreateUserName(user.getUserName());
        }
        //数据库保存
        videoLabelMapper.insertSelective(videoLabel);
        //设置时间戳字段的值
        return setTime(BeanCopyUtil.convert(videoLabel, VideoLabelResDTO.class), videoLabel);
    }

    /**
     * 删除录像标识
     * @param id 视频标识id
     */
    @Override
    public void deleteById(Long id) {
        //查询数据库原标签
        VideoLabel oldVideoLabel = videoLabelMapper.selectByPrimaryKey(id);
        //判断是否为标记标签
        if (!VideoLabelConstant.TYPE1.equals(oldVideoLabel.getType())) {
            throw BaseException.of(VideoErrorCode.LABEL_TYPE_ERROR.of());
        }
        videoLabelMapper.deleteByPrimaryKey(id);
    }

    /**
     * 修改录像标识
     * @param updateDTO 视频回放参数
     * @return VideoLabelResDTO
     */
    @Override
    public VideoLabelResDTO update(VideoLabelReqDTO updateDTO) {
        //查询数据库原标签
        VideoLabel oldVideoLabel = videoLabelMapper.selectByPrimaryKey(updateDTO.getLabelId());
        //判断标记点是否存在
        if (null == oldVideoLabel) {
            throw BaseException.of(VideoErrorCode.LABEL_ID_NOT_EXIST.of());
        }
        //判断是否为标记标签
        if (!VideoLabelConstant.TYPE1.equals(oldVideoLabel.getType())) {
            throw BaseException.of(VideoErrorCode.LABEL_TYPE_ERROR.of());
        }
        oldVideoLabel.setMark(updateDTO.getMark());
        oldVideoLabel.setUpdateTime(new Date());
        //设置用户信息
        User user = UserHandler.getUser();
        if (null != user) {
            oldVideoLabel.setUpdateUserCode(user.getUserCode());
            oldVideoLabel.setUpdateUserName(user.getUserName());
        }
        videoLabelMapper.updateByPrimaryKey(oldVideoLabel);
        //设置时间戳字段的值
        return setTime(BeanCopyUtil.convert(oldVideoLabel, VideoLabelResDTO.class), oldVideoLabel);
    }

    /**
     * 根据设备Id获取视频录像回放标记
     * @param id 设备Id
     * @param currentTime 当前时间
     * @return List<VideoLabelResDTO>
     */
    @Override
    public List<VideoLabelResDTO> findById(String id, Long currentTime) {
        //修改的名称不能同名
        String dateStr = "";
        if (null != currentTime) {
            dateStr = DateUtils.timeStampToDateStr(currentTime, DateUtils.ymd);
        }
        List<VideoLabel> videoLabelList = videoLabelMapper.queryList(id, dateStr);
        return doToDtoList(videoLabelList);
    }

    /**
     * 设置时间戳
     * @param videoLabelResDTO:返回前端DTO
     * @param videoLabel:从数据库获取的Model
     * @return VideoLabelResDTO
     */
    @Override
    public VideoLabelResDTO setTime(VideoLabelResDTO videoLabelResDTO, VideoLabel videoLabel) {
        if (null != videoLabel.getCreateTime()) {
            videoLabelResDTO.setCreateTime(videoLabel.getCreateTime().getTime());
        }
        if (null != videoLabel.getUpdateTime()) {
            videoLabelResDTO.setUpdateTime(videoLabel.getUpdateTime().getTime());
        }
        if (null != videoLabel.getStartTime()) {
            videoLabelResDTO.setStartTime(videoLabel.getStartTime().getTime());
         }
        if (null != videoLabel.getEndTime()) {
            videoLabelResDTO.setEndTime(videoLabel.getEndTime().getTime());
        }
        return videoLabelResDTO;
    }

    /**
     * doList转DTOList，并设置时间戳类型字段
     * @param videoLabelList 视频标签集合
     * @return List<VideoLabelResDTO>
     */
    @Override
    public List<VideoLabelResDTO> doToDtoList(List<VideoLabel> videoLabelList) {
        return  videoLabelList.stream().map(model -> {
            VideoLabelResDTO videoLabelResDTO =  BeanCopyUtil.convert(model, VideoLabelResDTO.class);
            return setTime(videoLabelResDTO, model);
        }).collect(Collectors.toList());
    }


}

