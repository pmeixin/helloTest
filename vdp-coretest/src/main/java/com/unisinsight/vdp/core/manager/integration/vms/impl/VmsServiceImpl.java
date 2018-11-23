/*
 * www.unisinsight.com Inc.
 * Copyright (c) 2018 All Rights Reserved
 */
package com.unisinsight.vdp.core.manager.integration.vms.impl;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.PropertyNamingStrategy;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.unisinsight.framework.common.exception.BaseException;
import com.unisinsight.framework.common.exception.RestException;
import com.unisinsight.vdp.common.constants.VMSConstant;
import com.unisinsight.vdp.common.vms.request.ChannelResourcesReq;
import com.unisinsight.vdp.common.vms.request.RealPlayUrlReq;
import com.unisinsight.vdp.common.vms.response.*;
import com.unisinsight.vdp.common.vms.response.bean.ChannelDevice;
import com.unisinsight.vdp.common.vms.response.bean.DetailChannelDevice;
import com.unisinsight.vdp.common.vms.response.bean.ResourceTreeNode;

import com.unisinsight.vdp.core.common.utils.RedisUtils;
import com.unisinsight.vdp.core.manager.integration.vms.VmsService;

import org.apache.logging.log4j.util.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.util.MimeType;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.client.RestTemplate;

import java.net.URL;
import java.nio.charset.Charset;
import java.util.*;

/**
 * description
 *
 * @author daisike [dai.sike@unisinsight.com]
 * @date 2018/10/08 16:22
 * @since 1.0
 */
//@Component
public class VmsServiceImpl implements VmsService, InitializingBean {

	private static final Logger logger = LoggerFactory.getLogger(VmsServiceImpl.class);

	private static final int REAL_PLAY_URL_TYPE = 0;

	private static final int REAL_PLAY_URL_PLAYTYPE = 1;

	private static final int REAL_PLAY_URL_LOCAL = 0;

	private static String VMS_RESOURCE_TREE_DEFAULT_KEY;

	private volatile String token;

	private SerializeConfig config;


	@Value("${vms.url.base}")
	private String vmsUrl;

	@Value("${vms.redis-expire}")
	private long nodeExpireTime;

	@Value("${vms.auth.id}")
	private String vmsUser;

	@Value("${vms.auth.code}")
	private String vmsPw;

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private RedisUtils redisUtils;

	@Override
	public List<ResourceTreeNode> getResourceTree(String unitType, String deviceType, boolean refresh) {
		String key = VMS_RESOURCE_TREE_DEFAULT_KEY;

		Map<String, Object> reqParamMap = new HashMap<>(2);
		if (Strings.isNotBlank(unitType)) {
			key = key + unitType.replaceAll(",", "");
			reqParamMap.put("unit_type", "0,1");
		}
		if (Strings.isNotBlank(deviceType)) {
			key = key + "_" + deviceType.replaceAll(",", "");
			reqParamMap.put("category", deviceType);
		}
		if (refresh) {
			List<ResourceTreeNode> resourceTreeNodes = executeGetResourceTree(reqParamMap);
			if (null != resourceTreeNodes && resourceTreeNodes.size() > 0) {
				redisUtils.setWithExpire(key, resourceTreeNodes, nodeExpireTime, null);
			}
			return resourceTreeNodes;
		} else {
			List<ResourceTreeNode> resourceTreeNodes = (List<ResourceTreeNode>) redisUtils.get(key);
			if (null != resourceTreeNodes) {
				logger.info("从[redis]中获取缓存数据成功,key={},value={}", key, resourceTreeNodes);
				return resourceTreeNodes;
			}
			logger.info("从[redis]中获取缓存数据为空,key={}, 准备请求[VMS]并更新缓存", key);
			resourceTreeNodes = executeGetResourceTree(reqParamMap);
			if (null != resourceTreeNodes && resourceTreeNodes.size() > 0) {
				redisUtils.setWithExpire(key, resourceTreeNodes, nodeExpireTime, null);
			}
			return resourceTreeNodes;
		}
	}

	@Override
	public String getRealPlayUrl(String cameraId) {
		Map<String, Object> params = new HashMap<>(4);
		params.put(RealPlayUrlReq.CAMERA_ID, cameraId);
		params.put(RealPlayUrlReq.TYPE, REAL_PLAY_URL_TYPE);
		params.put(RealPlayUrlReq.PALY_TYPE, REAL_PLAY_URL_PLAYTYPE);

		params.put(RealPlayUrlReq.IS_LOCAL, REAL_PLAY_URL_LOCAL);
		String path = "/mock/171/bserver/api/v1/device/real_play";
		String url = buildUrl(vmsUrl, path, params);
		RealPlayUrlRes realPlayUrlRes = sendRequest(url, null, RealPlayUrlRes.class, "获取实时播放视频流地址");
		return realPlayUrlRes.getData();
	}

	@Override
	public List<ChannelDevice> getChannelsByDeviceId(String deviceId) {
		//"/mock/171/bserver/api/v1/device/channels/id"
		String path = VMSConstant.QUERY_CHANNEL_LIST_BY;
		Map<String,Object > params = new HashMap<>(1);
		params.put("device_code", deviceId);
		String url = buildUrl(this.vmsUrl, path, params);
		ChannelRes channelRes = sendRequest(url, null, ChannelRes.class,
				"根据设备编码查询通道列表");
		return channelRes.getData();
	}

	@Override
	public List<DetailChannelDevice> getChannelResources(ChannelResourcesReq channelResoucesReq) {
		String path = VMSConstant.GET_CHANNEL_LIST;
		Map<String, Object> params = new HashMap<>(3);
		params.put("unit_type", channelResoucesReq.getUnitType());
		params.put("condition", JSON.toJSON(channelResoucesReq.getCondition(),config));
		params.put("pagination", JSON.toJSON(channelResoucesReq.getPagination(), config));
		String url = buildUrl(this.vmsUrl, path, params);
        ChannelResoucesRes channelResoucesRes = sendRequest(url, null, ChannelResoucesRes.class,"获取通道资源列表");
		return channelResoucesRes.getData();
	}

	private String buildUrl(String vmsUrl, String path, Map<String, Object> queryParams) {
		if(Strings.isBlank(token)){
			throw new BaseException("系统内部错误");
		}
		StringBuilder sb = new StringBuilder().append(vmsUrl).append(path).append("?token=").append(token);
		if (null != queryParams && !queryParams.isEmpty()) {
			sb.append("&");
			Set<Map.Entry<String, Object>> paramEntry = queryParams.entrySet();
			Iterator<Map.Entry<String, Object>> iterator = paramEntry.iterator();
			int index = 0;
			int flag = queryParams.size() - 1;
			while (iterator.hasNext()) {
				Map.Entry<String, Object> entry = iterator.next();
				sb.append(entry.getKey()).append("=").append(entry.getValue());
				if (index++ < flag) {
					sb.append("&");
				}
			}
		}
		return sb.toString();
	}

	private List<ResourceTreeNode> executeGetResourceTree(Map<String, Object> reqParamMap) {
		// /mock/171/bserver/api/v1/organization/all
		String path = VMSConstant.ORGANIZATION_DEVICE_TREE_NODE;
		String url = buildUrl(vmsUrl, path, reqParamMap);
		ResourceTreeRes resourceTreeRes = sendRequest(url, null, ResourceTreeRes.class, "获取资源树结点");
		return resourceTreeRes.getData();
	}

	private void initToken() {
		String url = this.vmsUrl + VMSConstant.USER_AUTHENTICATION_TOKEN;
		HttpHeaders httpHeaders = getHeader();
		TokenRes tokenRes = sendRequest(url, httpHeaders, TokenRes.class, "获取token");
		if (Strings.isBlank(tokenRes.getData())) {
			logger.error("调用[vms]平台微服务【获取token】返回token值为空,返回结果:", JSON.toJSONString(tokenRes));
		} else {
			token = tokenRes.getData();
		}
	}

	private void refreshToken() {
		String path = VMSConstant.REFRESH_AUTHENTICATION_TOKEN;
		String url = buildUrl(this.vmsUrl, path, null);
		TokenRes tokenRes = sendRequest(url, null, TokenRes.class, "刷新token");
		if (Strings.isBlank(tokenRes.getData())) {
			logger.error("调用[vms]平台微服务【更新token】返回token值为空,返回结果:", JSON.toJSONString(tokenRes));
			initToken();
		} else {
			token = tokenRes.getData();
		}
	}

	private HttpHeaders getHeader() {
		// 请求头
		HttpHeaders headers = new HttpHeaders();

		String encode = "Basic " + Base64.getEncoder().encodeToString((this.vmsUser + ":" + this.vmsPw).getBytes());
		headers.set("Authorization", encode);

		MimeType mimeType = MimeTypeUtils.parseMimeType("application/json");
		MediaType mediaType = new MediaType(mimeType.getType(), mimeType.getSubtype(), Charset.forName("UTF-8"));
		headers.setContentType(mediaType);
		return headers;
	}

	private <T extends VmsBaseRes> T sendRequest(String url, HttpHeaders headers, Class<T> rspClass,
			String description) {
		HttpEntity entity = headers == null ? null : new HttpEntity(headers);
		T rsp;
		try {
			logger.info("请求[vms]平台微服务获取【{}】开始,url:{}", description, url);
			ResponseEntity<T> responseEntity = restTemplate.exchange(url, HttpMethod.GET, entity, rspClass);
			rsp = responseEntity.getBody();
		} catch (Exception e) {
			if (e instanceof RestException) {
				RestException re = (RestException) e;
				String errorMassage = String.format("{%s, %s}", re.getHttpStatus(), e.getMessage());
				logger.error("请求外部平台微服务【{}】异常,errorMessage:{}, 异常信息:", description, errorMassage, e);
				throw e;
			}
			logger.error("调用[vms]平台微服务[{}]【未知异常】,异常信息:", description, e);
			throw e;
		}
		if (null != rsp) {
			logger.info("调用[vms]平台微服务[{}]【成功】,返回数据：{}", description, JSON.toJSON(rsp));
			return rsp;
		} else {
			logger.info("调用[vms]平台微服务[{}]【失败】, 返回为空", description);
			return null;
		}

	}

	@Override
	public void afterPropertiesSet() throws Exception {
		String[] ipslices = new URL(this.vmsUrl).getHost().split("\\.");
		VMS_RESOURCE_TREE_DEFAULT_KEY = new StringBuilder("vms_tree_").append(ipslices[2]).append("_")
				.append(ipslices[3]).append("_").toString();
        config = new SerializeConfig();
        config.setPropertyNamingStrategy(PropertyNamingStrategy.SnakeCase);
		try {
			new Thread(() -> initToken()).start();
		} catch (Exception e) {
			// 此处异常不影响全局功能，将异常信息打印出提示后，吃掉异常
			logger.error("应用启动初始化vms平台访问token异常, vms地址:{}, 异常信息:", this.vmsUrl, e);
		}
		//第一次20分钟后更新，以后每29分钟更新一次，避免token失效
		new Timer("refresh-vms-token").schedule(new TimerTask() {
			@Override
			public void run() {
				refreshToken();
			}
		}, 1200000, 1740000);

	}
}
