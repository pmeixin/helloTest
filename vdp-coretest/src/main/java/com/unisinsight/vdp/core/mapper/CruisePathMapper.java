package com.unisinsight.vdp.core.mapper;
import com.unisinsight.framework.common.base.Mapper;
import com.unisinsight.vdp.core.model.CruisePath;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.provider.base.BaseInsertProvider;
import java.util.List;

/**
* 巡航路径校验Mapper
* @author unisinsight  [KF.hujunA@h3c.com]
* @date   2018/10/17 15:52
* @since  1.0
*/
public interface CruisePathMapper extends Mapper<CruisePath> {

    /**
     * 新增根据名称查询
     * @param name 巡航路径名称
     * @return 巡航路径信息集合
     */
    CruisePath findByName(@Param("name") String name);

    /**
     * 修改时根据名称查询
     * @param name 巡航路径名称
     * @return 巡航路径信息集合
     */
    List<CruisePath> findByNameLessTwo(@Param("name") String name);

    /**
     * 根据设备id查询
     * @param id 设备id
     * @return 巡航路径信息集合
     */
    List<CruisePath> findById(@Param("id") String id);

    /**
     * 重写插入方法，返回主键
     * @param record 巡航路径
     * @return 插入返回值
     */
    @Override
    @InsertProvider(type = BaseInsertProvider.class, method = "dynamicSQL")
    @Options(useGeneratedKeys = true, keyColumn = "cruise_path_id", keyProperty = "cruisePathId")
    int insertSelective(CruisePath record);

}