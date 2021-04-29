package com.wxw.manager.client;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.junit.platform.commons.util.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import javax.annotation.Resource;
import java.io.ByteArrayOutputStream;
import java.net.URI;
import java.util.List;
import java.util.Map;

/**
 * @author weixiaowei
 * @desc:
 * @date: 2021/4/23
 */
@Slf4j
@Component
public class FileServiceClient {

    @Value("${oss.host:http://localhost:8080}")
    private String host;

    @Value("${oss.cdn:cdnName}")
    private String cdn;

    @Value("${oss.bucket-name: bucketName}")
    private String bucket;

    /**
     * 默认文件上传路径
     */
    @Value("${oss.bucket.path:pathXx}")
    private String path;

    @Value("${oss.get-uid:/bucket/saveConfig}")
    public String getUidUrl;

    @Resource
    private RestTemplate restTemplate;

    /**
     * 返回下载链接
     *
     * @param url
     * @param expires 文件临时链接有效时间 120（单位：秒，默认为60秒） 这里设置为 3天
     * @return
     */
    public String downloadFile(String url, String expires) {
        if (StringUtils.isBlank(expires)) {
            expires = String.valueOf(259200);
        }
        // 请求参数
        MultiValueMap<String, String> requestBody = new LinkedMultiValueMap<>();
        requestBody.add("path", url);
        requestBody.add("uid", getFileUid());
        requestBody.add("expires", expires);
        // 发送上传请求
        HttpHeaders headers = new HttpHeaders();
        // 请求体
        HttpEntity<String> request = new HttpEntity<>(headers);
        UriComponentsBuilder builder = UriComponentsBuilder
                .fromHttpUrl(host + "/privateUpload/getDownloadUrl")
                .queryParams(requestBody);
        ResponseEntity<JSONObject> responseEntity =
                restTemplate.postForEntity(builder.toUriString(), request, JSONObject.class);
        JSONObject body = responseEntity.getBody();
        log.info("body = {}", body);
        if (body != null && body.isEmpty()) {
            log.error("download file get url is failed! responseEntity = {}", JSON.toJSONString(responseEntity));
        }
        Object data = body.get("data");
        Map map = JSON.parseObject(JSON.toJSONString(data), Map.class);
        if (!CollectionUtils.isEmpty(map)){
            return map.get("downloadUrl").toString();
        }
        return null;
    }


    /**
     * 二进制流上传文件
     *
     * @param filePath     文件存放路径
     * @param fileName     log-excel.xlsx
     * @param outputStream
     * @return
     */
    public String binaryUploadFile(String filePath, ByteArrayOutputStream outputStream, String fileName) {
        if (StringUtils.isNotBlank(filePath)) {
            this.path = filePath;
        }
        String fileType = fileName.substring(fileName.indexOf("."));
        // 封装请求参数
        MultiValueMap<String, String> requestParams = new LinkedMultiValueMap<>();
        requestParams.add("fileName", fileName);
        requestParams.add("uid", getFileUid());
        requestParams.add("ossBucket", bucket);
        requestParams.add("expires", "259200");
        requestParams.add("dir", path);
        requestParams.add("fext", fileType);
        log.info("requestParams = {}",JSON.toJSONString(requestParams));
        HttpHeaders headers = new HttpHeaders();
        /**
         * 二进制流作为请求体
         * @param body            请求体
         * @param headers         请求头
         * @param requestParams   请求参数
         * HttpEntity(@Nullable T body, @Nullable MultiValueMap<String, String> headers)
         */
        HttpEntity<byte[]> request = new HttpEntity<>(outputStream.toByteArray(), headers);

        // 拼接请求参数
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(host + "/binaryUpload/upload")
                .queryParams(requestParams);
        ResponseEntity<JSONObject> responseEntity =
                restTemplate.postForEntity(builder.toUriString(), request, JSONObject.class);
        log.info("responseEntity = {}", responseEntity);
        JSONObject entityBody = responseEntity.getBody();
        if (!entityBody.isEmpty()) {
            Object data = entityBody.get("data");
            Map map = JSON.parseObject(JSON.toJSONString(data), Map.class);
            Object files = map.get("files");
            return null;
        }
        return null;
    }

    /**
     * 将文件上传到业务方申请的bucket，需要将申请的bucket通过维护接口上传到storage-ng服务获取uid信息
     *
     * @return
     */
    public String getFileUid() {
        JSONObject ossParams = new JSONObject();
        ossParams.put("ossBucket", bucket);
        ossParams.put("ossCdn", cdn);
        ossParams.put("model", 1);
        URI uri = URI.create(host + getUidUrl);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        log.info("uid param = {}", ossParams);
        HttpEntity<JSONObject> requestEntity = new HttpEntity<>(ossParams, headers);
        ResponseEntity<JSONObject> exchange = restTemplate.exchange(uri, HttpMethod.POST, requestEntity, JSONObject.class);
        JSONObject exchangeBody = exchange.getBody();
        log.info("uid exchangeBody = {}", JSON.toJSONString(exchangeBody));
        return null;
    }
}
