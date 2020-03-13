package com.wxw.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @Author: wxw
 * @create: 2020-03-09-21:59
 */
@Controller
@RestController
public class HelloController {

    @ResponseBody
    @RequestMapping("/hello")
    public String helloTest(){
        return "Hello World!";
    }

    // 查出一些数据在页面展示
    @RequestMapping("/success")
    public String SuccessTest(Map<String,Object> map){
        map.put("hello", "你好");
        //classpath:/templates/ 下的.html文件
        return "success";
    }
}
