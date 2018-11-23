package com.unisinsight.vdp.core.service;


import com.unisinsight.vdp.core.model.DataRetentionPeriodLog;

import java.util.List;

/**
 * TODO 数据存留期时间修改日志service
 *
 * @author liujiaji [HZ.liujiaji@h3c.com]
 * @date 2018/10/24 17:18
 * @since 1.0
 */
public interface DataRetentionPeriodLogService {
    /**
     * 新增
     * @param log {@link DataRetentionPeriodLog}
     * @return int  保存成功与否状态
     */
    int save(DataRetentionPeriodLog log);


    /**
     * 获取所有的日志
     * @return List<DataRetentionPeriodLog>
     **/
    List<DataRetentionPeriodLog> listAll();


}
