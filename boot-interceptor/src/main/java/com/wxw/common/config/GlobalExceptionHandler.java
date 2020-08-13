package com.wxw.common.config;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @ Author ：wxw.
 * @ Date ： 9:19 2020/8/13
 * @ Description：全局异常处理拦截
 * @ Version:   v_0.0.1
 * (3) 全局级别异常处理器 @ControllerAdvice+@ExceptionHandler
 *   监控所有Controller层异常
 */
//@ControllerAdvice // 相当于通知
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public String fix1(Exception e){
        System.out.println("全局的异常处理器");
        return "网络不给力，请稍后重试！";
    }
}
