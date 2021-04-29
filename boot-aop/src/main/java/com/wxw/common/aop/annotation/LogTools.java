package com.wxw.common.aop.annotation;

import com.wxw.domain.LogInfo;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

/**
 * @ Author ：wxw.
 * @ Date ： 16:12 2020/6/5
 * @ Description：Aop数据处理辅助工具类
 * @ Version:   v_0.0.1
 */
public class LogTools {

    /**
     * 定义的同时初始化类型
     */
    private static final ThreadLocal<LogInfo> localLog = new ThreadLocal<LogInfo>() {
        @Override
        protected LogInfo initialValue() {
            return new LogInfo();
        }
    };


    /**
     * 返回实体对象
     */
    public static LogInfo getLog() {
        return localLog.get();
    }

    /**
     * 完成后 释放线程信息
     */
    public static void removeLog() {
        localLog.remove();
    }

    /**
     * 扩展线程信息
     *
     * @param logEntity
     * @param joinPoint
     * @param sysLog
     */
    public static void setLogInfo(LogInfo logEntity, JoinPoint joinPoint, SysLog sysLog) {

        // 注解信息
        logEntity.setSysLogMsg(sysLog.message());
        logEntity.setSysLogVal(sysLog.value());
        // 接口信息
        Signature signature = joinPoint.getSignature();
        // 类名+方法名
        logEntity.setClassMethodName(String.format("%s.%s:%s", signature.getDeclaringTypeName(),
                signature.getName()));
        logEntity.setRequestParams(getRequestParamsByJoinPoint(joinPoint));
        logEntity.setCreateDate(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
    }

    /**
     * 获取入参
     *
     * @param joinPoint
     * @return
     */
    private static Map<String, Object> getRequestParamsByJoinPoint(JoinPoint joinPoint) {
        //参数名
        String[] paramNames = ((MethodSignature) joinPoint.getSignature()).getParameterNames();
        //参数值
        Object[] paramValues = joinPoint.getArgs();
        return buildRequestParam(paramNames, paramValues);
    }

    private static Map<String, Object> buildRequestParam(String[] paramNames, Object[] paramValues) {
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
}
