package com.wxw.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: wxw
 * @create: 2020-03-07-16:46
 */
@RestController
public class PersonController {

    @Value("${person.username}")
    private String username;

    @RequestMapping("/hello")
    public String sayHello(){
        System.out.println("开始打招呼！！");
        return "Hello "+username;
    }
}
