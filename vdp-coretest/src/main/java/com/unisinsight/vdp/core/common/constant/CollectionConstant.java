/*
 * www.unisinsight.com Inc.
 * Copyright (c) 2018 All Rights Reserved
 */
package com.unisinsight.vdp.core.common.constant;

/**
 * 视频收藏常量
 *
 * @author hujun [KF.hujunA@h3c.com]
 * @date 2018/11/01 19:35
 * @since 1.0
 */
public class CollectionConstant {

    /**
     * 展示资源树的不同功能，1：获取所有组织、设备节点的资源树（树状结构），
     * 2：获取设备资源树（含是否已创建任务的标识），3：获取设备资源树节点（平铺结构）。
     * 如果不设置该值，默认情况下返回第一种类型
     */
    public static final Integer FUNCTION_TYPE = 3;
    /**
     * 资源收藏sql查询拼接语句
     */
    public static final String GROUP_ID = "group_id = '";
}
