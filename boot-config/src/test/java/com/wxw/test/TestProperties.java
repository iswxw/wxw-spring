package com.wxw.test;

import com.wxw.domain.OrderClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @Author: wxw
 * @create: 2020-03-07-17:22
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class TestProperties {

    @Resource
    private OrderClass orderClass;

    @Test
    public void test_1() {
        System.out.println("order = " + orderClass);
    }
}
