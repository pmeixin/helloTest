package com.unisinsight.vdp.core.controller;

import com.alibaba.fastjson.JSON;
import com.unisinsight.framework.common.core.annotation.Log;
import com.unisinsight.vdp.core.common.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.io.UnsupportedEncodingException;
import java.util.Map;

/**
 * TODO 根据fid转成图片云存储URL
 * ImageFid2UrlController
 *
 * @author zhoubiao [KF.zhoubiao@h3c.com]
 * @date 2018-10-18 16:38:53
 * @since 1.0
 */
@RestController
@RequestMapping("/api/vdp/v1/core/get-image-url")
public class ImageFid2UrlController {

    @Autowired
    private RestTemplate restTemplate;

    public ImageFid2UrlController() {
    }

    @ResponseBody
    @RequestMapping(method=RequestMethod.GET)
    @Log(module = Constants.LOGGER_MODEL, action = "根据fid转成图片云存储URL")
    public Map getImageUrl(String fids){
        StringBuilder sb = new StringBuilder();
        sb.append(Constants.FID_URL+"?");
        String[] fidArray = fids.split(",");
        boolean isFirst = true;
        for(String fid:fidArray){
            if(isFirst){
                sb.append(fid);
                isFirst = false;
            }else {
                sb.append("&"+fid);
            }

        }
        String result = restTemplate.getForObject(sb.toString(),String.class);
        result = result.replaceAll("\\n", "");
        result = result.replaceAll("\\t", "");
        Map resultMap = JSON.parseObject(result);
//        return resultMap;
        return resultMap;
    }

    @RequestMapping(value = "/sayHi",method = RequestMethod.GET)
    @ResponseBody
    public String sayHi(){
        return "Hello";
    }

}
