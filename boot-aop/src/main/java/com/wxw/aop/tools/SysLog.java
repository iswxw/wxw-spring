package com.wxw.aop.tools;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SysLog {

     long value() default 0;

    String message() default "";

}