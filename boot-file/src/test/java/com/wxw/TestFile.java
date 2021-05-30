package com.wxw;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.swing.*;

/**
 * @author weixiaowei
 * @desc:
 * @date: 2021/5/29
 */
@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class TestFile {

    @Test
    public void test_path() {
        String property = System.getProperty("user.dir");
        System.out.println("property = " + property);
    }
}
