/*
 * www.unisinsight.com Inc.
 * Copyright (c) 2018 All Rights Reserved
 */
package com.unisinsight.vdp.core.component;

import com.unisinsight.framework.common.exception.BaseErrorCode;
import com.unisinsight.framework.common.exception.BaseException;
import com.unisinsight.vdp.core.common.enums.DataRetentionPeriodServiceEnum;
import com.unisinsight.vdp.core.common.enums.DataRetentionPeriodTypeEnum;
import com.unisinsight.vdp.core.common.utils.RedisUtils;
import com.unisinsight.vdp.core.dto.request.DataRetentionPeriodReqDTO;
import com.unisinsight.vdp.core.dto.response.DataRetentionPeriodResDTO;
import com.unisinsight.vdp.core.dto.response.bean.DataRetentionPeriodInfo;
import com.unisinsight.vdp.core.service.DataRetentionPeriodService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.lang.reflect.Method;

import static com.unisinsight.vdp.common.constants.BigDataConstant.DATA_RETENTION_PERIOD;

/**
 * 初始化留存期时间数据线程
 *
 * @author liujiaji [liu.jiaji@unisinsight.com]
 * @date 2018/11/12 09:57
 * @since 1.0
 */
@Component
public class DataPeriodInitExecutor implements Runnable {

    @Resource
    private RestTemplate restTemplate;

    @Resource
    private RedisUtils redisUtils;

    @Resource
    private DataRetentionPeriodService retentionPeriodService;

    /**
     * 数据留存期远程接口地址
     */
    @Value(value = "${viid.url}")
    private String viidPath;

    /**
     * 存redis的key前缀
     */
    public static final String REDIS_KEY_PREFIX = "dataRetention::period";

    /**
     * 接口请求字段type
     */
    public static final String TYPE = "type";

    /**
     * 接口请求字段服务
     */
    public static final String SERVICE = "service";

    /**
     * 车辆告警
     */
    private static final String VEHICLE_NOTIFICATION = "VehicleNotification";

    /**
     * 人脸告警
     */
    private static final String FACE_NOTIFICATION = "FaceNotification";


    @Override
    public void run() {
        initDataPeriod2DB();
    }


    /**
     * 启动服务时将所有留存期时间数据加载入数据库
     */
    public void initDataPeriod2DB() {
        // 清空数据库数据
        retentionPeriodService.deleteAll();
        // 清空redis数据
        for (int i = 0; i < 3; i++) {
            redisUtils.delete(REDIS_KEY_PREFIX + i);
        }
        Class dtoClass = DataRetentionPeriodResDTO.class;
        // 获取类型枚举
        DataRetentionPeriodTypeEnum[] typeEnums = DataRetentionPeriodTypeEnum.values();
        // 获取服务枚举
        DataRetentionPeriodServiceEnum[] serviceEnums = DataRetentionPeriodServiceEnum.values();
        for (DataRetentionPeriodTypeEnum typeEnum : typeEnums) {
            DataRetentionPeriodResDTO resDTO = new DataRetentionPeriodResDTO();
            Integer dataType = typeEnum.getNum();
            resDTO.setType(dataType);
            try {
                for (DataRetentionPeriodServiceEnum serviceEnum : serviceEnums) {
                    // 如果是非结构化数据,则没有车辆告警.人脸告警
                    String name = serviceEnum.getName();
                    if (!new Integer(0).equals(dataType) && (name.equals(VEHICLE_NOTIFICATION) || name.equals(FACE_NOTIFICATION))) {
                        continue;
                    }
                    DataRetentionPeriodInfo[] periods = restTemplate.getForObject(viidPath + DATA_RETENTION_PERIOD + "?" + TYPE + "=" + dataType + "&" + SERVICE + "=" + name, DataRetentionPeriodInfo[].class);
                    // 暂时不需要静态人脸数据
                    DataRetentionPeriodInfo period = periods[0];
                    if ("FaceStatic".equals(period.getService())) {
                        continue;
                    }
                    Method setMethod = dtoClass.getDeclaredMethod("set" + period.getService(), Integer.class);
                    setMethod.invoke(resDTO, period.getValue());
                }
                DataRetentionPeriodReqDTO reqDTO = new DataRetentionPeriodReqDTO();
                BeanUtils.copyProperties(resDTO, reqDTO);
                // 保存数据库
                retentionPeriodService.saveOrUpdate(reqDTO);
            } catch (Exception e) {
                e.printStackTrace();
                throw BaseException.of(BaseErrorCode.NETWORK_ERROR.of(), "网络错误,数据加载失败" + e.getMessage());
            }
        }
    }


}