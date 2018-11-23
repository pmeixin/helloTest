package com.unisinsight.vdp.core.dto.response;


import com.unisinsight.vdp.core.dto.response.bean.AlgorithmRepositoryDataInfo;
import com.unisinsight.vdp.core.dto.response.bean.PageInfo;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author zhoubiao [KF.zhoubiao@h3c.com]
 * @date 2018-10-24 17:49:53
 * @since 2.0
 */
public class AlgorithmRepositoryResDTO implements Serializable {

    private static final long serialVersionUID = 1619598635317738992L;
    /**
     * 分页信息响应
     */
    private PageInfo pageInfo;

    /**
     * 结果信息列表
     */
    private ArrayList<AlgorithmRepositoryDataInfo> data;

    public PageInfo getPageInfo() {
        return pageInfo;
    }

    public void setPageInfo(PageInfo pageInfo) {
        this.pageInfo = pageInfo;
    }

    public ArrayList<AlgorithmRepositoryDataInfo> getData() {
        return data;
    }

    public void setData(ArrayList<AlgorithmRepositoryDataInfo> data) {
        this.data = data;
    }
}
