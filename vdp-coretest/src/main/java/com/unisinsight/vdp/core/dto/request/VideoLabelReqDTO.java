/*
 * www.unisinsight.com Inc.
 * Copyright (c) 2018 All Rights Reserved
 */
package com.unisinsight.vdp.core.dto.request;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;
import java.io.Serializable;

/**
 * 视频播放标记，前端请求DTO
 *
 * @author wangshuai [wang.shuai@unisinsight.com]
 * @date 2018/10/30
 * @since 1.0
 */
public class VideoLabelReqDTO implements Serializable {

  private static final long serialVersionUID = 2160337747763072593L;

  /**
   * 标记Id
   */
  private Long labelId;

  /**
   * 起始时间
   */
  private Long startTime;

  /**
   * 标记结束时间
   */
  private Long endTime;

  /**
   * 设备Id
   */
  @Length(min = 1, max = 32, message = "设备Id的长度范围在1-32之间")
  private String id;

  /**
   * 标记类型，1：标签，2：锁
   */
  @Range(min = 1, max = 2, message = "标记类型范围为1-2")
  private Integer type;

  /**
   * 标记说明
   */
  private String mark;


  public Long getStartTime() {
    return startTime;
  }

  public void setStartTime(Long startTime) {
    this.startTime = startTime;
  }

  public Long getEndTime() {
    return endTime;
  }

  public void setEndTime(Long endTime) {
    this.endTime = endTime;
  }

  public Long getLabelId() {
    return labelId;
  }

  public void setLabelId(Long labelId) {
    this.labelId = labelId;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public Integer getType() {
    return type;
  }

  public void setType(Integer type) {
    this.type = type;
  }

  public String getMark() {
    return mark;
  }

  public void setMark(String mark) {
    this.mark = mark;
  }

  @Override
  public String toString() {
    return "VideoLabelReqDTO{" + "labelId=" + labelId + ", startTime=" + startTime + ", endTime=" + endTime
            + ", id='" + id + '\'' + ", type=" + type + ", mark='" + mark + '\'' + '}';
  }
}
