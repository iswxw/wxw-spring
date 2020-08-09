package com.wxw.controller;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * (3) 全局级别异常处理器 @ControllerAdvice+@ExceptionHandler
 *   监控所有Controller层异常
 */
//@ControllerAdvice // 相当于通知
@RestControllerAdvice
public class GlobalController {

    @ExceptionHandler(RuntimeException.class)
    public String fix1(Exception e){
        System.out.println("全局的异常处理器");
        return "网络不给力，请稍后重试！";
    }
}