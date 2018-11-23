/*
 * www.unisinsight.com Inc.
 * Copyright (c) 2018 All Rights Reserved
 */
package com.unisinsight.vdp.core.common.enums;

import com.unisinsight.framework.common.exception.ErrorCodeDefaultImpl;
import com.unisinsight.framework.common.exception.IErrorCode;

/**
 * description
 *
 * @author hujun [KF.hujunA@unisinsight.com]
 * @date 2018/10/11 10:48
 * @since 1.0
 */
public enum VideoErrorCode {

    /**
     * 系统编号 0104 : 视综平台
     * 错误编号
     * 第一位错误码类型：（0 通用异常（统一定义）、1 自定义异常）
     * 第二位为严重级别：（0 未定义严重级别、1 普通提示信息、2 错误、3 致命错误，其他类型暂时保留不用）
     * 后四位为系统错误码编码（建议：前两位为模块编号，后两位为模块异常编号）
     */
    GROUPID_AND_NAME_NOTNULL("0104110151", "资源组id和名称不能为空"),
    GROUP_NOT_EXIST("0104110152", "资源组id不能为空或资源组不存在"),
    PRESET_NAME_HAS_EXIST("0104110153", "预设点已存在"),
    PRESET_NOT_EXIST("0104110154", "预设点不存在"),
    CRUISEPATH_NAME_HAS_EXIST("0104110155", "巡航路径已存在"),
    CRUISEPATH_NOT_EXIST("0104110156", "巡航路径不存在"),
    CRUISEPRESET_LESS_TWO("0104110157", "关键点少于两个，不能删除"),
    PRESETDEVICE_MORE_TWO("0104110158", "新增巡航轨迹时，预设点至少有两个"),
    GROUP_NAME_HAS_EXIST("0104110159", "资源组已存在"),
    DUPLICATE_DEVICE_NAME("0104110160", "该监控点已经被收藏"),
    ID_NOT_EXIST("0104110161", "设备id不存在"),
    PRESETDEVICE_NOT_EXIST("0104110162", "预设点添加到巡航路径时必须存在"),
    PRESETDEVICE_DELETE_EXCEPTION("0104110163", "请先移除巡航路径中的预设点后重试"),
    ID_IS_DIFFERENT("0104110164", "预置点和巡航路径的设备id必须相同"),
    CRUISEPRESET_NOT_EXIST("0104110165", "关键点修改时必须存在"),
    GROUPID_IS_DIFFERENT("0104110166", "批量收藏时资源组id必须相同"),
    LABEL_ID_NOT_EXIST("0104110180", "标记Id不存在"),
    LABEL_TYPE_ERROR("0104110181", "只能对标记标签进行修改或删除操作"),
    LABEL_REPEAT("0104110182", "标签或锁不能加在相同位置上"),
    ;

    VideoErrorCode(String errorCode, String message) {
        this.error = new ErrorCodeDefaultImpl(errorCode, message);
    }

    public IErrorCode of(){
        return error;
    }

    private IErrorCode error;

    public IErrorCode getError() {
        return error;
    }

    void setError(IErrorCode error) {
        this.error = error;
    }
}


