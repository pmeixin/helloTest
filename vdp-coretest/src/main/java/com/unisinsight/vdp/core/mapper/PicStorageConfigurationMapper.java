package com.unisinsight.vdp.core.mapper;

import com.unisinsight.framework.common.base.Mapper;
import com.unisinsight.vdp.common.mda.response.PicStorageConfigurationResDTO;
import com.unisinsight.vdp.core.model.PicStorageConfigurationLog;
import com.unisinsight.vdp.core.model.PicStorageConfiguration;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.provider.base.BaseInsertProvider;

import java.util.List;

public interface PicStorageConfigurationMapper extends Mapper<PicStorageConfiguration> {

    @Override
    @InsertProvider(type = BaseInsertProvider.class, method = "dynamicSQL")
    @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
    int insertSelective(PicStorageConfiguration record);

    /**
     * 根据类型修改数据
     * @param info
     */
    void updateByType(PicStorageConfiguration info);

    /**
     * 根据数据类型获取存储信息
     * @param type
     * @return
     */
    PicStorageConfiguration getInfoByType(Integer type);


    /**
     * 根据数据类型删除存储信息
     * @param type
     */
    void deleteByType(Integer type);

    /**
     * 删除所有数据
     */
    void deleteAll();


    /**
     * 刷新所有数据的剩余容量
     * @param remainCapacity
     */
    void updateAllRemainCapacity(Integer remainCapacity);


    List<PicStorageConfiguration> getConfigsByTypes(@Param(value = "types") List<Integer> types);

}