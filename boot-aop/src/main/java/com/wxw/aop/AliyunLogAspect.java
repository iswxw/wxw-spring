package com.wxw.aop;

import com.alibaba.fastjson.JSON;
import com.wxw.aop.tools.LogTools;
import com.wxw.aop.tools.SysLog;
import com.wxw.domain.SysLogEntity;
import com.wxw.service.LogService;
import com.wxw.tools.DateTools;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @ Author ：wxw.
 * @ Date ： 15:52 2020/6/5
 * @ Description：日志监听输出到aliyun的SLS日志服务
 * @ Version:   v_0.0.1
 */
@Aspect
@Component
public class AliyunLogAspect {

    private static Logger logger = LoggerFactory.getLogger(AliyunLogAspect.class);
    private static final ThreadLocal<Long> timeLine = new ThreadLocal<>();

    @Resource
    private LogService logService;


    @Pointcut("execution(* com.wxw.service.impl..*.*(..))")
    public void webLog() {
    }

    @Before("webLog() && @annotation(sysLog)")
    public void BeforeMethod(JoinPoint joinPoint, SysLog sysLog) throws Throwable {
        long startTime = System.currentTimeMillis();
        timeLine.set(startTime);
        SysLogEntity logEntity = LogTools.getLog();
        // 日志记录
        System.out.println("joinPoint = " + "日志记录内容");
        logEntity.setStartTime(DateTools.getLong2YyyyMmDdHhMmSs(startTime));
    }

    @AfterThrowing(pointcut = "webLog() && @annotation(sysLog)",throwing = "result")
    public void afterThrowing(JoinPoint joinPoint,SysLog sysLog,Throwable result) throws Throwable {
        afterReturning(joinPoint,sysLog,result);
    }

    @AfterReturning(returning = "result", pointcut = "webLog() && @annotation(sysLog)")
    public void afterReturning(JoinPoint joinPoint,SysLog sysLog,Object result) throws Throwable {
        try {
            long endTime = System.currentTimeMillis();
            SysLogEntity logEntity = LogTools.getLog();
            //运行时间
            logEntity.setRunTime((int)(endTime-timeLine.get()));
            // 结束时间
            logEntity.setEndTime(DateTools.getLong2YyyyMmDdHhMmSs(endTime));
            // 结果
            logEntity.setMessage(result.toString());
            // 补充扩展信息
            LogTools.setLogInfo(logEntity,joinPoint,sysLog);
            logger.info("响应参数 RESPONSE : " + result);
            logger.info("消耗时间 SPEND TIME : " + (System.currentTimeMillis() - timeLine.get()+"ms"));
            logger.info("处理完成后的完整信息 : " + JSON.toJSONString(logEntity));
            // 释放线程信息
        } finally {
            LogTools.removeLog();
        }
    }

}
