/*
 * www.unisinsight.com Inc.
 * Copyright (c) 2018 All Rights Reserved
 */
package com.unisinsight.vdp.core.controller;
import com.unisinsight.vdp.common.ia.inner.IATaskQueryResult;
import com.unisinsight.vdp.common.ia.inner.PageInfo;

import com.alibaba.fastjson.JSON;
import com.unisinsight.vdp.common.ia.inner.TaskAlgorithm;
import com.unisinsight.vdp.common.ia.response.IATaskNewRes;
import com.unisinsight.vdp.common.ia.response.IATaskQueryRes;
import com.unisinsight.vdp.common.mda.response.ImageTaskNewRes;
import com.unisinsight.vdp.common.mda.response.ImageTaskQueryRes;
import org.junit.Test;

/**
 * description
 *
 * @author daisike [dai.sike@h3c.com]
 * @date 2018/10/11 18:55
 * @since 1.0
 */
public class MockJSONTest {

    @Test
    public void iaTaskNewResMock(){
        IATaskNewRes iAtaskNewRes = new IATaskNewRes();
        TaskAlgorithm[] taskAlgorithms = new TaskAlgorithm[1];
        TaskAlgorithm taskAlgorithm = new TaskAlgorithm();
        taskAlgorithm.setAlgorithmId("008");
        taskAlgorithm.setStatusCode("0");
        taskAlgorithm.setStatusMsg("成功");
        taskAlgorithms[0] = taskAlgorithm;


        iAtaskNewRes.setAlgorithmIdList(taskAlgorithms);
        iAtaskNewRes.setTaskId("10002018092210084109201");
        System.out.println(JSON.toJSONString(iAtaskNewRes));
    }

    @Test
    public void iaTaskQueryResMock(){
        IATaskQueryRes iAtaskQueryRes = new IATaskQueryRes();
        PageInfo pageInfo = new PageInfo();
        pageInfo.setTotalElement("1");
        iAtaskQueryRes.setPageInfo(pageInfo);
        IATaskQueryResult[] iAtaskQueryResults = new IATaskQueryResult[1];
        IATaskQueryResult iAtaskQueryResult = new IATaskQueryResult();
        iAtaskQueryResult.setTaskId("10002018092210084109201");
        iAtaskQueryResult.setCameraId("0000001");
        iAtaskQueryResult.setCreateTime(0L);
        iAtaskQueryResult.setTaskStatus("running");
        iAtaskQueryResult.setAlgorithmIdList(null);
        iAtaskQueryResults[0] = iAtaskQueryResult;
        iAtaskQueryRes.setData(iAtaskQueryResults);
        System.out.println(JSON.toJSONString(iAtaskQueryRes));


    }

    @Test
    public void imageTaskNewResMock(){
        ImageTaskNewRes imageTaskNewRes = new ImageTaskNewRes();
        imageTaskNewRes.setId("201812345678911");
        imageTaskNewRes.setType("0");
        imageTaskNewRes.setBeginTime("20181012170208");
        imageTaskNewRes.setEndTime("20191012170208");
        imageTaskNewRes.setCreateTime("20181012150008");
        System.out.println(JSON.toJSONString(imageTaskNewRes));

    }

    @Test
    public void imageTaskQueryResMock(){
        ImageTaskQueryRes imageTaskQueryRes = new ImageTaskQueryRes();
        imageTaskQueryRes.setId("201812345678911");
        imageTaskQueryRes.setTollgateId("000");
        imageTaskQueryRes.setType("0");
        imageTaskQueryRes.setBeginTime("20191012170208");
        imageTaskQueryRes.setEndTime("20191012170208");
        imageTaskQueryRes.setCreateTime("20191012170208");
        System.out.println(JSON.toJSONString(imageTaskQueryRes));

    }
}
