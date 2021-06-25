package com.wxw.source.bean.post_processor;

import com.wxw.domain.HelloWorld;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author ：com.wxw.wxw.
 * @date ： 12:15 2020/11/26
 * @description：后置处理器测试实现类  https://www.w3cschool.cn/wkspring/xs181ici.html
 * @version: v_0.0.1
 */
public class PostProcessorTest {
    public static void main(String[] args) {
        AbstractApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        HelloWorld obj = (HelloWorld) context.getBean("helloWorld");
        obj.getMessage();
        // 将确保正常关闭，并且调用相关的 ​destroy​ 方法
        context.registerShutdownHook();
    }
}
