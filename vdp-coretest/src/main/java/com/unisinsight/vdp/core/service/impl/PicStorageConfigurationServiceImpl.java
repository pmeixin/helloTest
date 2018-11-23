/*
 * www.unisinsight.com Inc.
 * Copyright (c) 2018 All Rights Reserved
 */
package com.unisinsight.vdp.core.service.impl;

import com.alibaba.fastjson.JSON;
import com.unisinsight.framework.common.exception.BaseErrorCode;
import com.unisinsight.framework.common.exception.BaseException;
import com.unisinsight.vdp.common.mda.response.PicStorageConfigurationResDTO;
import com.unisinsight.vdp.core.common.utils.RedisUtils;
import com.unisinsight.vdp.core.dto.request.PicStorageConfigReq;
import com.unisinsight.vdp.core.dto.request.PicStorageConfigurationReqDTO;
import com.unisinsight.vdp.core.dto.response.bean.PicStorageInfo;
import com.unisinsight.vdp.core.dto.response.bean.PicStorageStrategy;
import com.unisinsight.vdp.core.mapper.PicStorageConfigurationMapper;
import com.unisinsight.vdp.core.model.PicStorageConfiguration;
import com.unisinsight.vdp.core.service.PicStorageConfigurationService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

import static com.unisinsight.vdp.common.constants.MDAConstant.PIC_STORAGE_CONFIG;
import static com.unisinsight.vdp.common.constants.MDAConstant.PIC_STORAGE_REDIS_PREFIX;

/**
 * 本地图片存储策略数据service
 *
 * @author liujiaji [liu.jiaji@unisinsight.com]
 * @date 2018/11/05 19:07
 * @since 1.0
 */
@Service
public class PicStorageConfigurationServiceImpl implements PicStorageConfigurationService {

    @Resource
    private RestTemplate restTemplate;

    @Resource
    private RedisUtils redisUtils;

    @Resource
    private PicStorageConfigurationMapper infoMapper;

    @Value(value = "${mda.url}")
    private String mdaPath;

    /**
     * redis缓存命名空间
     */
    public static final String PIC_CACHE_NAME_SPACE = "picStorage";

    /**
     * redis缓存key前缀
     */
    public static final String PIC_CACHE_KEY = "picConfigRedis";


    @Override
    public void saveOrUpdate(PicStorageConfiguration configuration) {
        // 依据类型判断新增/修改
        if (infoMapper.getInfoByType(configuration.getDataType()) != null) {
            infoMapper.updateByType(configuration);
        } else {
            infoMapper.insertSelective(configuration);
        }
    }


    @Override
    public PicStorageConfigurationResDTO updateByResDTO(PicStorageConfigurationReqDTO reqDTO) {
        if (reqDTO.getStorageIp() == null || reqDTO.getStoragePort() == null) {
            throw BaseException.of(BaseErrorCode.NETWORK_ERROR.of(), "参数错误,ip端口不能为空");
        }
        PicStorageConfigurationResDTO resDTO = new PicStorageConfigurationResDTO();
        try {
            // 获取参数
            Integer dataType = reqDTO.getDataType();
            Integer capacity = reqDTO.getCapacity();
            Integer strategy = reqDTO.getStrategy();
            String storageIp = reqDTO.getStorageIp();
            Integer storagePort = reqDTO.getStoragePort();
            // 先查询是否有该数据
            String raw = restTemplate.getForObject(mdaPath + PIC_STORAGE_CONFIG + "?data_type=" + reqDTO.getDataType(), String.class);
            PicStorageInfo configuration = JSON.parseObject(raw, PicStorageInfo.class);
            // 为空进行创建
            List<PicStorageStrategy> entityList = configuration.getStorageConfigEntityList();
            if (entityList == null || entityList.size() == 0) {
                // 如果是新建，则需要ip 和 端口
                PicStorageConfigReq configReq = new PicStorageConfigReq();
                configReq.setData_type(dataType);
                configReq.setCapacity(capacity);
                configReq.setStrategy(strategy);
                configReq.setStorage_ip(storageIp);
                configReq.setStorage_port(storagePort);
                restTemplate.postForObject(mdaPath + PIC_STORAGE_CONFIG, configReq, Object.class);
            } else {
                // 如果修改了端口或者ip,则是在修改集群,存储配置只保留当前图片配置
                PicStorageStrategy picStorageStrategy = entityList.get(0);
                if (!picStorageStrategy.getStorageIp().equals(reqDTO.getStorageIp()) || !picStorageStrategy.getStoragePort().equals(reqDTO.getStoragePort())) {
                    restTemplate.put(mdaPath + PIC_STORAGE_CONFIG + "?data_type=" + dataType + "&capacity=" + capacity + "&strategy=" + strategy + "&storage_ip=" + storageIp + "&storage_port=" + storagePort, Object.class);
                    // 清空数据库
                    infoMapper.deleteAll();
                    // 清空redis
                    for (int i = 1; i < 11; i++) {
                        redisUtils.delete(PIC_STORAGE_REDIS_PREFIX + i);
                    }
                } else {
                    // 不为空进行修改
                    restTemplate.put(mdaPath + PIC_STORAGE_CONFIG + "?data_type=" + dataType + "&capacity=" + capacity + "&strategy=" + strategy, Object.class);
                }
            }
            // 修改/新建成功后查询数据
            String updateRaw = restTemplate.getForObject(mdaPath + PIC_STORAGE_CONFIG + "?data_type=" + reqDTO.getDataType(), String.class);
            PicStorageInfo updateData = JSON.parseObject(updateRaw, PicStorageInfo.class);
            // 更新本地数据库/Redis
            PicStorageStrategy storageStrategy = updateData.getStorageConfigEntityList().get(0);
            // 剩余容量
            Integer remainCapacity = updateData.getRemainCapacity();
            PicStorageConfiguration storageConfiguration = new PicStorageConfiguration();
            BeanUtils.copyProperties(storageStrategy, storageConfiguration);
            BeanUtils.copyProperties(storageStrategy, resDTO);
            storageConfiguration.setRemainCapacity(remainCapacity);
            resDTO.setRemainCapacity(remainCapacity);
            // 更新数据库
            saveOrUpdate(storageConfiguration);
            // 更新redis
            syncData2Redis(storageConfiguration);
        } catch (Exception e) {
            e.printStackTrace();
            throw BaseException.of(BaseErrorCode.NETWORK_ERROR.of(), "网络错误,保存失败");
        }
        return resDTO;
    }


    @Override
    @Cacheable(value = PIC_CACHE_NAME_SPACE, key = "#type")
    public PicStorageConfigurationResDTO getInfoByType(String type) {
        PicStorageConfigurationResDTO resDTO = new PicStorageConfigurationResDTO();
        Integer num = Integer.valueOf(type.substring(PIC_CACHE_KEY.length()));
        // c
        PicStorageConfiguration configuration = infoMapper.getInfoByType(num);
        if (configuration != null) {
            BeanUtils.copyProperties(configuration, resDTO);
        } else {
            // 查询远程是否有该数据
            String raw = restTemplate.getForObject(mdaPath + PIC_STORAGE_CONFIG + "?data_type=" + num, String.class);
            PicStorageInfo remote = JSON.parseObject(raw, PicStorageInfo.class);
            List<PicStorageStrategy> entityList = remote.getStorageConfigEntityList();
            // 如果远程没有数据
            if (entityList == null || entityList.size() == 0) {
                // 存储策略默认为满覆盖
                resDTO.setStrategy(0);
                // 如果有数据
            } else {
                BeanUtils.copyProperties(entityList.get(0), resDTO);
                resDTO.setRemainCapacity(remote.getRemainCapacity());
                configuration = new PicStorageConfiguration();
                BeanUtils.copyProperties(resDTO, configuration);
                // 存入数据库
                saveOrUpdate(configuration);
            }
        }
        return resDTO;
    }

    @Override
    public void deleteByType(Integer type) {
        infoMapper.deleteByType(type);
    }

    @Override
    public void deleteAll() {
        infoMapper.deleteAll();
    }

    @Override
    public void updateAllRemainCapacity(Integer remainCapacity) {
        infoMapper.updateAllRemainCapacity(remainCapacity);
    }

    @Override
    public List<PicStorageConfigurationResDTO> getConfigsByTypes(List<Integer> types) {
        List<PicStorageConfiguration> configs = infoMapper.getConfigsByTypes(types);
        List<PicStorageConfigurationResDTO> resDTOs = new ArrayList<>();
        for (PicStorageConfiguration configuration : configs) {
            PicStorageConfigurationResDTO resDTO = new PicStorageConfigurationResDTO();
            BeanUtils.copyProperties(configuration, resDTO);
            resDTOs.add(resDTO);
        }
        return resDTOs;
    }

    /**
     * 同步数据到redis
     *
     * @param configuration
     */
    private void syncData2Redis(PicStorageConfiguration configuration) {
        // 修改redis数据
        PicStorageConfigurationResDTO resDTO = new PicStorageConfigurationResDTO();
        BeanUtils.copyProperties(configuration, resDTO);
        // 向命名空间下设置redis数据
        redisUtils.set(PIC_STORAGE_REDIS_PREFIX + resDTO.getDataType(), resDTO);
    }
}