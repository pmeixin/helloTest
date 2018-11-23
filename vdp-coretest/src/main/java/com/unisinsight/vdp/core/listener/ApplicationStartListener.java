/*
 * www.unisinsight.com Inc.
 * Copyright (c) 2018 All Rights Reserved
 */
package com.unisinsight.vdp.core.listener;

import com.unisinsight.vdp.core.component.DataPeriodInitExecutor;
import com.unisinsight.vdp.core.component.PicStorageDataInitExecutor;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 监听服务启动后,初始化存储配置信息
 *
 * @author liujiaji [liu.jiaji@unisinsight.com]
 * @date 2018/11/12 09:36
 * @since 1.0
 */
@Component
public class ApplicationStartListener implements ApplicationListener<ContextRefreshedEvent> {

    @Resource
    private PicStorageDataInitExecutor storageDataInitExecutor;

    @Resource
    private DataPeriodInitExecutor periodInitExecutor;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        // 启动服务将图片存储配置信息保存数据库
        new Thread(storageDataInitExecutor).start();
        // 启动服务时将所有留存期时间数据加载入数据库
        new Thread(periodInitExecutor).start();
    }


}