package com.wxw.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * @ Author ：wxw.
 * @ Date ： 10:34 2020/8/13
 * @ Description：请求转发测试类 https://www.cnblogs.com/wwct/articles/12275815.html
 * @ Version:   v_0.0.1
 *  注意请求转发
 *    - 必须使用@Controller而非@RestController
 *    - 必须加 @ResponseBody 响应类型
 */
@Controller
public class ForwardController {

    /**
     * 重定向 再转发测试
     * http://test.wxw.com:8080/redirect
     * @return
     */
    @GetMapping("/redirect")
    public String demo3() {
        System.out.println("重定向测试开始");
        return "forward:/success";
    }

    /**
     * 成功路径测试
     * http://test.wxw.com:8080/success
     *
     * @return
     */
    @GetMapping("/success")
    public String demo2() {
        return new Date() + "成功路径测试";
    }

    @RequestMapping("/AA")
    @ResponseBody
    public String AA(){
        System.out.println("重定向到AA了");
        return "重定向到AA了，现在时间是："+ LocalDateTime.now();
    }


    @RequestMapping("/BB")
    public String BB(){
        return "redirect:/AA";
    }
}
