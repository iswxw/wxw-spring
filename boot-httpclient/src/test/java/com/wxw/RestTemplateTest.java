package com.wxw;

import com.wxw.manager.client.HttpRequestHelper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import javax.annotation.Resource;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: wxw
 * @date: 2021-04-17-1:29
 * @link:
 * @description:
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class RestTemplateTest {

  @Resource
  private HttpRequestHelper httpRequestHelper;


    /**
     * 二进制流作为请求体 上传文件
     * body            请求体
     * headers         请求头
     * requestParams   请求参数
     * HttpEntity(@Nullable T body, @Nullable MultiValueMap<String, String> headers)
     */
    @Test
    public void testMethod() throws Exception {
        // 请求头
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/octet-stream");
        File file = new File("/tmp/1.jpeg");
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        byte[] fileByte = outputStream.toByteArray();
        // 请求体
        HttpEntity<byte[]> entity = new HttpEntity<byte[]>(fileByte, headers);
        RestTemplate restTemplate = new RestTemplate();
        // 封装请求参数
        MultiValueMap<String, String> requestParams = new LinkedMultiValueMap<>();
        requestParams.add("expires", "259200");
        requestParams.add("dir", "filepath");
        String url = "http://localhost/binaryUpload/upload?fileName=test.jpg";
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url)
                .queryParams(requestParams);
        Object uploadResponse = restTemplate.postForObject(builder.toUriString(), entity, Object.class);
        Assert.assertNotNull(uploadResponse);
    }

    @Test
    public void test_rest() {
        String url="http://localhost:8080/getList";
        //组装请求参数
        Map<String,Object> parmMap =new HashMap<String,Object>();
        String result = httpRequestHelper.get(url, parmMap);
        System.out.println(result);
    }
}
