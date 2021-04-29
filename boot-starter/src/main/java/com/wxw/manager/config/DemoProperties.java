package com.wxw.manager.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author: wxw
 * @date: 2020-12-23-22:00
 * @description: 配置信息 实体
 */
@ConfigurationProperties(prefix = "demo")
public class DemoProperties {

    private String sayWhat;
    private String toWho;

    public String getSayWhat() {
        return sayWhat;
    }

    public void setSayWhat(String sayWhat) {
        this.sayWhat = sayWhat;
    }

    public String getToWho() {
        return toWho;
    }

    public void setToWho(String toWho) {
        this.toWho = toWho;
    }
}
