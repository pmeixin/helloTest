package com.unisinsight.vdp.core.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.unisinsight.framework.common.core.annotation.Log;
import com.unisinsight.vdp.core.common.utils.Constants;
import com.unisinsight.vdp.core.dto.request.AlgorithmRepositoryFilterReqDTO;
import com.unisinsight.vdp.core.dto.request.AlgorithmRepositoryReqDTO;
import com.unisinsight.vdp.core.dto.response.AlgorithmRepositoryResDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.util.MimeType;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.nio.charset.Charset;

/**
 *
 * @author zhoubiao [KF.zhoubiao@h3c.com]
 * @date 2018-10-24 17:49:53
 * @since 2.0
 */
@RestController
@RequestMapping("/api/iax/v1/algorithm-repository")
public class AlgorithmRepositoryController {

    @Autowired
    private RestTemplate restTemplate;

    @DeleteMapping("/{id}")
//    @Log(module = Constants.LOGGER_MODEL, action = "算法仓库")
    public JSONObject delete(@PathVariable String id) {
        String url = Constants.AIX_URL+"/api/aix/v1/algorithm-repository/"+id;

        // 请求头
        HttpHeaders headers = new HttpHeaders();
        MimeType mimeType = MimeTypeUtils.parseMimeType("application/json");
        MediaType mediaType = new MediaType(mimeType.getType(), mimeType.getSubtype(), Charset.forName("UTF-8"));
        // 请求体
        headers.setContentType(mediaType);

        // 发送请求
        HttpEntity entity = new HttpEntity(headers);

        ResponseEntity<String> resultEntity = restTemplate.exchange(url,HttpMethod.DELETE, entity, String.class);
        JSONObject result = (JSONObject) JSON.parse(resultEntity.getBody());
        return result;

    }

    @PutMapping("/{id}")
//    @Log(module = Constants.LOGGER_MODEL, action = "算法仓库")
    public JSONObject update(@PathVariable String id,@RequestBody @Valid AlgorithmRepositoryReqDTO algorithmRepositoryReqDTO) {
        String url = Constants.AIX_URL+"/api/aix/v1/algorithm-repository/"+id;
//        restTemplate.put(url,algorithmRepositoryReqDTO);

        // 请求头
        HttpHeaders headers = new HttpHeaders();
        MimeType mimeType = MimeTypeUtils.parseMimeType("application/json");
        MediaType mediaType = new MediaType(mimeType.getType(), mimeType.getSubtype(), Charset.forName("UTF-8"));
        // 请求体
        headers.setContentType(mediaType);

        // 发送请求
        HttpEntity<AlgorithmRepositoryReqDTO> entity =
                new HttpEntity<AlgorithmRepositoryReqDTO>(algorithmRepositoryReqDTO,headers);

        ResponseEntity<String> resultEntity = restTemplate.exchange(url,HttpMethod.PUT, entity, String.class);
//        return resultEntity.getBody();
        JSONObject jsonObject = (JSONObject) JSON.parse(resultEntity.getBody());
        return jsonObject;
    }

    @PostMapping("/filter-query")
//    @Log(module = Constants.LOGGER_MODEL, action = "算法仓库")
    public JSONObject filterQuery(@RequestBody @Valid AlgorithmRepositoryFilterReqDTO  algorithmRepositoryFilterReqDTO) {
        String url = Constants.AIX_URL+"/api/aix/v1/algorithm-repository/filter-query";
//        AlgorithmRepositoryResDTO algorithmRepositoryResDTO =
//                restTemplate.postForObject(url,algorithmRepositoryFilterReqDTO,AlgorithmRepositoryResDTO.class);
        String requestString = JSON.toJSONString(algorithmRepositoryFilterReqDTO);
        String result = restTemplate.postForObject(url,requestString,String.class);
        JSONObject jsonObject = (JSONObject) JSON.parse(result);

        return jsonObject;
    }

}
