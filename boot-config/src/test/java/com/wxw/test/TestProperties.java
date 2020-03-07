package com.wxw.test;

import com.wxw.bean.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Author: wxw
 * @create: 2020-03-07-17:22
 */
@SpringBootTest
public class TestProperties {

    @Autowired
    private Order order;

    @Test
    public void TestOrderOne(){
        System.out.println("order = " + order);
    }
}
