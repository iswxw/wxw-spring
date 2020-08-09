package com.wxw.common.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;

/**
 * @Author: wxw
 * @create: 2020-08-09-23:13
 */
public class Mylistener01 implements ApplicationListener<MyEvent> {

    public static final Logger logger = LoggerFactory.getLogger(Mylistener01.class);

    @Override
    public void onApplicationEvent(MyEvent event) {
        logger.info(String.format("%s监听到事件源：%s.", Mylistener01.class.getName(), event.getSource()));
    }

}

/**
 * @SpringBootApplication
 * public class InterceptorMain {
 *
 *     public static void main(String[] args) {
 *         SpringApplication.run(InterceptorMain.class,args);
 *
 *         ConfigurableApplicationContext context = SpringApplication.run(InterceptorMain.class, args);
 *         //装载监听
 *         context.addApplicationListener(new MyListener01());
 *     }
 * }
 */
