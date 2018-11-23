/*
 * www.unisinsight.com Inc.
 * Copyright (c) 2018 All Rights Reserved
 */
package com.unisinsight.vdp.core.common.enums;

/**
 * description
 *
 * @author daisike [dai.sike@unisinsight.com]
 * @date 2018/09/19 11:39
 * @since 1.0
 */
public enum AnalysisTaskTypeEnum {
	/** 图片流 **/
	IMAGE_STREAM(0, "人脸图片流"),

	/** 视频流 **/
	VIDEO_STREAM(1, "人脸视频流"),

	/** 视频结构化解析 **/
	VIDEO_STRUCTURE(2, "视频结构化"),

	/** 车辆图片流 **/
	VEHICLE_IMAGE_STREAM(3, "车辆实时图片流");
	/**
	 * 枚举值
	 */
	private final Integer code;

	/**
	 * 枚举描述
	 */
	private final String message;

	/**
     * 构造器
	 * @param code
	 *            枚举值
	 * @param message
	 *            枚举描述
	 */
	AnalysisTaskTypeEnum(Integer code, String message) {
		this.code = code;
		this.message = message;
	}

	/**
     * 枚举code
	 * @return Returns the code.
	 */
	public Integer code() {
		return code;
	}

	/**
     * 枚举message
	 * @return Returns the message.
	 */
	public String message() {
		return message;
	}

	/**
	 * 通过枚举&lt;code&gt;code&lt;/code&gt;获得枚举
	 *
	 * @param code
	 * @return AnalysisTaskTypeEnum
	 */
	public static AnalysisTaskTypeEnum getByCode(Integer code) {
		for (AnalysisTaskTypeEnum _enum : values()) {
			if (_enum.code().equals(code)) {
				return _enum;
			}
		}
		return null;
	}

	/**
	 * 获取全部枚举
	 *
	 * @return List<AnalysisTaskTypeEnum >;
	 */
	public static java.util.List<AnalysisTaskTypeEnum> getAllEnum() {
		java.util.List<AnalysisTaskTypeEnum> list = new java.util.ArrayList<AnalysisTaskTypeEnum>(values().length);
		for (AnalysisTaskTypeEnum _enum : values()) {
			list.add(_enum);
		}
		return list;
	}

	/**
	 * 获取全部枚举值
	 *
	 * @return List&lt;String&gt;
	 */
	public static java.util.List<Integer> getAllEnumCode() {
		java.util.List<Integer> list = new java.util.ArrayList<Integer>(values().length);
		for (AnalysisTaskTypeEnum _enum : values()) {
			list.add(_enum.code());
		}
		return list;
	}

	/**
	 * 通过code获取msg
	 *
	 * @param code
	 *            枚举值
	 * @return String
	 */
	public static String getMsgByCode(Integer code) {
		if (code == null) {
			return null;
		}
		AnalysisTaskTypeEnum _enum = getByCode(code);
		if (_enum == null) {
			return null;
		}
		return _enum.message();
	}

	/**
	 * 获取枚举code
	 *
	 * @param _enum
	 * @return Integer
	 */
	public static Integer getCode(AnalysisTaskTypeEnum _enum) {
		if (_enum == null) {
			return null;
		}
		return _enum.code();
	}

}
