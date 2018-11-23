package com.unisinsight.vdp.core.service.impl;

import com.unisinsight.framework.common.utils.BeanCopyUtil;
import com.unisinsight.framework.common.utils.User;
import com.unisinsight.framework.common.utils.UserHandler;
import com.unisinsight.vdp.core.mapper.DataRetentionPeriodLogMapper;
import com.unisinsight.vdp.core.model.DataRetentionPeriodLog;
import com.unisinsight.vdp.core.service.DataRetentionPeriodLogService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * 数据存留期时间修改日志serviceimpl
 *
 * @author liujiaji [HZ.liujiaji@h3c.com]
 * @date 2018/10/24 17:58
 * @since 1.0
 */
@Service
public class DataRetentionPeriodLogServiceImpl implements DataRetentionPeriodLogService {


    @Resource
    private DataRetentionPeriodLogMapper logMapper;


    /**
     * 新增
     *
     * @param log
     * @return int  保存成功与否状态
     */
    @Override
    public int save(DataRetentionPeriodLog log) {
        // 获取用户
        User user = UserHandler.getUser();
        log.setUpdateTime(new Date());
        // 用户获取不到,暂时使用假数据
        log.setUpdateUserName("ljj");
        return logMapper.insertSelective(log);
    }

    /**
     * 获取所有的日志
     *
     * @return List<DataRetentionPeriodLog>
     **/
    @Override
    public List<DataRetentionPeriodLog> listAll() {
        return BeanCopyUtil.convertList(logMapper.selectAll(), DataRetentionPeriodLog.class);
    }


}
