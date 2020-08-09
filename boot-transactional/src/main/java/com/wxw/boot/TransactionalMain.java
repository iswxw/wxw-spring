package com.wxw.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ Author ：wxw.
 * @ Date ： 13:03 2020/7/10
 * @ Description：事务测试启动类
 * @ Version:   v_0.0.1
 * http://blog.didispace.com/spring-boot-learning-21-3-10/
 */
@SpringBootApplication
public class TransactionalMain {
    public static void main(String[] args) {
        SpringApplication.run(TransactionalMain.class,args);
    }

//    @RestController
//    static class TextController {
//        @GetMapping("/hello")
//        public String hello() {
//            return "hello world";
//        }
//    }

}
