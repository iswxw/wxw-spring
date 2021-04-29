package com.wxw.function.circularReference;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @ Author ：wxw.
 * @ Date ： 11:06 2020/7/27
 * @ Description：bean
 * @ Version:   v_0.0.1
 */
@Component
public class Teacher01 {

    /**
     * B也注入A
     */
    @Resource
    private Student01 student;

    public Teacher01() {
        System.out.println("Teacher init1:" + student);
    }

    public void teach () {
        System.out.println("teach:");
        student.learn();
    }
}


/**
 *  什么是循环依赖？
 *  （1）无法解决的构造器中有属性的循环依赖
 *  （2）可以解决的属性注入循环依赖
 *
 * 循环引用方式有两种：
 *  (1) 相互引用
 * （2） 引用自身
 */