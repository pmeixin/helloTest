/*
 * www.unisinsight.com Inc.
 * Copyright (c) 2018 All Rights Reserved
 */
package com.unisinsight.vdp.core.manager.integration.vms.pre;

import com.alibaba.fastjson.JSON;
import com.unisinsight.vdp.common.constants.VMSConstant;
import com.unisinsight.vdp.common.vms.request.ChannelResourcesReq;
import com.unisinsight.vdp.common.vms.response.RealPlayUrlRes;
import com.unisinsight.vdp.common.vms.response.ResourceTreeResPre;
import com.unisinsight.vdp.common.vms.response.bean.ChannelDevice;
import com.unisinsight.vdp.common.vms.response.bean.DetailChannelDevice;
import com.unisinsight.vdp.common.vms.response.bean.ResourceTreeNode;
import com.unisinsight.vdp.common.vms.response.bean.ResourceTreeNodePre;
import com.unisinsight.vdp.core.common.utils.RedisUtils;
import com.unisinsight.vdp.core.manager.integration.vms.VmsService;
import com.unisinsight.vdp.core.manager.integration.vms.pre.webservice.VideoManager;

import com.unisinsight.vdp.core.manager.integration.vms.pre.webservice.VideoManagerService;
import com.unisinsight.vdp.core.manager.integration.vms.pre.webservice.WebServiceConfig;
import org.apache.logging.log4j.util.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * description
 *
 * @author daisike [dai.sike@unisinsight.com]
 * @date 2018/10/15 09:49
 * @since 1.0
 */
@Component
public class VmsServicePreImpl implements VmsService, InitializingBean {

    /**
     * 设置log
     */
	private static final Logger LOGGER = LoggerFactory.getLogger(VmsServicePreImpl.class);

	/**
	 * 默认资源树结点类型
	 */
	private static final String DEFAULT_TREE_TYPE = "0,1";

	/**
	 * 设置HashMap初始容量
	 */
	private static final int HASH_MAP_ORIGINAL_CAPACITY = 16;

	/**
	 * 通道标识
	 */
	private static final String VMS_CHANNEL_SIGN_STRING = "$1$0$0";

	/**
	 * todo 验证多线程安全问题
	 */
	private static VideoManager videoManager;

	/**
	 * redis默认key
	 */
	private static String VMS_RESOURCE_TREE_DEFAULT_KEY;

	/**
	 * vms webservice地址
	 */
	@Value("${vms.url.webservice}")
	private String vmsUrl;

	/**
	 * 设置redis
	 */
	@Value("${vms.redis-expire}")
	private long expireTime;

	/** 摄像头设备 */
	@Value("${vms-device.names}")
	private String[] deviceNames;

	/** 设置经度 */
	@Value("${vms-device.longitudes}")
	private String[] deviceLongitude;

	/** 设置维度 */
	@Value("${vms-device.latitudes}")
	private String[] deviceLatitude;

	private Double[] deviceLongitudes;


	private Double[] deviceLatitudes;

	/** 设置ip */
    @Value("${vms-device.ips}")
    private String[] ips;

    /** 设置行政区划代码 */
    @Value("${vms-device.placeCode}")
    private String[] placeCode;

    /**
     * 设置redis工具
     */
	@Autowired
	private RedisUtils redisUtils;

	@Override
	public void afterPropertiesSet() throws Exception {
		String[] ipslices = new URL(this.vmsUrl).getHost().split("\\.");
		VMS_RESOURCE_TREE_DEFAULT_KEY = new StringBuilder("vms_tree_").append(ipslices[2]).append("_")
				.append(ipslices[3]).append("_").toString();
		this.deviceLongitudes = new Double[this.deviceLongitude.length];
		for(int i = 0 ; i < this.deviceLongitude.length; i++){
			this.deviceLongitudes[i] = Double.parseDouble(this.deviceLongitude[i]);
		}
		this.deviceLatitudes = new Double[this.deviceLongitude.length];
		for(int i = 0 ; i < this.deviceLatitude.length; i++){
			this.deviceLatitudes[i] = Double.parseDouble(this.deviceLatitude[i]);
		}

	}

	/**
	 * 初始化
	 */
	public static void init() {
		try {
			new Thread(() -> videoManager = new VideoManagerService().getVideoManagerPort()).start();
		} catch (Exception e) {
			// 此处异常不影响全局功能，将异常信息打印出提示后，吃掉异常
			LOGGER.error("VMS Webservice[{}]地址不可用,异常信息：{}", WebServiceConfig.urlPath, e);
		}
	}
	/**
	 * 根据第一期项目中的资源树接口来获取数据，并添加经纬度信息
	 * FIXME 二期项目中需要修改获取数据方式
	 * @param  treeType {inheritDoc}
     * @param  deviceType {inheritDoc}
     * @param  refresh {inheritDoc}
	 * @return {inheritDoc}
	 */
	@Override
	public List<ResourceTreeNode> getResourceTree(String treeType, String deviceType, boolean refresh) {

		String key = VMS_RESOURCE_TREE_DEFAULT_KEY;
		Map<String, Object> reqParamMap = new HashMap<>(2);
		if (Strings.isNotBlank(treeType)) {
			key = key + treeType.replaceAll(",", "");
			reqParamMap.put("tree_type", "0,1");
		}
		if (Strings.isNotBlank(deviceType)) {
			key = key + "_" + deviceType.replaceAll(",", "");
			reqParamMap.put("device_type", deviceType);
		}

        // 获取一期数据
		List<ResourceTreeNodePre> resourceTreeNodePreList;
		if (refresh) {
			resourceTreeNodePreList = executeWebservice(treeType, deviceType);
			if (null != resourceTreeNodePreList && resourceTreeNodePreList.size() > 0) {
				redisUtils.setWithExpire(key, resourceTreeNodePreList, expireTime, null);
			}
		} else {
			resourceTreeNodePreList = (List<ResourceTreeNodePre>) redisUtils.get(key);
			if (null == resourceTreeNodePreList) {
				LOGGER.info("从[redis]中获取缓存数据为空,key={}, 准备请求[VMS]并更新缓存", key);
				resourceTreeNodePreList = executeWebservice(treeType, deviceType);
				if (null != resourceTreeNodePreList && resourceTreeNodePreList.size() > 0) {
					redisUtils.setWithExpire(key, resourceTreeNodePreList, expireTime, null);
				}
			}
			LOGGER.info("从[redis]中获取缓存数据成功,key={},value={}", key, resourceTreeNodePreList);
		}

		// 将每个节点的设备名称属性当做key映射到map里面，？？这里需要考虑的是以设备名称属性当做key，还是以id属性当做key
		Map<String, ResourceTreeNodePre> mappingDevice = new HashMap<>(HASH_MAP_ORIGINAL_CAPACITY);
		if (null != resourceTreeNodePreList) {
			resourceTreeNodePreList.forEach(resourceTreeNodePre -> {
				/*if (resourceTreeNodePre.getId().contains("$")) {
					resourceTreeNodePre.setId(splitVmsDeviceSign(resourceTreeNodePre.getId()));
				}*/
                mappingDevice.put(resourceTreeNodePre.getName(), resourceTreeNodePre);
			});
		}

		// 遍历key，然后写入经纬度信息，！！此处还可以继续添加其他属性信息
			for (int i = 0; i < deviceNames.length; i++) {
				if (mappingDevice.containsKey(deviceNames[i])) {
					mappingDevice.get(deviceNames[i]).setLongitude(deviceLongitudes[i]);
					mappingDevice.get(deviceNames[i]).setLatitude(deviceLatitudes[i]);
				}
			}

		List<ResourceTreeNodePre> transferList = new ArrayList<>();
		for (String mapKey : mappingDevice.keySet()) {
			transferList.add(mappingDevice.get(mapKey));
		}


		//将节点类型写死，先设置为0，再分别设置2和1，写法挺搓，后期删除
        for (ResourceTreeNodePre aTransferList1 : transferList) {
            aTransferList1.setNodeType(0);

        }

        //模拟category和nodeType字段
		for (int i = 0; i < transferList.size(); i++) {
		    if (transferList.get(i).getCategory() == 5) {
				transferList.get(i).setCategory(7);
		        transferList.get(i).setNodeType(2);
                for (ResourceTreeNodePre aTransferList : transferList) {
                    if (transferList.get(i).getpId().equals(aTransferList.getId())) {
						aTransferList.setCategory(7);
                        aTransferList.setNodeType(1);
                    }
                }
            }
		}

		// 一期ResourceTreeNodePre转换到二期ResourceTreeNode
		List<ResourceTreeNode> resourceTreeNodeList = new ArrayList<>();
		for (int i = 0; i < transferList.size(); i++) {
			ResourceTreeNode resourceTreeNode = new ResourceTreeNode();
			BeanUtils.copyProperties(transferList.get(i), resourceTreeNode);
			resourceTreeNodeList.add(resourceTreeNode);
			resourceTreeNodeList.get(i).setDeviceCode(transferList.get(i).getpPid());
			resourceTreeNodeList.get(i).setOrgCode(transferList.get(i).getpId());

		}

		// 若有需求，可构造ip，place，installAddr数据
        for (ResourceTreeNode aResourceTreeNodeList : resourceTreeNodeList) {
            for (int j = 0; j < deviceNames.length; j++) {
                if (aResourceTreeNodeList.getName().equals(deviceNames[j])) {
                    aResourceTreeNodeList.setIp(ips[j]);
                    aResourceTreeNodeList.setPlaceCode(placeCode[j]);
                }
            }
        }
		return resourceTreeNodeList;

	}

	/**
	 * 获取播放地址
     * @param  cameraId 设备id
	 * @return 返回播放地址
	 */
	@Override
	public String getRealPlayUrl(String cameraId) {

		String mediaPlayUrlJson = videoManager.getMediaPalyUrl("00001", cameraId, "0", "1", 0);
		RealPlayUrlRes mediaResult = JSON.parseObject(mediaPlayUrlJson, RealPlayUrlRes.class);
		if (VMSConstant.VMS_SUCCESS_CODE.equals(mediaResult.getErrorCode())) {
			LOGGER.info("获取VMS设备【{}】实时视频播放url成功:mediaPlayUrl={}", cameraId, mediaResult.getErrorCode());
		} else {
			LOGGER.warn("获取VMS设备【{}】实时视频播放url地址出现异常:errorCode={},errorMessage={}", cameraId,
					mediaResult.getErrorCode(), mediaResult.getMessage());
		}
		return mediaResult.getData();
	}

	/**
	 * 获取通道列表，
	 * FIXME 二期项目VMS相关接口未给出，目前的联调将资源树的数据进行改造后提供
	 * @param {inheritDoc}
	 * @return {inheritDoc}
	 */
	@Override
	public List<ChannelDevice> getChannelsByDeviceId(String deviceId) {
		List<ResourceTreeNode> resourceTreeNodes = this.getResourceTree("1", null, false);
		List<ChannelDevice> channelDeviceList = new ArrayList<>(1);
		resourceTreeNodes.forEach(resourceTreeNode -> {
			if (resourceTreeNode.getId().contains(deviceId)) {
				ChannelDevice channelDevice = new ChannelDevice();
				channelDevice.setOrgCode("001");
				channelDevice.setDeviceCode(deviceId);
				BeanUtils.copyProperties(resourceTreeNode, channelDevice);
				channelDeviceList.add(channelDevice);
			}

		});
		return channelDeviceList;
	}

	@Override
	public List<DetailChannelDevice> getChannelResources(ChannelResourcesReq channelResourcesReq) {
		return null;
	}



	/**
	 * 调用vms底层服务
	 * @param treeType {@inheritDoc}
     * @param deviceType {@inheritDoc}
	 * @return 资源梳列表
	 */
	private List<ResourceTreeNodePre> executeWebservice(String treeType, String deviceType) {
		String resultJson;
		if (null != videoManager) {
			resultJson = videoManager.getTreeNode(treeType, deviceType);
		} else {
			LOGGER.error("VMS WebService地址初始化失败");
			return null;
		}

		// FIXME ResourceTreeResPre后期也需要修改
		ResourceTreeResPre resourceTreeResult = JSON.parseObject(resultJson, ResourceTreeResPre.class);
		if (!StringUtils.isEmpty(resourceTreeResult.getErrorCode())
				&& !VMSConstant.VMS_SUCCESS_CODE.equals(resourceTreeResult.getErrorCode())) {
			LOGGER.warn("获取VMS资源树访问异常:errorCode={},errorMessage={},请检查VMS Webservice配置",
					resourceTreeResult.getErrorCode(), resourceTreeResult.getMessage()
			);
		} else {
			LOGGER.info("获取VMS资源树结果:{}", resultJson);
		}
		return resourceTreeResult.getResultData();

	}

	/**
	 * 分割设备编码和通道编码
	 * @param  source 待分割的字符串
	 * @return 返回分割后的字符串
	 */
	private String splitVmsDeviceSign(String source) {
		return source.substring(0, source.indexOf('$'));
	}

	/**
	 * 恢复设备标识
	 * @param  source 待恢复的字符串
	 * @return 恢复后的字符串
	 */
	public static String recoveryVmsDeviceSign(String source) {
		return source + VMS_CHANNEL_SIGN_STRING;
	}


}
