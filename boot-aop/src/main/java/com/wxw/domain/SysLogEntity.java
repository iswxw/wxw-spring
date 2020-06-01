package com.wxw.domain;


import lombok.Data;

import java.time.LocalDateTime;

@Data
public class SysLogEntity {
    // 全路径名
    private String pathName;
    // 类名
    private String className;
    // 方法名
    private String methodName;
    // 开始执行时间
    private String startTime;
    // 运行时间
    private String runTime;
    // 创建时间
    private LocalDateTime createDate;
}