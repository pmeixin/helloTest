package com.unisinsight.vdp.core.dto.request.bean;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;

/**
 *
 * @author zhoubiao [KF.zhoubiao@h3c.com]
 * @date 2018-10-24 19:56:53
 * @since 2.0
 */
public class PageInfo implements Serializable {
    private static final long serialVersionUID = -6367220038114829915L;

    /**
     * 偏移量
     */
    private Integer offset;

    /**
     * 限制条目数
     */
    private Integer limit;

    /**
     * 是否查询总数，0--不查询总数，1--查询总数
     */
    @JSONField(name="query_count")
    private Integer queryCount;

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getQueryCount() {
        return queryCount;
    }

    public void setQueryCount(Integer queryCount) {
        this.queryCount = queryCount;
    }
}
