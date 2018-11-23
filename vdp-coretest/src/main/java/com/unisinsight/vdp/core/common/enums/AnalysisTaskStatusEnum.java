/*
 * www.unisinsight.com Inc.
 * Copyright (c) 2018 All Rights Reserved
 */
package com.unisinsight.vdp.core.common.enums;

/**
 * description
 *
 * @author daisike [dai.sike@unisinsight.com]
 * @date 2018/09/20 11:26
 * @since 1.0
 */
public enum AnalysisTaskStatusEnum {

	/** 创建成功 **/
	SUCCESS(1, "创建成功"),

	/** 创建失败 **/
	FAIL(0, "创建失败"),

	/** 创建中 **/
	PROCESSING(2, "创建中");
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
	AnalysisTaskStatusEnum(Integer code, String message) {
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
	 * @return TaskStatus
	 */
	public static AnalysisTaskStatusEnum getByCode(Integer code) {
		for (AnalysisTaskStatusEnum _enum : values()) {
			if (_enum.code().equals(code)) {
				return _enum;
			}
		}
		return null;
	}

	/**
	 * 获取全部枚举
	 *
	 * @return List<TaskStatus >;
	 */
	public static java.util.List<AnalysisTaskStatusEnum> getAllEnum() {
		java.util.List<AnalysisTaskStatusEnum> list = new java.util.ArrayList<AnalysisTaskStatusEnum>(values().length);
		for (AnalysisTaskStatusEnum _enum : values()) {
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
		for (AnalysisTaskStatusEnum _enum : values()) {
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
		AnalysisTaskStatusEnum _enum = getByCode(code);
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
	public static Integer getCode(AnalysisTaskStatusEnum _enum) {
		if (_enum == null) {
			return null;
		}
		return _enum.code();
	}

}
