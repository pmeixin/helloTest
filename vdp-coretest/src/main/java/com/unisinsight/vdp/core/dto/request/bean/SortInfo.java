package com.unisinsight.vdp.core.dto.request.bean;

import java.io.Serializable;

/**
 *
 * @author zhoubiao [KF.zhoubiao@h3c.com]
 * @date 2018-10-24 20:05:53
 * @since 2.0
 */
public class SortInfo implements Serializable {
    private static final long serialVersionUID = -6367220038114829916L;

    private String key;

    private String value;

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
}
