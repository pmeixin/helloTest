package com.unisinsight.vdp.core.service;

import com.unisinsight.vdp.core.model.AlgorithmRepositoryConfig;

/**
 * 算法仓配置
 *
 * @author zhoubiao [zhou.biao@unisinsight.com]
 * @date 2018/11/08
 * @since 1.0
 */
public interface AlgorithmRepositoryConfigService {
    int addAlgorithmRepository(AlgorithmRepositoryConfig algorithmRepositoryConfig);

    AlgorithmRepositoryConfig queryByVdpId(String vdpId);

    int updateAlgorithmRepositoryConfig(AlgorithmRepositoryConfig algorithmRepositoryConfig);

}
