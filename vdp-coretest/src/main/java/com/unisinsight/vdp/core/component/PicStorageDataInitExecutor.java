/*
 * www.unisinsight.com Inc.
 * Copyright (c) 2018 All Rights Reserved
 */
package com.unisinsight.vdp.core.component;

import com.alibaba.fastjson.JSON;
import com.unisinsight.framework.common.exception.BaseErrorCode;
import com.unisinsight.framework.common.exception.BaseException;
import com.unisinsight.vdp.core.common.utils.RedisUtils;
import com.unisinsight.vdp.core.dto.response.bean.PicStorageInfo;
import com.unisinsight.vdp.core.dto.response.bean.PicStorageStrategy;
import com.unisinsight.vdp.core.model.PicStorageConfiguration;
import com.unisinsight.vdp.core.service.PicStorageConfigurationService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.List;

import static com.unisinsight.vdp.common.constants.MDAConstant.PIC_STORAGE_CONFIG;
import static com.unisinsight.vdp.common.constants.MDAConstant.PIC_STORAGE_REDIS_PREFIX;

/**
 * 初始化图片存储配置数据线程
 *
 * @author liujiaji [liu.jiaji@unisinsight.com]
 * @date 2018/11/12 09:57
 * @since 1.0
 */
@Component
public class PicStorageDataInitExecutor implements Runnable {

    @Resource
    private RestTemplate restTemplate;

    @Resource
    private RedisUtils redisUtils;

    @Resource
    private PicStorageConfigurationService storageConfigurationService;

    /**
     * 图片配置MDA远程地址
     */
    @Value(value = "${mda.url}")
    private String mdaPath;


    @Override
    public void run() {
        initPicData2DB();
    }

    /**
     * 启动服务将图片存储配置信息保存数据库
     */
    public void initPicData2DB() {
        try {
            String raw = restTemplate.getForObject(mdaPath + PIC_STORAGE_CONFIG, String.class);
            PicStorageInfo configuration = JSON.parseObject(raw, PicStorageInfo.class);
            //获取剩余容量
            Integer remainCapacity = configuration.getRemainCapacity();
            //获取配置信息
            List<PicStorageStrategy> storageInfo = configuration.getStorageConfigEntityList();
            //清空数据库
            storageConfigurationService.deleteAll();
            // 清空redis
            for (int i = 1; i < 11; i++) {
                redisUtils.delete(PIC_STORAGE_REDIS_PREFIX + i);
            }
            //循环存数据库
            for (PicStorageStrategy info : storageInfo) {
                PicStorageConfiguration storageConfiguration = new PicStorageConfiguration();
                // 更新数据库
                BeanUtils.copyProperties(info, storageConfiguration);
                storageConfiguration.setRemainCapacity(remainCapacity);
                storageConfigurationService.saveOrUpdate(storageConfiguration);
            }
        } catch (Exception e){
            e.printStackTrace();
            throw BaseException.of(BaseErrorCode.NETWORK_ERROR.of(), "网络错误,数据加载失败" + e.getMessage());
        }
    }
}