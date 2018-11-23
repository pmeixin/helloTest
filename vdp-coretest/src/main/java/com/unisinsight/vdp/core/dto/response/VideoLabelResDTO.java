/*
 * www.unisinsight.com Inc.
 * Copyright (c) 2018 All Rights Reserved
 */
package com.unisinsight.vdp.core.dto.response;

import java.io.Serializable;

/**
 * 视频播放标记，前端参数返回DTO
 *
 * @author wangshuai [wang.shuai@unisinsight.com]
 * @date 2018/10/30
 * @since 1.0
 */
public class VideoLabelResDTO implements Serializable {

  private static final long serialVersionUID = 5505044374289449837L;

  /**
   * 视频标记id
   */
  private Long labelId;

  /**
   * 视频设备Id
   */
  private String id;

  /**
   * 标记开始时间
   */
  private Long startTime;

  /**
   * 标记结束时间
   */
  private Long endTime;

  /**
   * 标记内容
   */
  private String mark;

  /**
   * 标记类型：1标签、2锁
   */
  private Integer type;

  /**
   * 创建时间
   */
  private Long createTime;

  /**
   * 更新时间
   */
  private Long updateTime;

  /**
   * 添加人用户名
   */
  private String createUserName;

  /**
   * 添加人用户code
   */
  private String createUserCode;

  /**
   * 更新人用户名
   */
  private String updateUserName;

  /**
   * 更新人用户code
   */
  private String updateUserCode;

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

  public String getMark() {
    return mark;
  }

  public void setMark(String mark) {
    this.mark = mark;
  }

  public Integer getType() {
    return type;
  }

  public void setType(Integer type) {
    this.type = type;
  }

  public Long getCreateTime() {
    return createTime;
  }

  public void setCreateTime(Long createTime) {
    this.createTime = createTime;
  }

  public Long getUpdateTime() {
    return updateTime;
  }

  public void setUpdateTime(Long updateTime) {
    this.updateTime = updateTime;
  }

  public String getCreateUserName() {
    return createUserName;
  }

  public void setCreateUserName(String createUserName) {
    this.createUserName = createUserName;
  }

  public String getCreateUserCode() {
    return createUserCode;
  }

  public void setCreateUserCode(String createUserCode) {
    this.createUserCode = createUserCode;
  }

  public String getUpdateUserName() {
    return updateUserName;
  }

  public void setUpdateUserName(String updateUserName) {
    this.updateUserName = updateUserName;
  }

  public String getUpdateUserCode() {
    return updateUserCode;
  }

  public void setUpdateUserCode(String updateUserCode) {
    this.updateUserCode = updateUserCode;
  }

  @Override
  public String toString() {
    return "VideoLabelResDTO{" + "labelId=" + labelId + ", id='" + id + '\'' + ", startTime=" + startTime
            + ", endTime=" + endTime + ", mark='" + mark + '\'' + ", type='" + type + '\'' + ", createTime=" + createTime
            + ", updateTime=" + updateTime + ", createUserName='" + createUserName + '\''
            + ", createUserCode='" + createUserCode + '\'' + ", updateUserName='" + updateUserName + '\''
            + ", updateUserCode='" + updateUserCode + '\'' + '}';
  }
}
