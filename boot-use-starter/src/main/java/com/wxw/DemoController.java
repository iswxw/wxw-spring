package com.wxw;

import com.wxw.service.DemoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author: wxw
 * @date: 2020-12-23-22:13
 * @description:
 */
@RestController
public class DemoController {

    @Resource
    private DemoService demoService;

    @GetMapping("/say")
    public String sayWhat(){
        return demoService.say();
    }
}
