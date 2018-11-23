package com.unisinsight.vdp.core.mapper;

import com.unisinsight.framework.common.base.Mapper;
import com.unisinsight.vdp.core.model.AnalysisTask;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AnalysisTaskMapper extends Mapper<AnalysisTask> {

	/**
	 * 查询非失败状态任务记录
	 * 
	 * @param deviceId
	 * @param type
	 * @return
	 */
	AnalysisTask queryExistedTask(@Param("deviceId") String deviceId, @Param("type") Integer type);

	/**
	 *
	 * @return
	 */
    List<String> getCreatedDevices();
}