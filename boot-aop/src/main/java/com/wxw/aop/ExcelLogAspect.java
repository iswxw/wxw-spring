package com.wxw.aop;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.util.IdUtil;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.wxw.domain.SysLogEntity;
import com.wxw.tools.DateTools;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
//@Aspect
//@Component
@Deprecated
public class ExcelLogAspect {

    private Logger logger = LoggerFactory.getLogger(ExcelLogAspect.class);

    public List<SysLogEntity> entityList = new ArrayList<>();

    ThreadLocal<Long> startTime = new ThreadLocal<Long>();

    private final String pathExcel= "D:\\Project\\wxw2020\\Itcast-springboot\\boot-aop\\src\\main\\java\\com\\wxw\\doc\\";

    @Pointcut("execution(* com.wxw.service.impl..*.*(..))")
    public void webLog() {
    }
    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {
        startTime.set(System.currentTimeMillis());
        // 日志记录
        save(joinPoint);
        System.out.println("joinPoint = " + "日志记录内容");
    }

    @AfterReturning(returning = "ret", pointcut = "webLog()")
    public void doAfterReturning(Object ret) throws Throwable {
        String midTime = System.currentTimeMillis() - startTime.get()+ "ms";
        // 处理完请求，返回内容
        String time1 = DateFormatUtils.format(new Date(startTime.get()), DatePattern.NORM_DATETIME_PATTERN);
        // 添加运行时间
        List<SysLogEntity> sysLogEntities = entityList.stream().map(sysLogEntity -> {
            sysLogEntity.setRunTime((int)(System.currentTimeMillis() - startTime.get()));
            sysLogEntity.setStartTime(time1);
            return sysLogEntity;
        }).collect(Collectors.toList());

        // 输出Excel
        exportExcel(sysLogEntities);
        logger.info("响应参数 RESPONSE : " + ret);
        logger.info("消耗时间 SPEND TIME : " + (System.currentTimeMillis() - startTime.get()+"ms"));
        // 释放线程信息
        startTime.remove();
    }

    private void exportExcel(List<SysLogEntity> sysLogEntities) {
        // 通过工具类创建writer
        ExcelWriter writer = ExcelUtil.getWriter( pathExcel+"readme"+ IdUtil.randomUUID() +".xlsx");

        // 指定宽度
        //自定义标题别名
        writer.addHeaderAlias("pathName", "全路径");
        writer.addHeaderAlias("className", "类名");
        writer.addHeaderAlias("methodName", "方法名");
        writer.addHeaderAlias("argsName", "方法参数");
        writer.addHeaderAlias("startTime", "开始执行时间");
        writer.addHeaderAlias("runTime", "运行时间");
        writer.addHeaderAlias("createDate", "创建时间");

        // 合并单元格后的标题行，使用默认标题样式
        //writer.merge(4, "方法运行时间统计");
        // 一次性写出内容，使用默认样式，强制输出标题
        writer.write(sysLogEntities, true);
        // 关闭writer，释放内存
        writer.close();
    }


    private void save(JoinPoint joinPoint) {
        Signature signature1 = joinPoint.getSignature();
        logger.info("ServiceImpl 全路径==>signature ==>{}",signature1.getClass().getName());
        logger.info("ServiceImpl 方法的实现==>signature ==>{}", signature1.getDeclaringTypeName());
        logger.info("ServiceImpl 方法名称==>signature ==>{}", signature1.getName());
        logger.info("ServiceImpl 方法==>signature ==>{}", joinPoint.getTarget().getClass().getName());
        logger.info("ServiceImpl 方法==>signature ==>{}", joinPoint.getSourceLocation());
        logger.info("ServiceImpl 方法==>signature ==>{}", joinPoint.getStaticPart());
        // 获取方法的关键信息，类，包 路径

        SysLogEntity sysLogEntity = new SysLogEntity();
        sysLogEntity.setPathName(signature1.toString().split(" ")[1]);
        sysLogEntity.setClassName(signature1.getDeclaringTypeName());
        sysLogEntity.setMethodName(signature1.getName());
        sysLogEntity.setArgsName(Arrays.toString(joinPoint.getArgs()));
        sysLogEntity.setRunTime(null);
        sysLogEntity.setCreateDate(DateTools.getLong2YyyyMmDdHhMmSs(System.currentTimeMillis()));
        entityList.add(sysLogEntity);
        log.info("sysLogEntity=>{}", sysLogEntity);
    }

}