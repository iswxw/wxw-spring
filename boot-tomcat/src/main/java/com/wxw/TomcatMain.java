package com.wxw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.util.StopWatch;

/**
 * @ Author ：wxw.
 * @ Date ： 10:18 2020/8/4
 * @ Description：Tomcat容器测试启动类
 * @ Version:   v_0.0.1
 */
@SpringBootApplication
public class TomcatMain {
    public static void main(String[] args) {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start("Tomcat1");
        SpringApplication.run(TomcatMain.class,args);
        stopWatch.stop();
        System.out.println(String.format("stopWatch = %s ms",stopWatch.getLastTaskTimeMillis()));
        System.out.println("stopWatch = " + stopWatch.getLastTaskName());
    }
}

/**
 * SpringBoot内置tomcat启动原理  // https://www.cnblogs.com/sword-successful/p/11383723.html
 *
 *
 */
