/*
 * www.unisinsight.com Inc.
 * Copyright (c) 2018 All Rights Reserved
 */
package com.unisinsight.vdp.core.manager.mapper;
import com.unisinsight.vdp.core.mapper.CollectionGroupMapper;
import com.unisinsight.vdp.core.model.CollectionGroup;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;

/**
 * 视频巡逻收藏资源MapperManager
 *
 * @author wangshuai [KF.wangshuaiB@h3c.com]
 * @date 2018/09/25 11:35
 * @since 1.0
 */
@Component
public class CollectionGroupMapperManager extends AbstractMapperManager<CollectionGroup, Integer> {

  /**
   * 注入collectionGroupMapper
   */
  @Resource
  private CollectionGroupMapper collectionGroupMapper;
  /**
   * 查询资源组是否重名
   * @param name 资源组名称
   * @return true 资源组重名，false 资源组不重名
   */
  public boolean validCollectionGroupName(String name) {
    return (collectionGroupMapper.findByName(name) != null);
  }

  /**
   * 查询数据库中资源组是否存在
   * @param id 资源组id
   * @return true 资源组存在，false 资源组不存在
   */
  public boolean validGroupId(Integer id) {
    return collectionGroupMapper.selectByPrimaryKey(id) != null;
  }
}
