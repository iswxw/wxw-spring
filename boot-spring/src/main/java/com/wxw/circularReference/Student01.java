package com.wxw.circularReference;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @ Author ：wxw.
 * @ Date ： 8:51 2020/7/27
 * @ Description：x
 * @ Version:   v_0.0.1
 */
@Component
public class Student01 {

    /**
     * A也注入B
     */
    @Resource
    private Teacher01 teacher;

    public Student01() {
        System.out.println("Student init1:" + teacher);
    }

    public void learn () {
        System.out.println("Student learn");
    }
}
