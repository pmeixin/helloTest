package com.unisinsight.vdp.core.service.impl;

import com.unisinsight.framework.common.exception.BaseErrorCode;
import com.unisinsight.framework.common.exception.BaseException;
import com.unisinsight.vdp.core.common.enums.DataRetentionPeriodTypeEnum;
import com.unisinsight.vdp.core.common.utils.RedisUtils;
import com.unisinsight.vdp.core.dto.request.DataRetentionPeriodReqDTO;
import com.unisinsight.vdp.core.dto.response.DataRetentionPeriodResDTO;
import com.unisinsight.vdp.core.dto.response.bean.DataRetentionPeriodInfo;
import com.unisinsight.vdp.core.mapper.DataRetentionPeriodMapper;
import com.unisinsight.vdp.core.model.DataRetentionPeriod;
import com.unisinsight.vdp.core.service.DataRetentionPeriodService;
import org.springframework.beans.BeanUtils;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import static com.unisinsight.vdp.core.component.DataPeriodInitExecutor.TYPE;


/**
 * TODO 数据存留期时间service
 *
 * @author liujiaji [HZ.liujiaji@h3c.com]
 * @date 2018/10/24 17:18
 * @since 1.0
 */
@Service
public class DataRetentionPeriodServiceImpl implements DataRetentionPeriodService {

    @Resource
    private RestTemplate restTemplate;

    @Resource
    private RedisUtils redisUtils;

    @Resource
    private DataRetentionPeriodMapper periodMapper;

    /**
     * 数据留存期远程接口地址
     */
    private static final String URL = "http://192.168.130.248:8090/api/viid/v1/config/retained";

    /**
     * 存redis的key前缀
     */
    private static final String REDIS_KEY_PREFIX = "dataRetention::period";

    /**
     * 存redis 命名空间
     */
    public static final String DATA_REDIS_NAMESPACE = "dataRetention";

    /**
     * 存redis key
     */
    public static final String DATA_REDIS_KEY = "period";


    @Override
    public void saveOrUpdate(DataRetentionPeriodReqDTO reqDTO) {
        DataRetentionPeriod period = periodMapper.getPeriodByType(reqDTO.getType());
        if (period != null) {
            BeanUtils.copyProperties(reqDTO,period);
            periodMapper.updateByType(period);
        } else {
            DataRetentionPeriod retentionPeriod = new DataRetentionPeriod();
            BeanUtils.copyProperties(reqDTO, retentionPeriod);
            periodMapper.insertSelective(retentionPeriod);
        }

    }

    @Override
    public void updateRetentionPeriod(DataRetentionPeriodReqDTO reqDTO) {
        // 后端数据校验
        dataValid(reqDTO);
        try {
            // 反射获取字段,用于动态调用接口
            Class clazz = DataRetentionPeriodReqDTO.class;
            Field[] fields = clazz.getDeclaredFields();
            // 根据接口入参设置数据
            List<DataRetentionPeriodInfo> periods = new ArrayList<>();
            for (Field field : fields) {
                String fieldName = field.getName();
                // 跳过序列和类型字段
                if ("serialVersionUID".equals(fieldName) || TYPE.equals(fieldName)) {
                    continue;
                }
                // 大写用于拼接
                String upperField = fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
                // 获取getter方法,并调用获取数据
                Method getMethod = clazz.getDeclaredMethod("get" + upperField);
                Integer value = (Integer) getMethod.invoke(reqDTO);
                // 如果数据有值，添加到请求接口参数
                if (value != null) {
                    DataRetentionPeriodInfo period = new DataRetentionPeriodInfo();
                    period.setType(reqDTO.getType());
                    period.setService(upperField);
                    period.setValue(value);
                    periods.add(period);
                }
            }
            restTemplate.put(URL, periods);
            // 成功则更新数据库
            saveOrUpdate(reqDTO);
            // 更新redis
            syncRedisPeriod(reqDTO);
        } catch (Exception e) {
            e.printStackTrace();
            throw BaseException.of(BaseErrorCode.NETWORK_ERROR.of(), "网络错误,修改失败");
        }
    }


    @Override
    @Cacheable(value = DATA_REDIS_NAMESPACE, key = "#dataType")
    public DataRetentionPeriodResDTO getPeriodByType(String dataType) {
        DataRetentionPeriodResDTO resDTO = new DataRetentionPeriodResDTO();
        DataRetentionPeriod period = periodMapper.getPeriodByType(Integer.valueOf(dataType.substring(DATA_REDIS_KEY.length())));
        if (period != null) {
            BeanUtils.copyProperties(period, resDTO);
        }
        return resDTO;
    }

    @Override
    public void deleteAll() {
        periodMapper.deleteAll();
    }

    @Override
    public void dataValid(DataRetentionPeriodReqDTO DataRetentionPeriodReqDTO) {
        Integer person = DataRetentionPeriodReqDTO.getPerson();
        Integer type = DataRetentionPeriodReqDTO.getType();
        Integer vehicle = DataRetentionPeriodReqDTO.getVehicle();
        Integer faceSnap = DataRetentionPeriodReqDTO.getFaceSnap();
        // 如果是结构化数据，则抓拍人体留存期时间不能小于35
        if (type == 0) {
            if (person < 35) {
                throw BaseException.of(BaseErrorCode.INVALID_PARAM_ERROR.of(), "不能小于35（结构化数据抓拍人体留存期时间）");
            }
            if (vehicle < 35) {
                throw BaseException.of(BaseErrorCode.INVALID_PARAM_ERROR.of(), "不能小于35（结构化数据抓拍车辆留存期时间）");
            }
            if (faceSnap < 35) {
                throw BaseException.of(BaseErrorCode.INVALID_PARAM_ERROR.of(), "不能小于35（结构化数据抓拍人脸留存期时间）");
            }
            //如果是冷热数据，则抓拍人体留存时间不能大于35,小于10
        } else {
            if (person > 35 || person < 10) {
                throw BaseException.of(BaseErrorCode.INVALID_PARAM_ERROR.of(), "不能大于35（" + DataRetentionPeriodTypeEnum.getByNum(type).getName() + "抓拍人体留存期时间）");
            }
            if (vehicle > 35 || vehicle < 10) {
                throw BaseException.of(BaseErrorCode.INVALID_PARAM_ERROR.of(), "不能大于35（" + DataRetentionPeriodTypeEnum.getByNum(type).getName() + "抓拍车辆留存期时间）");
            }
            if (faceSnap > 35 || faceSnap < 10) {
                throw BaseException.of(BaseErrorCode.INVALID_PARAM_ERROR.of(), "不能大于35（" + DataRetentionPeriodTypeEnum.getByNum(type).getName() + "抓拍人脸留存期时间）");
            }
        }
    }

    private void syncRedisPeriod(DataRetentionPeriodReqDTO reqDTO){
        DataRetentionPeriodResDTO resDTO = new DataRetentionPeriodResDTO();
        BeanUtils.copyProperties(reqDTO, resDTO);
        redisUtils.set(REDIS_KEY_PREFIX + resDTO.getType(), resDTO);
    }
}