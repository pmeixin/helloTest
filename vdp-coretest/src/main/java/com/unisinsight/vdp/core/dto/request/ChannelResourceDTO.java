/*
 * www.unisinsight.com Inc.
 * Copyright (c) 2018 All Rights Reserved
 */
package com.unisinsight.vdp.core.dto.request;


import java.io.Serializable;

/**
 * description
 *
 * @author daisike [dai.sike@h3c.com]
 * @date 2018/11/13 10:19
 * @since 1.0
 */
public class ChannelResourceDTO implements Serializable {

    private static final long serialVersionUID = -3635061887456361337L;

    /**
     * 通道类型；
     * 0-全部；1-视频输入通道；2-解码通道；3-报警输入通道；4-报警输出通道；5-卡口通道；10-动环通道。
     * 可以自定义传递多个类型，例如，"1,5"|
     */
    private String unitType;

    /**
     *  仅支持"通道名称"的模糊查询，如果多关键字空格分开，例如："南街 北街"|
     */
    private String keyWord;

    /**
     * 通道在线状态，-1：全部，0：离线，1：在线|
     */
    private Integer onlineStatus;

    /**
     * 通道是否标记：-1-全部；0-标记；1-未标记|
     */
    private Integer mark;

    /**
     * 页码
     */
    private Integer pageNum;

    /**
     * 每页数量
     */
    private Integer pageSize;

    /**
     * 排序方式 desc 或asc
     */
    private String order;

    public String getUnitType() {
        return unitType;
    }

    public void setUnitType(String unitType) {
        this.unitType = unitType;
    }

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }

    public Integer getOnlineStatus() {
        return onlineStatus;
    }

    public void setOnlineStatus(Integer onlineStatus) {
        this.onlineStatus = onlineStatus;
    }

    public Integer getMark() {
        return mark;
    }

    public void setMark(Integer mark) {
        this.mark = mark;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }
}
