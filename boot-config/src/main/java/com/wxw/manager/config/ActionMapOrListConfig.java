package com.wxw.manager.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// #要注意的地方是 属性名 要与 XXX.properties中的参数的名保持一致，详见属性注解
@Data
//@Configuration 标识这是一个配置类
@Component // 添加到bean容器时指定其名为 configData
@ConfigurationProperties(prefix = "data")  // 读取前缀为 data 的内容
//如果只有一个主配置类文件，@PropertySource可以不写
//@PropertySource("classpath:XXX.properties")
@PropertySource(value = { "classpath:config/action.properties" }, encoding = "utf-8")
public class ActionMapOrListConfig {

    /**
     * data.person.name
     * 这里map名需要和XXX.properties中的参数data.person.name的person一	 致
     */
    private Map<String, String> person = new HashMap<>();
    /**
     * data.list
     * 这里list名需要和XXX.properties中的参数data.list的list一致
     */
    private List<String> list = new ArrayList<>();
}
