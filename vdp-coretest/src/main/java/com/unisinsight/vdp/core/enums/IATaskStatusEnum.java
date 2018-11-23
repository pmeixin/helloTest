/*
 * www.unisinsight.com Inc.
 * Copyright (c) 2018 All Rights Reserved
 */
package com.unisinsight.vdp.core.enums;

/**
 * ia任务状态枚举 description
 *
 * @author daisike [dai.sike@unisinsight.com]
 * @date 2018/09/30 15:31
 * @since 1.0
 */
public enum IATaskStatusEnum {

	/** 运行中 **/
	RUNNING("running", 1, "正常运行中"),

	PENDING("pending", 2, "等待分配节点"),

	ERROR("error", 3, "运行错误");

	private final String name;

	/**
	 * 枚举值
	 */
	private final Integer code;

	/**
	 * 枚举描述
	 */
	private final String message;

	/**
	 * @param code
	 *            枚举值
	 * @param message
	 *            枚举描述
	 */
	IATaskStatusEnum(String name, Integer code, String message) {
		this.name = name;
		this.code = code;
		this.message = message;
	}

	/**
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return Returns the code.
	 */
	public Integer getCode() {
		return code;
	}

	/**
	 * @return Returns the message.
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @return Returns the code.
	 */
	public Integer code() {
		return code;
	}

	/**
	 * @return Returns the message.
	 */
	public String message() {
		return message;
	}

	public static IATaskStatusEnum getByName(String name) {
		for (IATaskStatusEnum _enum : values()) {
			if (_enum.getName().equals(name)) {
				return _enum;
			}
		}
		return null;
	}

	/**
	 * 通过枚举code获得枚举
	 *
	 * @param code
	 * @return IATaskStatusEnum
	 */
	public static IATaskStatusEnum getByCode(Integer code) {
		for (IATaskStatusEnum _enum : values()) {
			if (_enum.getCode().equals(code)) {
				return _enum;
			}
		}
		return null;
	}

	/**
	 * 获取全部枚举
	 *
	 * @return List<IATaskStatusEnum >;
	 */
	public static java.util.List<IATaskStatusEnum> getAllEnum() {
		java.util.List<IATaskStatusEnum> list = new java.util.ArrayList<IATaskStatusEnum>(values().length);
		for (IATaskStatusEnum _enum : values()) {
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
		java.util.List<Integer> list = new java.util.ArrayList<>(values().length);
		for (IATaskStatusEnum _enum : values()) {
			list.add(_enum.code());
		}
		return list;
	}

	/**
	 * 通过code获取msg
	 *
	 * @param code
	 *            枚举值
	 * @return
	 */
	public static String getMsgByCode(Integer code) {
		if (code == null) {
			return null;
		}
		IATaskStatusEnum _enum = getByCode(code);
		if (_enum == null) {
			return null;
		}
		return _enum.getMessage();
	}

	/**
	 * 获取枚举code
	 *
	 * @param _enum
	 * @return
	 */
	public static Integer getCode(IATaskStatusEnum _enum) {
		if (_enum == null) {
			return null;
		}
		return _enum.getCode();
	}

}
