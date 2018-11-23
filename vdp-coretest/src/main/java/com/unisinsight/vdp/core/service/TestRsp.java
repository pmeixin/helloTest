/*
 * www.unisinsight.com Inc.
 * Copyright (c) 2018 All Rights Reserved
 */
package com.unisinsight.vdp.core.service;

import com.alibaba.fastjson.JSON;
import com.unisinsight.vdp.core.dto.response.AnalysisTaskBatchDeleteResDTO;

/**
 * description
 *
 * @author daisike [dai.sike@h3c.com]
 * @date 2018/11/05 19:36
 * @since 1.0
 */
public class TestRsp {
    public static void main(String[] args) {
        System.out.println(JSON.toJSONString(new AnalysisTaskBatchDeleteResDTO()));
    }
}
