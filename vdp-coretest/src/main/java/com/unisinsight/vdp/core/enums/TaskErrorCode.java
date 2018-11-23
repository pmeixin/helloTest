/*
 * www.unisinsight.com Inc.
 * Copyright (c) 2018 All Rights Reserved
 */
package com.unisinsight.vdp.core.enums;

import com.unisinsight.framework.common.exception.ErrorCodeDefaultImpl;
import com.unisinsight.framework.common.exception.IErrorCode;

/**
 * description
 *
 * @author daisike [dai.sike@unisinsight.com]
 * @date 2018/10/08 20:50
 * @since 1.0
 */
public enum TaskErrorCode {

    /**
     * 错误码说明：采用10位数字，分为两段系统编号+错误编号，前4位表示系统编号，0104表示视综平台
     * 后六位表示错误编号，
     * 第一位 0 通用异常（统一定义）、1 自定义异常
     * 第二位 表示严重级别（1 普通提示信息、 2 错误、3 致命错误）
     * 后四位 系统错误码编码---前两位模块编号（ 01 公共业务 02 人脸 03 车辆 04 布控 05 图上监控），后两位位模块异常编号
     *
     *
     */
    TASK_DELETE_FAIL("0104110203","删除解析任务失败")

    ;
    private IErrorCode error;

    TaskErrorCode(String errorCode, String message){
        error = new ErrorCodeDefaultImpl(errorCode, message);
    }

    public IErrorCode of(){
        return error;
    }

    public String getErrorCode(){
        return error.getErrorCode();
    }

    public String getMessage(){
        return error.getMessage();
    }
}
