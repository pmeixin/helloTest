/*
 * www.unisinsight.com Inc.
 * Copyright (c) 2018 All Rights Reserved
 */
package com.unisinsight.vdp.core.manager.integration.task;

import com.alibaba.fastjson.JSON;
import com.unisinsight.framework.common.exception.BaseException;
import com.unisinsight.framework.common.exception.RestException;
import com.unisinsight.vdp.core.enums.TaskErrorCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * description
 *
 * @author daisike [dai.sike@h3c.com]
 * @date 2018/11/03 12:08
 * @since 1.0
 */
@Component
public class RestTemplateWrapper {

	private static final Logger logger = LoggerFactory.getLogger(RestTemplateWrapper.class);

	@Autowired
	private RestTemplate restTemplate;

	/**
	 * HTTP POST
	 * 
	 * @param url
	 *            请求url
	 * @param req
	 *            请求参数封装
	 * @param rspClass
	 *            返回结果Class
	 * @param description
	 *            请求描述
	 * @param <T>
	 *            返回结果
	 * @return
	 */
	public <T> T doPost(String url, Object req, Class<T> rspClass, String description) {
		logger.info("请求外部平台微服务【{}】开始,url:{}, 请求参数:{}", description, url, JSON.toJSONString(req));
		T rsp;
		try {
			rsp = restTemplate.postForObject(url, req, rspClass);
		} catch (Exception e) {
			handException(description, e);
			throw new BaseException("请求外部微服务失败");
		}
		if (null == rsp) {
			logger.error("请求外部平台微服务【{}】失败,返回结果为空", description);
			throw new BaseException("请求外部平台失败,返回结果为空");
		}
		logger.info("请求外部平台微服务【{}】成功,返回结果:{}", description, JSON.toJSONString(rsp));
		return rsp;
	}

	/**
	 * HTTP GET
	 * 
	 * @param url
	 *            请求url
	 * @param rspClass
	 *            返回结果Class
	 * @param description
	 *            请求描述
	 * @param <T>
	 *            返回结果
	 * @return
	 */
	public <T> T doGet(String url, Class<T> rspClass, String description) {
		logger.info("请求外部平台微服务获取【{}】开始,url:{}", description, url);
		T rsp;
		try {
			rsp = restTemplate.getForObject(url, rspClass);
		} catch (Exception e) {
			handException(description, e);
			throw new BaseException("请求外部微服务失败");
		}
		if (null == rsp) {
			logger.error("请求外部平台微服务获取【{}】失败,返回结果为空", description);
			throw new BaseException("请求外部平台失败,返回结果为空");
		}
		logger.info("请求外部平台微服务获取【{}】成功,返回结果:{}", description, JSON.toJSONString(rsp));
		return rsp;

	}

	/**
	 * HTTP DELETE
	 * 
	 * @param url
	 *            请求url
	 * @param description
	 *            请求描述
	 * @return
	 */
	public void doDelete(String url, String description) {
		logger.info("请求外部平台微服务删除【{}】开始,url:{}", description, url);
		try {
			restTemplate.delete(url);
			logger.info("请求外部平台微服务删除【{}】成功", description);
		} catch (Exception e) {
			handException(description, e);
			throw BaseException.of(TaskErrorCode.TASK_DELETE_FAIL.of());
		}

	}

	private void handException(String description, Exception e) {
		if (e instanceof RestException) {
			RestException re = (RestException) e;
			String errorMassage = String.format("{%s, %s}", re.getHttpStatus(), e.getMessage());
			logger.error("请求外部平台微服务【{}】异常,errorMessage:{}, 异常信息:", description, errorMassage, e);
		} else {
			logger.error("请求外部平台微服务【{}】出现未知异常, 异常信息:", description, e);
		}
	}

}
