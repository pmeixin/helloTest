package com.unisinsight.vdp.core.mapper;
import com.unisinsight.framework.common.base.Mapper;
import com.unisinsight.vdp.core.model.CollectionGroup;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.provider.base.BaseInsertProvider;
import java.util.List;

/**
 * 视频收藏组Mapper
 *
 * @author wangshuai [KF.wangshuaiB@h3c.com]
 * @date 2018/09/18
 * @since 1.0
 */
public interface CollectionGroupMapper extends Mapper<CollectionGroup> {
    /**
     * 返回单条插入语句的主键
     * @param record 资源组
     * @return 资源组信息
     */
    @Override
    @InsertProvider(type = BaseInsertProvider.class, method = "dynamicSQL")
    @Options(useGeneratedKeys = true, keyColumn = "group_id", keyProperty = "groupId")
    int insertSelective(CollectionGroup record);

    /**
     * 根据名称查询
     * @param name 资源组名称
     * @return CollectionGroup
     */
    CollectionGroup findByName(@Param("name") String name);

    /**
     * 资源组模糊查询
     * @param name 资源组名称
     * @return  List<CollectionGroup>
     */
    List<CollectionGroup> searchKey(@Param("name") String name);
}