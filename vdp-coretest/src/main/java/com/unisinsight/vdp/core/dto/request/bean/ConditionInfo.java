package com.unisinsight.vdp.core.dto.request.bean;

import java.io.Serializable;

/**
 *
 * @author zhoubiao [KF.zhoubiao@h3c.com]
 * @date 2018-10-24 19:55:53
 * @since 2.0
 */
public class ConditionInfo implements Serializable {
    private static final long serialVersionUID = -6367220038114829914L;

    /**
     * 字段名称
     */
    private String key;

    /**
     * 字段值
     */
    private String value;

    /**

     * 逻辑关系符，详见备注逻辑关系符表定义
     */
    private String operator;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

}
