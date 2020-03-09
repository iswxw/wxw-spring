package com.wxw.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: wxw
 * @create: 2020-03-09-21:59
 */
@RestController
public class HelloController {

    @ResponseBody
    @RequestMapping("/hello")
    public String helloTest(){
        return "Hello World!";
    }
}
