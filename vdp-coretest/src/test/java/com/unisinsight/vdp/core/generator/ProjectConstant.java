/*
 * www.unisinsight.com Inc.
 * Copyright (c) 2018 All Rights Reserved
 */
package com.unisinsight.vdp.core.generator;

/**
 * 项目常量
 */
public final class ProjectConstant {

    //生成代码所在的基础包名称，可根据自己公司的项目修改（注意：这个配置修改之后需要手工修改src目录项目默认的包路径，使其保持一致，不然会找不到类）
    public static final String BASE_PACKAGE = "com.unisinsight.vdp.core";

    //生成的Model所在包
    public static final String MODEL_PACKAGE = BASE_PACKAGE + ".model";

    //生成的Mapper所在包
    public static final String MAPPER_PACKAGE = BASE_PACKAGE + ".mapper";

    //生成的Service所在包
    public static final String SERVICE_PACKAGE = BASE_PACKAGE + ".service";

    //生成的ServiceImpl所在包
    public static final String SERVICE_IMPL_PACKAGE = SERVICE_PACKAGE + ".impl";

    //生成的Controller所在包
    public static final String CONTROLLER_PACKAGE = BASE_PACKAGE + ".controller";

    //生成的dto request所在包
    public static final String DTO_REQ_PACKAGE = BASE_PACKAGE + ".dto.request";

    //生成的dto response所在包
    public static final String DTO_RES_PACKAGE = BASE_PACKAGE + ".dto.response";

    //生成的Convert所在包
    public static final String MODELCONVERT_PACKAGE = SERVICE_PACKAGE + ".convert";

    //Mapper插件基础接口的完全限定名
    public static final String MAPPER_INTERFACE_REFERENCE = "com.unisinsight.framework.common.base.Mapper";

    //生成的dto response 文件名后缀
    public static final String DTO_RES_SUFFIX = "ResDTO";

    //生成的dto request 文件名后缀
    public static final String DTO_REQ_SUFFIX = "ReqDTO";
}
