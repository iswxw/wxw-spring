package com.wxw.function.circularReference;

import com.wxw.manager.config.ScanConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 源码分析地址： https://www.jb51.net/article/168398.htm
 * @ Author ：wxw.
 * @ Date ： 11:10 2020/7/27
 * @ Description：循环依赖测试
 * @ Version:   v_0.0.1
 */
public class TestCircleReference {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(ScanConfig.class);

        /**
         * 有构造数据的循环依赖，无法解决的
         */
        // applicationContext.getBean(Teacher.class).teach();

        /**
         * 属性注入的方式
         */
        //applicationContext.getBean(Teacher01.class).teach();

        /**
         * 从缓存池获取bean
         */
        Object teacher01 = applicationContext.getBean("Teacher01");
        System.out.println("teacher01 = " + teacher01);
    }
}

/**
 *  源码分析地址： https://www.jb51.net/article/168398.htm
 *  什么是循环依赖？
 *  （1）无法解决的构造器中有属性的循环依赖
 *  （2）可以解决的属性注入循环依赖
 *
 * 循环引用方式有两种：
 *  (1) 相互引用
 * （2） 引用自身
 */
