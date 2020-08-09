package com.wxw.aop.tools;

import com.wxw.domain.SysLogEntity;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

/**
 * @ Author ：wxw.
 * @ Date ： 16:12 2020/6/5
 * @ Description：Aop数据处理辅助工具类
 * @ Version:   v_0.0.1
 */
public class LogTools {

    /**
     *  定义的同时初始化类型
     */
    private static final ThreadLocal<SysLogEntity> localLog = new ThreadLocal<SysLogEntity>(){
        @Override
        protected SysLogEntity initialValue() {
            return new SysLogEntity();
        }
    };


    /**
     * 返回实体对象
     */
    public static SysLogEntity getLog(){
        return localLog.get();
    }
    /**
     * 完成后 释放线程信息
     */
    public static void removeLog(){
        localLog.remove();
    }

    /**
     * 扩展线程信息
     * @param logEntity
     * @param joinPoint
     * @param sysLog
     */
    public static void setLogInfo(SysLogEntity logEntity, JoinPoint joinPoint, SysLog sysLog) {

        // 注解信息
        logEntity.setSysLogMsg(sysLog.message());
        logEntity.setSysLogVal(sysLog.value());
        // 接口信息
        Signature signature = joinPoint.getSignature();
        logEntity.setPathName(signature.toString().split(" ")[1]);
        logEntity.setClassName(signature.getDeclaringTypeName());
        logEntity.setMethodName(signature.getName());
        Object[] args = joinPoint.getArgs();
        if (args.length < 1) {
            logEntity.setArgsName("该方法没有参数");
        }
        List<Object> arglist = Arrays.asList(args);
        logEntity.setArgsName(StringUtils.strip(arglist.toString(),"[]"));
        logEntity.setCreateDate(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
    }
}
