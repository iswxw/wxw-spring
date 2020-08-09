package com.wxw.service.impl;

import com.wxw.aop.tools.SysLog;
import com.wxw.service.TestService;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @ Author ：wxw.
 * @ Date ： 12:44 2020/6/1
 * @ Description：实现层
 * @ Version:   $
 */
@Service
public class TestServiceImpl implements TestService {

    //@SysLog(value = 2020)
    public String testPrint() {

        try { TimeUnit.SECONDS.sleep(2); } catch (InterruptedException e) {  }
        return "成功";
    }

    @SysLog(message = "自定义名称")
    public String testPrint1(String name) {

        return name;
    }
}
