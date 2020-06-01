package com.wxw.controller;

import com.wxw.aop.SysLog;
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
   // @SysLog("测试")
    @GetMapping("/test")
    public String test(@RequestParam("name") String name, @RequestParam("age") int age){
        String print = testService.testPrint();
        return name + ", " + age+":"+print;
    }

    /**
     * http://localhost:8080/aop/hello?name="wxw"
     * @param name
     * @return
     */
    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    @ResponseBody
    public String hello(@RequestParam String name) {

        String print = testService.testPrint1();
        return "Hello " + name;
    }

}