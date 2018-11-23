/*
 * www.unisinsight.com Inc.
 * Copyright (c) 2018 All Rights Reserved
 */
package com.unisinsight.vdp.core.mapper;

import com.unisinsight.framework.common.base.Mapper;
import com.unisinsight.vdp.core.model.DataRetentionPeriodLog;
import com.unisinsight.vdp.core.model.PicStorageConfigurationLog;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Options;
import tk.mybatis.mapper.provider.base.BaseInsertProvider;

/**
 * 图片云存储地址修改日志mapper
 *
 * @author liujiaji [HZ.liujiaji@h3c.com]
 * @date 2018/10/24 17:39
 * @since 1.0
 */
public interface PicStorageConfigurationLogMapper extends Mapper<PicStorageConfigurationLog> {

    @Override
    @InsertProvider(type = BaseInsertProvider.class, method = "dynamicSQL")
    @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
    int insertSelective(PicStorageConfigurationLog record);
}