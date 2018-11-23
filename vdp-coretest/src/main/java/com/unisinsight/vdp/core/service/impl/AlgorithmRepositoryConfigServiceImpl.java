package com.unisinsight.vdp.core.service.impl;


import com.unisinsight.vdp.core.mapper.AlgrothmRepositoryConfigMapper;
import com.unisinsight.vdp.core.mapper.AnalysisTaskMapper;
import com.unisinsight.vdp.core.model.AlgorithmRepositoryConfig;
import com.unisinsight.vdp.core.service.AlgorithmRepositoryConfigService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


/**
 * 算法仓配置实现类
 *
 * @author zhoubiao [zhou.biao@unisinsight.com]
 * @date 2018/11/09
 * @since 1.0
 */

@Service
public class AlgorithmRepositoryConfigServiceImpl implements AlgorithmRepositoryConfigService {

    private static final Logger logger = LoggerFactory.getLogger(AnalysisTaskServiceImpl.class);


    @Resource
    private AlgrothmRepositoryConfigMapper  algrothmRepositoryConfigMapper;

    @Override
    public int addAlgorithmRepository(AlgorithmRepositoryConfig algorithmRepositoryConfig) {
        return algrothmRepositoryConfigMapper.insert(algorithmRepositoryConfig);
    }

    @Override
    public AlgorithmRepositoryConfig queryByVdpId(String vdpId) {
        return algrothmRepositoryConfigMapper.selectByPrimaryKey(vdpId);
    }

    @Override
    public int updateAlgorithmRepositoryConfig(AlgorithmRepositoryConfig algorithmRepositoryConfig) {
        return algrothmRepositoryConfigMapper.updateByPrimaryKey(algorithmRepositoryConfig);
    }
}
