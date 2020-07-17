package com.wxw.controller;

import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

/**
 * @ Author ：wxw.
 * @ Date ： 12:26 2020/7/17
 * @ Description：测试端
 * @ Version:   v_0.0.1
 */
@RestController
public class TestController {

    @GetMapping("get")
    public String  testGet(){
       return "测试Get请求";
    }

    @PostMapping("post")
    public String  testPost(){
       return LocalDate.now()+ ":测试Post请求";
    }

    @GetMapping("dev")
    public String testDev(@RequestParam("id")String id){
         return LocalDate.now()+ ":开发环境测试 Dev ---"+id;
    }

    @GetMapping("prod")
    public String testProd(@RequestParam("id")String id){
        return LocalDate.now()+ ":生产环境测试 Prod ---"+id;
    }

}
