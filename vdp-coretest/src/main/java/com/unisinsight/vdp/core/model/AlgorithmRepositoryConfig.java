package com.unisinsight.vdp.core.model;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 算法仓配置类
 *
 * @author zhoubiao [zhou.biao@unisinsight.com]
 * @date 2018/11/08
 * @since 1.0
 */
@Table(name = "algorithm_repository_config")
public class AlgorithmRepositoryConfig {
    /**
     * 模块id
     */
    @Id
    private String vdpId;

    /**
     * 算法仓id
     */
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
