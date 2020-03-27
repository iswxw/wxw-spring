package com.wxw;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.wxw.mapper")
@SpringBootApplication
public class BootMybatisPlusApplication {

    public static void main(String[] args) {
        SpringApplication.run(BootMybatisPlusApplication.class, args);
    }

}
