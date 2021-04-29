package com.wxw.domain;


import lombok.Data;

@Data
public class LogInfo {
    // 远程IP
    private String remoteIp;
    // 请求URL
    private String requestUrl;
    // httpMethod
    private String httpMethod;
    // 类名 + 方法名 + 行号
    private String classMethodName;
    // 方法请求参数
    private Object requestParams;
    // 请求状态
    private String runStatus;
    // 方法返回结果
    private Object resultMsg;
    // SysLog 注解备注信息
    private String sysLogMsg;
    // SysLog 的值
    private Long sysLogVal;
    // 开始执行时间
    private String startTime;
    // 运行时间
    private String runTime;
    // 执行结束时间
    private String endTime;
    // 日志创建时间
    private String createDate;
}