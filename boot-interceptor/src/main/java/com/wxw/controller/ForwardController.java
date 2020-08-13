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
 * @ Description：请求转发测试类
 *    https://www.cnblogs.com/wwct/articles/12275815.html
 *    https://www.cnblogs.com/harrychinese/p/SpringBoot_redirect_and_forward.html
 * @ Version:   v_0.0.1
 *  注意请求转发
 *    - 必须使用@Controller而非@RestController
 *    - 必须加 @ResponseBody 响应类型
 */
@Controller
public class ForwardController {

    // =====================请求转发========================
    /**
     * 重定向 再转发测试
     * http://test.wxw.com:8080/forward
     * @return
     */
    @GetMapping("/forward")
    public String demo3() {
        System.out.println("请求转发测试开始");
        return "forward:/success";
    }

    /**
     * 成功路径测试
     * http://test.wxw.com:8080/success
     *
     * @return
     */
    @GetMapping("/success")
    @ResponseBody
    public String demo2() {
        return new Date() + "成功路径测试";
    }

    // =====================重定向========================
    @RequestMapping("/BB")
    public String BB(){
        System.out.println("我是 BB");
        return "redirect:/AA";
    }

    @RequestMapping("/AA")
    @ResponseBody
    public String AA(){
        System.out.println("我是 AA");
        return "从BB重定向到AA了，现在时间是："+ LocalDateTime.now();
    }
}
