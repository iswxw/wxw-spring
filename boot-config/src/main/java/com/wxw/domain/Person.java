package com.wxw.domain;

import lombok.*;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 方式1 @ConfigurationProperties
 * 方式2 @Value
 * 将配置文件中配置的每一个属性的值，映射到这个组件中
 * @ConfigurationProperties：告诉SpringBoot将本类中的所有属性和配置文件中相关的配置进行绑定；
 *      prefix = "person"：配置文件中哪个下面的所有属性进行一一映射
 *
 * 只有这个组件是容器中的组件，才能容器提供的@ConfigurationProperties功能；
 *  @ConfigurationProperties(prefix = "person")默认从全局配置文件中获取值；
 *
 *  指定具体的配置文件
 *  @PropertySource(value = {"classpath:person.properties"})
 */
// 启动JSR303校验 ConfigurationProperties 支持 但是Value()不支持
//@Validated
    //使用 properties配置文件
//@PropertySource(value = {"classpath:person.properties"})
@Component
@ConfigurationProperties(prefix = "person")
@Data
public class Person {

    /**
     * <bean class="Person">
     *      <property name="lastName" value="字面量/${key}从环境变量、配置文件中获取值/#{SpEL}"></property>
     * <bean/>
     */
    //username 必须是邮箱格式
    //@Email
    //@Value("${person.username}")
    private String username;
    //@Value("#{2*9}")
    private Integer age;
    //@Value("${person.gender}")
    private Boolean gender;
    private Date birth;

    private Map<String, Object> maps;
    private List<Object> lists;
    private Dog dog;

}
