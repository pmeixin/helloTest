/*
 * www.unisinsight.com Inc.
 * Copyright (c) 2018 All Rights Reserved
 */
package com.unisinsight.vdp.core.service;
import com.unisinsight.vdp.core.dto.request.CollectionDeviceReqDTO;
import com.unisinsight.vdp.core.dto.response.CollectionDeviceResDTO;
import com.unisinsight.vdp.core.model.CollectionDevice;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

/**
 * 收藏视频资源Service
 *
 * @author wangshuai [KF.wangshuaiB@h3c.com]
 * @date 2018/09/18
 * @since 1.0
 */
public interface CollectionDeviceService  {

    /**
     * 新增
     * @param reqDTO {@link CollectionDeviceReqDTO}
     * @return {@link CollectionDeviceResDTO}
     */
    @Transactional(rollbackFor = Exception.class)
    CollectionDeviceResDTO save(CollectionDeviceReqDTO reqDTO);

    /**
     * 新批量新增
     * @param reqDTOList 资源集合
     * @return {@link CollectionDeviceResDTO}
     */
    @Transactional(rollbackFor = Exception.class)
    List<CollectionDeviceResDTO> batchSave(List<CollectionDeviceReqDTO> reqDTOList);

    /**
     * 通过主键删除
     * @param id 主键
     */
    void deleteById(Integer id);

    /**
    * 通过ID查找
    * @param id 主键
    * @return CollectionDeviceResDTO
    */
    CollectionDeviceResDTO findById(Integer id);

    /**
     * 资源模糊查询
     * @param name 资源点名称
     * @return  List<CollectionDeviceResDTO>
     */
    List<CollectionDeviceResDTO> searchKey(String name);

    /**
     * createTime和updateTime设置时间戳
     * @param collectionDeviceResDTO 返回前端DTO
     * @param collectionDevice 从数据库获取的Model
     * @return CollectionResDTO
     */
    CollectionDeviceResDTO setTime(CollectionDeviceResDTO collectionDeviceResDTO, CollectionDevice collectionDevice);

    /**
     * doList转DTOList，并设置时间戳类型字段
     * @param collectionDevice 监控点
     * @return CollectionDeviceResDTO
     **/
    List<CollectionDeviceResDTO> doToDtoList(List<CollectionDevice> collectionDevice);
}
