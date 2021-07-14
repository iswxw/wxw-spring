package com.wxw.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author weixiaowei
 * @desc:
 * @date: 2021/7/14
 */
@RestController
public class DefaultController {

    /**
     * curl http://localhost:8081/
     * @return
     */
    @GetMapping("/")
    public String defaultClass() {
        return "测试接口";
    }
}
