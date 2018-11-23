package com.unisinsight.vdp.core.mapper;

import com.unisinsight.framework.common.base.Mapper;
import com.unisinsight.vdp.core.model.CollectionDevice;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Options;
import tk.mybatis.mapper.provider.base.BaseInsertProvider;

import java.util.List;

/**
 * 收藏视频资源Mapper
 *
 * @author wangshuai [KF.wangshuaiB@h3c.com]
 * @date 2018/09/18
 * @since 1.0
 */
public interface CollectionDeviceMapper extends Mapper<CollectionDevice> {
    /**
     * 重写插入方法，返回新增的主键
     * @param record CollectionDevice
     * @return 收藏对象
     */
    @Override
    @InsertProvider(type = BaseInsertProvider.class, method = "dynamicSQL")
    @Options(useGeneratedKeys = true, keyColumn = "collection_device_id", keyProperty = "collectionDeviceId")
    int insertSelective(CollectionDevice record);
    /**
     * 带返回主键的批量收藏
     * @param collectionDeviceList 多个监控点集合
     *
     */
    void batchInsert(List<CollectionDevice> collectionDeviceList);

    /**
     * 批量删除
     * @param ids 关键点集合
     */
    void deleteBatch(List<Long> ids);
}