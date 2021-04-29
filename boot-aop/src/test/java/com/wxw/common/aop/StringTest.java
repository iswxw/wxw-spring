package com.wxw.common.aop;

import cn.hutool.core.date.DatePattern;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

/**
 * @ Author ：wxw.
 * @ Date ： 15:07 2020/6/1
 * @ Description：测试String
 * @ Version:   $
 */
@Slf4j
@SpringBootTest
public class StringTest {

    @Test
    public void testData1(){
         String testStr = "123456 789,789";
         log.info("使用空格截取字符串：{}",testStr.split(" ")[1]);
    }

    @Test
    public void testData2(){
        long millis = System.currentTimeMillis();
        String format = DateFormatUtils.format(new Date(millis), DatePattern.NORM_DATETIME_PATTERN);
        System.out.println("millis = " + format);
    }
}
