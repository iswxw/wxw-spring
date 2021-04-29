package com.wxw.service.impl;

import com.wxw.common.aop.annotation.SysLog;
import com.wxw.service.TestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @ Author ：wxw.
 * @ Date ： 12:44 2020/6/1
 * @ Description：实现层
 * @ Version:   $
 */
@Service
@Slf4j
public class TestServiceImpl implements TestService {

    @SysLog(value = 2020)
    public String testPrint(String name, int age) {
        log.info(name,age);
        try { TimeUnit.SECONDS.sleep(2); } catch (InterruptedException e) {  }
        return "成功";
    }

    // @SysLog 该注解进行 AOP 日志拦截
    @SysLog(message = "自定义名称")
    public String testPrint1(String name) {
         int num =  1/0;
        return name;
    }
}
