package com.wxw.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;

/**
 *  (1) Controller层面上异常处理 @ExceptionHandler 处理异常  只监控当前控制类的异常
 *   原理分析：https://www.cnblogs.com/lvbinbin2yujie/p/10574812.html#type4
 * @Author: wxw
 * @create: 2020-08-09-11:43
 */
@RestController
@RequestMapping("/interceptor")
public class TestController {

    /**
     * http://test.wxw.com:8080/interceptor/demo1
     * @return
     */
    @GetMapping("/demo1")
    public Object demo1(){
        int i = 1 / 0;
        return new Date();
    }
    // 统一处理异常 方式一
//    @ExceptionHandler({RuntimeException.class})
//    public String fix(Exception ex){
//        System.out.println(ex);
//        return "网络不给力,请稍后重试!";
//    }
}
