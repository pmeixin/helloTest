package com.unisinsight.vdp.core.mapper;

import com.unisinsight.framework.common.base.Mapper;
import com.unisinsight.vdp.core.model.CruisePath;
import com.unisinsight.vdp.core.model.DataRetentionPeriodLog;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Options;
import tk.mybatis.mapper.provider.base.BaseInsertProvider;

/**
 * mapper
 *
 * @author liujiaji [HZ.liujiaji@h3c.com]
 * @date 2018/10/24 17:39
 * @since 1.0
 */
public interface DataRetentionPeriodLogMapper extends Mapper<DataRetentionPeriodLog> {

    @Override
    @InsertProvider(type = BaseInsertProvider.class, method = "dynamicSQL")
    @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
    int insertSelective(DataRetentionPeriodLog record);
}