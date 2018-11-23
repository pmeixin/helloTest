/*
 * www.unisinsight.com Inc.
 * Copyright (c) 2018 All Rights Reserved
 */
package com.unisinsight.vdp.core.manager.mapper;
import com.unisinsight.vdp.core.mapper.CruisePathMapper;
import com.unisinsight.vdp.core.model.CruisePath;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.entity.Condition;
import javax.annotation.Resource;

/**
 * 巡航路径校验MapperManager
 *
 * @author hujun [KF.hujunA@h3c.com]
 * @date 2018/10/11 14:41
 * @since 1.0
 */
@Component
public class CruisePathMapperManager extends AbstractMapperManager<CruisePath, Integer> {
    /**
     * 注入cruisePathMapper
     */
    @Resource
    private CruisePathMapper cruisePathMapper;
    /**
     * 查询数据库判断巡航路径是否重名--新增
     * @param name 巡航路径名称
     * @return true 巡航路径重名，false 巡航路径不重名
     */
    public boolean validCruisePathName(String name) {
        return cruisePathMapper.findByName(name) != null;
    }

    /**
     * 查询数据库判断巡航路径是否重名--修改
     * @param name 巡航路径名称
     * @return true 巡航路径重名，false 巡航路径不重名
     */
    public boolean validCruisePathNameLessTwo(String name) {
        return cruisePathMapper.findByNameLessTwo(name).size() > 1;
    }

    /**
     * 查询数据库中巡航路径是否存在
     * @param id 预置点id
     * @return true 巡航路径存在，false 巡航路径不存在
     */
    public boolean validCruisePathId(Integer id) {
        return cruisePathMapper.selectByPrimaryKey(id) != null;
    }

    /**
     * 查询数据库中设备id是否存在
     * @param id 设备id
     * @return true 预设点存在，false 预设点不存在
     */
    public boolean validId(String id) {
        Condition condition = new Condition(CruisePath.class);
        condition.createCriteria().andCondition("id = '" + id + "'");
        return !cruisePathMapper.selectByCondition(condition).isEmpty();
    }


}


