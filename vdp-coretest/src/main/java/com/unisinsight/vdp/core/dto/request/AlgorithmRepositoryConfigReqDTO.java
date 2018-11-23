package com.unisinsight.vdp.core.dto.request;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;

public class AlgorithmRepositoryConfigReqDTO implements Serializable {
    private static final long serialVersionUID = 6767120038114828919L;

    /**
     * 模块id
     */
    @JSONField(name="vdp_id")
    private String vdpId;

    /**
     * 算法仓id
     */
    @JSONField(name="algorithm_id")
    private String algorithmId;

    public String getVdpId() {
        return vdpId;
    }

    public void setVdpId(String vdpId) {
        this.vdpId = vdpId;
    }

    public String getAlgorithmId() {
        return algorithmId;
    }

    public void setAlgorithmId(String algorithmId) {
        this.algorithmId = algorithmId;
    }
}
