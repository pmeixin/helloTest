package com.unisinsight.vdp.core.dto.response.bean;

import java.io.Serializable;

public class PageInfo implements Serializable {
    private static final long serialVersionUID = -2442633768111985318L;

    private Integer total_element;

    public Integer getTotal_element() {
        return total_element;
    }

    public void setTotal_element(Integer total_element) {
        this.total_element = total_element;
    }
}
