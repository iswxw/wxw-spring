package com.wxw.manager.log;

import ch.qos.logback.core.ConsoleAppender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author ：wxw.
 * @date ： 16:59 2021/3/5
 * @description：
 * @link: https://tech.meituan.com/2018/01/15/satellite-system.html
 * @version: v_0.0.1
 */
public class DemoAppender extends ConsoleAppender<String> {


    private static final Logger logger = LoggerFactory.getLogger(DemoAppender.class);

    public static void main(String[] args) {
        DemoAppender demoAppender = new DemoAppender();
    }


}
