package com.wxw.Test;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Author: wxw
 * @create: 2020-03-08-23:24
 *  SpringBoot 日志测试
 */

@SpringBootTest
public class SringBootLogTest {

    // 记录器
    Logger LOGGER = LoggerFactory.getLogger(SpringBootTest.class);
    @Test
    public void contextLoad(){
        // System.out.println();
        /**
         * 日志级别由高到低
         * 日志级别：ERROR，WARN，INFO，DEBUG，或TRACE。
         * 可以调整输出的日志级别；日志就只会在这个级别以以后的高级别生效
         */
        LOGGER.trace("日志踪迹");
        LOGGER.debug("日志debug模式");
        //SpringBoot默认给我们使用的是info级别的，没有指定级别的就用SpringBoot默认规定的级别；root级别
        LOGGER.info("日志提示信息");
        LOGGER.warn("日志警告信息");
        LOGGER.error("日志提示错误信息");
    }


}
