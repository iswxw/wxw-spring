package com.wxw.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

/**
 * @ Author ：wxw.
 * @ Date ： 13:54 2020/7/17
 * @ Description：登录测试
 * @ Version:   v_0.0.1
 */
@RestController
@RequestMapping("api")
public class LoginController {

    @PostMapping("/login")
    public String test(){
        System.out.println("登录测试时间 = " + LocalDate.now());
        return "you have been authenticated";
    }
}
