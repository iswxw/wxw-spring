package com.wxw.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ：wxw.
 * @date ： 11:50 2021/3/23
 * @description：日志测试类
 * @link:
 * @version: v_0.0.1
 */
@RestController
@RequestMapping("log")
@Slf4j
public class LogController {

    /**
     *
     * @return
     */
    @GetMapping("/get")
    public ResponseEntity<String> getLog() {
        int i = 100000;
        while (i > 0) {
            log.info("你一定要悄悄拔尖，然后超越所有人！");
            i--;
        }
        return ResponseEntity.ok("日志测试返回结果！！");
    }
}
