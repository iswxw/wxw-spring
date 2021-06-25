package com.wxw.source.boot_start;

import com.wxw.manager.config.AppConfig;
import com.wxw.service.StartService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author weixiaowei
 * @desc: 启动应用 https://mp.weixin.qq.com/s/q6zs7xRjpcB4YxLw6w477w
 * @date: 2021/6/25
 */
public class BootStartApp {
    public static void main(String[] args) {
        //        ClassPathXmlApplicationContext xmlApplicationContext = new ClassPathXmlApplicationContext("bean.xml");
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        StartService startService = applicationContext.getBean(StartService.class);
        System.out.println("startService = " + startService);
        applicationContext.close();
    }
}

