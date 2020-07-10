package com.wxw.boot.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ Author ：wxw.
 * @ Date ： 13:09 2020/7/10
 * @ Description：控制层
 * @ Version:   v_0.0.1
 */
@RestController
public class UserController {

    @GetMapping("/hello")
    public String hello() {
        return "hello world";
    }

}
