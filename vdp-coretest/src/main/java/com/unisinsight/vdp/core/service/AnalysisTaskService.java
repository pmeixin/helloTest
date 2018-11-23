/*
 * www.unisinsight.com Inc.
 * Copyright (c) 2018 All Rights Reserved
 */
package com.unisinsight.vdp.core.service;
import com.unisinsight.framework.common.base.PageResult;
import com.unisinsight.vdp.core.dto.request.AnalysisTaskReqDTO;
import com.unisinsight.vdp.core.dto.response.AnalysisTaskBatchDeleteResDTO;
import com.unisinsight.vdp.core.dto.response.AnalysisTaskResDTO;

import java.util.List;
import java.util.Map;


/**
 * TODO description
 *
 * @author daisike [dai.sike@unisinsight.com]
 * @date 2018/10/09
 * @since 1.0
 */
public interface AnalysisTaskService  {

/**
     * 新增
     * @param reqDTO
     * @return
     */
    AnalysisTaskResDTO save(AnalysisTaskReqDTO reqDTO);


    /**
     * 批量删除
     * @param taskIds
     * @param taskType
     */
    AnalysisTaskBatchDeleteResDTO deleteList(List<String> taskIds, Integer taskType);

    /**
     * 分页查询
     * @param params
     * @return
     */
    PageResult queryList(Map<String,String> params);

    List<String> getCreatedDeviceList();
}
