/*
 * www.unisinsight.com Inc.
 * Copyright (c) 2018 All Rights Reserved
 */
package com.unisinsight.vdp.core.service.impl;

import com.unisinsight.framework.common.utils.BeanCopyUtil;
import com.unisinsight.framework.common.utils.User;
import com.unisinsight.framework.common.utils.UserHandler;
import com.unisinsight.vdp.core.mapper.PicStorageConfigurationLogMapper;
import com.unisinsight.vdp.core.model.PicStorageConfigurationLog;
import com.unisinsight.vdp.core.service.PicStorageConfigurationLogService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * 图片云存储地址、端口、容量修改日志serviceimpl
 * @author liujiaji [HZ.liujiaji@h3c.com]
 * @date 2018/10/24 17:58
 * @since 1.0
 */
@Service
public class PicStorageConfigurationLogServiceImpl implements PicStorageConfigurationLogService {

    @Resource
    private PicStorageConfigurationLogMapper logMapper;

    /**
     * 新增日志
     * @param log {@link PicStorageConfigurationLog}
     * @return
     */
    @Override
    public int save(PicStorageConfigurationLog log) {
        // 获取当前登录用户
        User user = UserHandler.getUser();
        //log.setUpdateUserName(user.getUserName());
        // 使用假数据
        log.setUpdateUserName("ljj");
        log.setUpdateTime(new Date());
        return logMapper.insertSelective(log);
    }


    /**
     * 获取所有的日志
     *
     * @return List<PicStorageConfigurationLog>
     **/
    @Override
    public List<PicStorageConfigurationLog> listAll() {
        return BeanCopyUtil.convertList(logMapper.selectAll(), PicStorageConfigurationLog.class);
    }
}