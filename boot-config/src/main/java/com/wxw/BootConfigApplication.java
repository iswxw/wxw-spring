package com.wxw;

import com.wxw.bean.Person;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * @EnableConfigurationProperties(Person.class)  可以使得 @ConfigurationProperties： 生效
 */

@SpringBootApplication
public class BootConfigApplication {

    public static void main(String[] args) {
        SpringApplication.run(BootConfigApplication.class, args);
    }

}
