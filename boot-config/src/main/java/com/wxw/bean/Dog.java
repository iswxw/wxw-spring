package com.wxw.bean;

import lombok.*;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 *  测试配置文件占位符
 */
@Data
public class Dog {

    private String dogName;
    private Integer dogAge;
}
