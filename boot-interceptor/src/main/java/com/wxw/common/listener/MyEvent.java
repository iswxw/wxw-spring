package com.wxw.common.listener;

import org.springframework.context.ApplicationEvent;

/**
 *
 * 定义事件
 * 还需要定义一个监听器
 *
 */

public class MyEvent extends ApplicationEvent {

    private static final long serialVersionUID = 1L;

    public MyEvent(Object source) {
        //定义成Object类
        super(source);
    }
}