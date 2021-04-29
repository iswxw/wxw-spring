package com.wxw.domain;

import lombok.Data;

/**
 * @author ：wxw.
 * @date ： 11:55 2021/3/23
 * @description：日志信息
 * @link:
 * @version: v_0.0.1
 */
@Data
public class LogInfo {
    private String remoteIp;
    private String url;
    private String httpMethod;
    private String classMethod;
    private Object requestParams;
    private Object result;
    private Long timeCost;
}
