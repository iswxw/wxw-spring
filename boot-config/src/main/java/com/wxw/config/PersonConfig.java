package com.wxw.config;

import com.wxw.service.PersonService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Configuration：指明当前类是一个配置类；就是来替代之前的Spring配置文件
 *
 * 在配置文件中用<bean><bean/>标签添加组件
 *
 */
@Configuration
public class PersonConfig {

    //将方法的返回值添加到容器中；容器中这个组件默认的id就是方法名
    @Bean
    public PersonService personService02(){
        System.out.println("配置类@Bean给容器中添加组件了, bean的Id 默认是方法名");
        return new PersonService();
    }
}
