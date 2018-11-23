package com.unisinsight.vdp.core.dto.request;

import java.io.Serializable;
/**
 *
 * @author zhoubiao [KF.zhoubiao@h3c.com]
 * @date 2018-10-24 17:52:53
 * @since 2.0
 */
public class AlgorithmRepositoryReqDTO implements Serializable {


    private static final long serialVersionUID = 6737220038114829912L;

    //镜像名称
    private String name;
    //镜像描述
    private String descriptions;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(String descriptions) {
        this.descriptions = descriptions;
    }
}
