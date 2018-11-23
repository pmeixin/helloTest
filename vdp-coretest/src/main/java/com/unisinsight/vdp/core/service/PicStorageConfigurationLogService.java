package com.unisinsight.vdp.core.service;

import com.unisinsight.vdp.core.model.PicStorageConfigurationLog;

import java.util.List;

/**
 * TODO 图片云存储地址修改日志service
 *
 * @author liujiaji [HZ.liujiaji@h3c.com]
 * @date 2018/10/24 17:18
 * @since 1.0
 */
public interface PicStorageConfigurationLogService {

    /**
     * 新增
     * @param log {@link PicStorageConfigurationLog}
     * @return int  保存成功与否状态
     */
    int save(PicStorageConfigurationLog log);


    /**
     * 获取所有的日志
     * @return List<PresetDeviceResDTO>
     **/
    List<PicStorageConfigurationLog> listAll();


}
