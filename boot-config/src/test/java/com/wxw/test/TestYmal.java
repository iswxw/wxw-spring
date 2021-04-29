package com.wxw.test;

import com.wxw.domain.Person;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

/**
 * @Author: wxw
 * @create: 2020-03-07-14:49
 * SpringBoot单元测试;
 * 可以在测试期间很方便的类似编码一样进行自动注入等容器的功能
 */

@SpringBootTest
public class TestYmal {

    @Autowired
    private Person person;

    @Autowired
    private ApplicationContext Ioc;

    @Test
    public void testYmlOne(){
        System.out.println("person = " + person);
    }

    /**
     * 测试注解形式注入bean
     */
    @Test
    public void testPersonService(){
        boolean personService = Ioc.containsBean("personService02   ");
        System.out.println("personService = " + personService);
    }

    @Test
    public void testData1(){
        System.out.println("person = " + person);
    }

}
