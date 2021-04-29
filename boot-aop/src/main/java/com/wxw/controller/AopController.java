package com.wxw.controller;

import com.wxw.service.TestService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/aop")
public class AopController {


    @Resource
    private TestService testService;

    /**
     *  http://localhost:8080/aop/test?name=Tom&age=18
     * @param name
     * @param age
     * @return
     */
    @GetMapping("/test")
    public String test(@RequestParam("name") String name, @RequestParam("age") int age){
        String result = testService.testPrint(name,age);
        return name + ", " + age+":"+result;
    }

    /**
     * http://localhost:8080/aop/hello?name="wxw"
     * @param name
     * @return
     */
    @GetMapping("/hello")
    public String hello(@RequestParam String name) {
        String result = testService.testPrint1(name);
        return "Hello " + result;
    }

}