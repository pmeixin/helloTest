package com.unisinsight.vdp.core.controller;

import com.unisinsight.vdp.core.dto.request.AlgorithmRepositoryConfigReqDTO;
import com.unisinsight.vdp.core.dto.response.AlgorithmRepositoryConfigResDTO;
import com.unisinsight.vdp.core.model.AlgorithmRepositoryConfig;
import com.unisinsight.vdp.core.service.AlgorithmRepositoryConfigService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/vdp/v1/core/algorithm-repository-config")
public class AlgorithmRepositoryConfigController {

    @Resource
    private AlgorithmRepositoryConfigService algorithmRepositoryConfigService;

    @PostMapping
    public String addAlgorithmRepositoryConfig(@RequestBody @Valid AlgorithmRepositoryConfigReqDTO algorithmRepositoryConfigReqDTO){
        String result = null;
        AlgorithmRepositoryConfig algorithmRepositoryConfig = new AlgorithmRepositoryConfig();
        algorithmRepositoryConfig.setVdpId(algorithmRepositoryConfigReqDTO.getVdpId());
        algorithmRepositoryConfig.setAlgorithmId(algorithmRepositoryConfigReqDTO.getAlgorithmId());
        result = algorithmRepositoryConfigService.addAlgorithmRepository(algorithmRepositoryConfig)+"";
        return result;
    }

    @PostMapping("/update")
    public String updateAlgorithmRepositoryConfig(@RequestBody @Valid AlgorithmRepositoryConfigReqDTO algorithmRepositoryConfigReqDTO){
        String result = null;
        AlgorithmRepositoryConfig algorithmRepositoryConfig = new AlgorithmRepositoryConfig();
        algorithmRepositoryConfig.setVdpId(algorithmRepositoryConfigReqDTO.getVdpId());
        algorithmRepositoryConfig.setAlgorithmId(algorithmRepositoryConfigReqDTO.getAlgorithmId());
        result = algorithmRepositoryConfigService.updateAlgorithmRepositoryConfig(algorithmRepositoryConfig)+"";
        return result;
    }

    @GetMapping("/{id}")
    public AlgorithmRepositoryConfigResDTO queryAlgorithmRepositoryConfig(@PathVariable String id){

        AlgorithmRepositoryConfig algorithmRepositoryConfig = algorithmRepositoryConfigService.queryByVdpId(id);
        AlgorithmRepositoryConfigResDTO algorithmRepositoryConfigResDTO =
                new AlgorithmRepositoryConfigResDTO();
        algorithmRepositoryConfigResDTO.setVdpId(algorithmRepositoryConfig.getVdpId());
        algorithmRepositoryConfigResDTO.setAlgorithmId(algorithmRepositoryConfig.getAlgorithmId());
        return algorithmRepositoryConfigResDTO;
    }
}
