/*
 * www.unisinsight.com Inc.
 * Copyright (c) 2018 All Rights Reserved
 */
package com.unisinsight.vdp.core.controller;

import com.google.common.collect.Maps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.Map;

/**
 * description
 *
 * @author tanjiquan [KF.tanjiquan@h3c.com]
 * @date 2018/10/24 16:48
 * @since 1.0
 */
@RestController
@RequestMapping("/api/vdp/v1/core")
public class IndexController {
    private static final Logger logger = LoggerFactory.getLogger(IndexController.class);
    /**
     * 健康检查
     * @return 结果
     */
    @GetMapping("/check")
    public Map check() {
        Map m = Maps.newHashMap();
        m.put("status", "success");
        m.put("date", new Date());
        logger.info("vdp-core server is ok !");
        return m;
    }

}