package com.unisinsight.vdp.core.dto.request;

import com.unisinsight.vdp.core.dto.request.bean.ConditionInfo;
import com.unisinsight.vdp.core.dto.request.bean.PageInfo;
import com.unisinsight.vdp.core.dto.request.bean.SortInfo;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author zhoubiao [KF.zhoubiao@h3c.com]
 * @date 2018-10-24 20:20:53
 * @since 2.0
 */
public class AlgorithmRepositoryFilterReqDTO implements Serializable {

    private static final long serialVersionUID = 6767220038114829913L;

    private ArrayList<ConditionInfo> condition;

    private PageInfo page;

    public ArrayList<ConditionInfo> getCondition() {
        return condition;
    }

    public void setCondition(ArrayList<ConditionInfo> condition) {
        this.condition = condition;
    }

    public PageInfo getPage() {
        return page;
    }

    public void setPage(PageInfo page) {
        this.page = page;
    }

    public SortInfo getSort() {
        return sort;
    }

    public void setSort(SortInfo sort) {
        this.sort = sort;
    }

    private SortInfo sort;
}
