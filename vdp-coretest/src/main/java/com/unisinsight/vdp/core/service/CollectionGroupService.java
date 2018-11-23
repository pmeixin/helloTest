/*
 * www.unisinsight.com Inc.
 * Copyright (c) 2018 All Rights Reserved
 */
package com.unisinsight.vdp.core.service;
import com.unisinsight.vdp.core.dto.request.CollectionGroupReqDTO;
import com.unisinsight.vdp.core.dto.response.CollectionGroupResDTO;
import com.unisinsight.vdp.core.model.CollectionGroup;
import java.util.List;

/**
 * 视频收藏组Service
 *
 * @author wangshuai [KF.wangshuaiB@h3c.com]
 * @date 2018/09/18
 * @since 1.0
 */
public interface CollectionGroupService  {
    /**
     * 新增
     * @param reqDTO {@link CollectionGroupReqDTO}
     * @return CollectionGroupResDTO
     */
    CollectionGroupResDTO save(CollectionGroupReqDTO reqDTO);

    /**
     * 通过主键删除
     * @param id 主键id
     */
    void deleteById(Integer id);

    /**
    * 更新
    * @param updateDTO {@link CollectionGroupReqDTO}
    * @return CollectionGroupResDTO
    */
    CollectionGroupResDTO update(CollectionGroupReqDTO updateDTO);

    /**
    * 通过ID查找
    * @param id 主键
    * @return CollectionGroupResDTO
    */
    CollectionGroupResDTO findById(Integer id);

    /**
     * 资源模糊查询
     * @param name 资源组名称
     * @return  List<CollectionGroupResDTO>
     */
    List<CollectionGroupResDTO> searchKey(String name);
    /**
     * 获取所有的组与下属资源
     * @return List<CollectionGroupResDTO>
     **/
    List<CollectionGroupResDTO> listAll();

    /**
     * createTime和updateTime设置时间戳
     * @param collectionGroupResDTO 返回前端DTO
     * @param collectionGroup 从数据库获取的Model
     * @return CollectionResDTO
     */
    CollectionGroupResDTO setTime(CollectionGroupResDTO collectionGroupResDTO, CollectionGroup collectionGroup);

    List<CollectionGroupResDTO> selectbp(int currentpage,int pagesize);

    List<CollectionGroupResDTO> selectbpp(int currentpage,int pagesize);

}
