//package com.wxw.circularReference;
//
//import org.springframework.stereotype.Component;
//
//import javax.annotation.Resource;
//
///**
// * @ Author ：wxw.
// * @ Date ： 11:06 2020/7/27
// * @ Description：bean
// * @ Version:   v_0.0.1
// */
//@Component
//public class Teacher {
//
//    /**
//     * B也注入A
//     */
//    @Resource
//    private Student student;
//
//    public Teacher(Student student) {
//        System.out.println("Teacher init1:" + student);
//    }
//
//    public void teach () {
//        System.out.println("teach:");
//        student.learn();
//    }
//}
//
//
