package com.unisinsight.vdp.core.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.concurrent.TimeUnit;

/**
 * redis工具类 提供基本的CRUD
 *
 * @author daisike [dai.sike@unisinsight.com]
 * @date 2018/9/7 9:37
 * @since 1.0
 */
@Component
public class RedisUtils {

	private static final Logger logger = LoggerFactory.getLogger(RedisUtils.class);

	/**
	 * 注入redisTemplate
	 */
	@Autowired
	private RedisTemplate<String, Object> redisTemplate;

	/**
	 * 删除缓存
	 * 
	 * @param key
	 *            可以传一个值 或多个
	 */
	public void delete(String... key) {
		if (key != null && key.length > 0) {
			if (key.length == 1) {
				redisTemplate.delete(key[0]);
			} else {
				redisTemplate.delete(CollectionUtils.arrayToList(key));
			}
		}
	}

	/**
	 * 取得缓存（字符串类型）
	 * 
	 * @param key
	 * @return Object
	 */
	public Object get(String key) {
		return redisTemplate.boundValueOps(key).get();
	}

	/**
	 * 设置
	 * 
	 * @param key
	 * @param value
	 * @return boolean
	 */
	public boolean set(String key, Object value) {
		try {
			redisTemplate.opsForValue().set(key, value);
			return true;
		} catch (Exception e) {
			logger.error("向redis中设置值异常:key={},value={},异常信息:", key, value, e);
			return false;
		}
	}

	/**
	 * 向redis中设置值
	 * 
	 * @param key
	 * @param value
	 * @param time
	 *            过期时间 默认 TimeUnit.SECONDS
	 * @return boolean
	 */
	public boolean setWithExpire(String key, Object value, long time, TimeUnit timeUnit) {
		timeUnit = timeUnit == null ? TimeUnit.SECONDS : timeUnit;
		try {
			redisTemplate.opsForValue().set(key, value, time, timeUnit);
			logger.info("向redis中设置数据成功,key={}, value={}", key, value);
			return true;
		} catch (Exception e) {
			logger.error("向redis中设置值异常:key={},value={},过期时间:{}s,异常信息:", key, value, time, e);
			return false;
		}

	}
}
