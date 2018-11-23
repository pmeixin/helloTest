package com.unisinsight.vdp.core.mapper;

import com.unisinsight.framework.common.base.Mapper;
import com.unisinsight.vdp.core.model.PresetDevice;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.provider.base.BaseInsertProvider;
import java.util.List;

/**
* 预设点mapper
* @author unisinsight  [KF.hujunA@h3c.com]
* @date   2018/10/17 15:53
* @since  1.0
*/
public interface PresetDeviceMapper extends Mapper<PresetDevice> {

    /**
     * 根据名称查询
     * @param name 预置点名称
     * @return 预置点信息集合
     */
    PresetDevice findByName(@Param("name") String name);

    /**
     * 根据设备id查询
     * @param id 设备id
     * @return 预设点信息集合
     */
    List<PresetDevice> findById(@Param("id") String id);

    /**
     * 重写插入方法，返回主键
     * @param record 预置点
     * @return 返回插入结果
     */
    @Override
    @InsertProvider(type = BaseInsertProvider.class, method = "dynamicSQL")
    @Options(useGeneratedKeys = true, keyColumn = "preset_device_id", keyProperty = "presetDeviceId")
    int insertSelective(PresetDevice record);
    /**
     * 根据主键集合查询
     * @param ids preset_device_id
     * @return 预设点信息集合
     */
    List<PresetDevice> findByIds(@Param("list") List<Integer> ids);
}