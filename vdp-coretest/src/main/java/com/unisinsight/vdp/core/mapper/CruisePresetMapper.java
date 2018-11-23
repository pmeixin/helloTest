package com.unisinsight.vdp.core.mapper;

import com.unisinsight.framework.common.base.Mapper;
import com.unisinsight.vdp.core.model.CruisePreset;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
* 关键点mapper
* @author unisinsight  [KF.hujunA@h3c.com]
* @date   2018/10/17 15:53
* @since  1.0
*/
public interface CruisePresetMapper extends Mapper<CruisePreset> {

    /**
     * 批量删除
     * @param ids 关键点集合
     */
    void deleteBatch(List<Integer> ids);

    /**
     * 根据主键集合查询
     * @param ids cruise_preset_id
     * @return 预设点信息集合
     */
    List<CruisePreset> findByIds(@Param("list") List<Integer> ids);
}