package com.wxw.domain;


import lombok.Data;

@Data
public class SysLogEntity {
    // 全路径名
    private String pathName;
    // 类名
    private String className;
    // 方法名
    private String methodName;
    // 方法参数
    private String argsName;
    // SysLog 注解信息
    private String sysLogMsg;
    // SysLog 值
    private Long sysLogVal;
    // 开始执行时间
    private String startTime;
    // 运行时间
    private Integer runTime;
    // 结束时间
    private String endTime;
    // 创建时间
    private String createDate;
    // 备注信息
    private String message;
}