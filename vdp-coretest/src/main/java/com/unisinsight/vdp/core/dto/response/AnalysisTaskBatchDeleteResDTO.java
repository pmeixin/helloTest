/*
 * www.unisinsight.com Inc.
 * Copyright (c) 2018 All Rights Reserved
 */
package com.unisinsight.vdp.core.dto.response;

import java.io.Serializable;
import java.util.List;

/**
 * description
 *
 * @author daisike [dai.sike@unisinsight.com]
 * @date 2018/10/11 09:43
 * @since 1.0
 */
public class AnalysisTaskBatchDeleteResDTO implements Serializable {

	private static final long serialVersionUID = 1519598635317738991L;

	private List<String> successIds;

	private List<String> failIds;

    public List<String> getSuccessIds() {
        return successIds;
    }

    public void setSuccessIds(List<String> successIds) {
        this.successIds = successIds;
    }

    public List<String> getFailIds() {
        return failIds;
    }

    public void setFailIds(List<String> failIds) {
        this.failIds = failIds;
    }
}
