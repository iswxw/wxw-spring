package com.wxw.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author: wxw
 * @create: 2020-03-04-14:24
 */
@Controller
public class  HelloController{

    @ResponseBody
    @RequestMapping("/hello")
    public String hello(){
        String threadName = Thread.currentThread().getName();
        return threadName;
    }
}
