package com.wxw.common.aop.log;

import com.alibaba.fastjson.JSON;
import com.wxw.common.aop.annotation.SysLog;
import com.wxw.domain.ExceptionInfo;
import com.wxw.domain.LogInfo;
import com.wxw.service.LogService;
import com.wxw.tools.DateTools;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @ Author ：wxw.
 * @ Date ： 15:52 2020/6/5
 * @ Description：日志监听本地打印 前置通知+后置通知
 * @ link: https://juejin.cn/post/6844904087964614670
 * @ Version:   v_0.0.1
 */
@Aspect
@Component
public class LocalLogAspect {
    private static Logger logger = LoggerFactory.getLogger(LocalLogAspect.class);
    @Resource
    private LogService logService;

    // 切入点方法
    @Pointcut("execution(* com.wxw.service.impl..*.*(..))")
    public void webLog() {
    }

    @Around("webLog() && @annotation(sysLog)")
    public Object doAround(ProceedingJoinPoint joinPoint, SysLog sysLog) throws Throwable {
        long startTime = System.currentTimeMillis();
        logger.info("===============================Start========================");
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        Object result = joinPoint.proceed();
        LogInfo logInfo = new LogInfo();
        logInfo.setStartTime(DateTools.getLong2YyyyMmDdHhMmSs(startTime));
        logInfo.setRemoteIp(request.getRemoteAddr());
        logInfo.setRequestUrl(request.getRequestURL().toString());
        logInfo.setHttpMethod(request.getMethod());
        logInfo.setRequestParams(getRequestParamsByJoinPoint(joinPoint));
        final Signature signature = joinPoint.getSignature();
        logInfo.setClassMethodName(
                String.format("%s.%s",signature.getDeclaringTypeName(), signature.getName()));
        logInfo.setResultMsg(result); // 不传新参数 执行目标方法
        final long endTime = System.currentTimeMillis();
        logInfo.setRunTime((endTime - startTime) + "ms");
        logInfo.setEndTime(DateTools.getLong2YyyyMmDdHhMmSs(endTime));
        logInfo.setRunStatus("SUCCESS");
        logInfo.setCreateDate(logInfo.getEndTime());
        logInfo.setSysLogVal(sysLog.value());
        logInfo.setSysLogMsg(sysLog.message());
        logger.info("Request Info      : {}", JSON.toJSONString(logInfo));
        logger.info("===============================End========================");
        return result;
    }

    @AfterThrowing(pointcut = "webLog() && @annotation(sysLog)",throwing = "exception")
    public void afterThrowing(JoinPoint joinPoint,SysLog sysLog,RuntimeException exception){
        long startTime = System.currentTimeMillis();
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        assert attributes != null;
        HttpServletRequest request = attributes.getRequest();
        LogInfo logInfo = new LogInfo();
        logInfo.setRemoteIp(request.getRemoteAddr());
        logInfo.setRequestUrl(request.getRequestURL().toString());
        logInfo.setHttpMethod(request.getMethod());
        final Signature signature = joinPoint.getSignature();
        logInfo.setResultMsg(getException(exception));
        logInfo.setRequestParams(getRequestParamsByJoinPoint(joinPoint));
        logInfo.setClassMethodName(String.format("%s.%s",signature.getDeclaringTypeName(),
                signature.getName()));
        long endTime = System.currentTimeMillis();
        logInfo.setRunTime((endTime - startTime) + "ms");
        logInfo.setEndTime(DateTools.getLong2YyyyMmDdHhMmSs(endTime));
        logInfo.setRunStatus("FAILED");
        logInfo.setCreateDate(logInfo.getEndTime());
        logInfo.setSysLogVal(sysLog.value());
        logInfo.setSysLogMsg(sysLog.message());
        logger.info("消耗时间 SPEND TIME : " + (endTime -startTime+"ms"));
        logger.info("Error Request Info      : {}", JSON.toJSONString(logInfo));
        logger.info("===============================End========================");
        logger.error(JSON.toJSONString(exception));
    }

    /**
     * 获取 带有@SysLog 注解方法的 入参参数
     * @param joinPoint
     * @return
     */
    private Map<String, Object> getRequestParamsByJoinPoint(JoinPoint joinPoint) {
        //参数名
        String[] paramNames = ((MethodSignature)joinPoint.getSignature()).getParameterNames();
        //参数值
        Object[] paramValues = joinPoint.getArgs();
        Map<String, Object> requestParams = new HashMap<>();
        for (int i = 0; i < paramNames.length; i++) {
            Object value = paramValues[i];
            //如果是文件对象
            if (value instanceof MultipartFile) {
                MultipartFile file = (MultipartFile) value;
                value = file.getOriginalFilename();  //获取文件名
            }
            requestParams.put(paramNames[i], value);
        }
        return requestParams;
    }

    // 获取异常信息
    private ExceptionInfo getException(RuntimeException exception) {
        ExceptionInfo exceptionInfo = new ExceptionInfo();
        StackTraceElement stackTrace = exception.getStackTrace()[0];
        exceptionInfo.setExceptionType(exception.getClass().getTypeName());
        exceptionInfo.setExceptionMessage(exception.getMessage());
        exceptionInfo.setLocation(String.format("%s:%s",stackTrace.getClassName(),stackTrace.getLineNumber()));
        // exceptionInfo.setDetailMessage(exception);
        return exceptionInfo;
    }
}
