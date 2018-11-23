package com.unisinsight.vdp.core.manager.integration.vms.pre.webservice;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.unisinsight.vdp.core.manager.integration.vms.pre.VmsServicePreImpl;


/**
 * webservice配置
 *
 * @author  daisike [dai.sike@unisinsight.com]
 * @date    2018/9/7 9:39
 * @since   1.0
 */
@Component
public class WebServiceConfig implements InitializingBean {

    @Value("${vms.url.webservice}")
    private String url;

    public static  String urlPath;

    @Override
    public void afterPropertiesSet() throws Exception {
        urlPath = url;
        VmsServicePreImpl.init();
    }

}
