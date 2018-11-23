/*
 * www.unisinsight.com Inc.
 * Copyright (c) 2018 All Rights Reserved
 */
package com.unisinsight.vdp.core.common.config;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 自定义线程工厂，以命名线程
 *
 * @author daisike [dai.sike@unisinsight.com]
 * @date 2018/09/27 16:39
 * @since 1.0
 */
public class NamedThreadFactory implements ThreadFactory {

    /**
     * 线程池初始编号
     */
	private static final AtomicInteger POOL_NUMBER = new AtomicInteger(1);

    /**
     * 线程组
     */
	private final ThreadGroup group;

    /**
     * 线程初始编号
     */
	private final AtomicInteger threadNumber = new AtomicInteger(1);

    /**
     * 线程名称前缀
     */
	private final String namePrefix;

	public NamedThreadFactory(String name) {

		SecurityManager s = System.getSecurityManager();
		group = (s != null) ? s.getThreadGroup() : Thread.currentThread().getThreadGroup();
		if (null == name || name.isEmpty()) {
			name = "pool";
		}

		namePrefix = name + "-" + POOL_NUMBER.getAndIncrement() + "-thread-";
	}

    /**
     * 工厂类新建线程
     * @param r
     * @return
     */
	@Override
	public Thread newThread(Runnable r) {
		Thread t = new Thread(group, r, namePrefix + threadNumber.getAndIncrement(), 0);
		if (t.isDaemon()) {
			t.setDaemon(false);
		}
		if (t.getPriority() != Thread.NORM_PRIORITY) {
			t.setPriority(Thread.NORM_PRIORITY);
		}
		return t;
	}

}
