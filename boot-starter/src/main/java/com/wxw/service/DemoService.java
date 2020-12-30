package com.wxw.service;

/**
 * @author: wxw
 * @date: 2020-12-23-22:02
 * @description:
 */
public class DemoService {

    public String sayWhat;
    public String toWho;

    public DemoService(String sayWhat, String toWho){
        this.sayWhat = sayWhat;
        this.toWho = toWho;
    }
    public String say(){
        return this.sayWhat + "!  " + toWho;
    }
}
