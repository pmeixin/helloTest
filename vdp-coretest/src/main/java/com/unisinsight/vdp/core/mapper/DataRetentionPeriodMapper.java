package com.unisinsight.vdp.core.mapper;

import com.unisinsight.framework.common.base.Mapper;
import com.unisinsight.vdp.common.mda.response.PicStorageConfigurationResDTO;
import com.unisinsight.vdp.core.dto.request.DataRetentionPeriodReqDTO;
import com.unisinsight.vdp.core.model.DataRetentionPeriod;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Options;
import tk.mybatis.mapper.provider.base.BaseInsertProvider;

import java.util.List;

public interface DataRetentionPeriodMapper extends Mapper<DataRetentionPeriod> {


    @Override
    @InsertProvider(type = BaseInsertProvider.class, method = "dynamicSQL")
    @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
    int insertSelective(DataRetentionPeriod period);


    /**
     * 根据类型修改数据
     * @param period
     */
    void updateByType(DataRetentionPeriod period);

    /**
     * 根据数据类型获取留存期时间
     * @param type
     * @return
     */
    DataRetentionPeriod getPeriodByType(Integer type);

    /**
     * 根据数据类型删除留存期时间
     * @param type
     */
    void deleteByType(Integer type);

    /**
     * 删除所有数据
     */
    void deleteAll();

    /**
     * 查询多个数据
     * @param types
     * @return
     */
    List<PicStorageConfigurationResDTO> getConfigsByTypes(Integer...types);

}