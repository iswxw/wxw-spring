package com.wxw.source.bean.init_bean;

import com.wxw.wxw.service.GoodsService;
import com.wxw.wxw.service.UserService;
import org.junit.Test;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.config.RuntimeBeanReference;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * @author: com.wxw.wxw
 * @date: 2020-10-05-23:06
 */
public class BeanLoadTest {

    // 创建bean
    @Test
    public void createBeanTest() {
        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
        RootBeanDefinition def = new RootBeanDefinition(UserService.class);// 添加 BeanDefinition
        // 新增代码：填充属性 goodsService
        def.setPropertyValues(new MutablePropertyValues().add("goodsService", new RuntimeBeanReference(GoodsService.class)));
        factory.registerBeanDefinition("userService", def); // 为工厂类注册 BeanDefinition
        UserService bean = factory.getBean(UserService.class);
        System.out.println(bean);
    }


    // 这是一段正确的代码 循环依赖产生代码
    @Test
    public void test() {
        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
        {
            RootBeanDefinition userServiceDef = new RootBeanDefinition(UserService.class);
            userServiceDef.setPropertyValues(new MutablePropertyValues()
                    .add("goodsService", new RuntimeBeanReference("goodsService")));
            factory.registerBeanDefinition("userService", userServiceDef);
        }
        {
            RootBeanDefinition goodsServiceDef = new RootBeanDefinition(); // 为商品类GoodsService补充定义
            goodsServiceDef.setBeanClass(GoodsService.class);
            goodsServiceDef.setPropertyValues(new MutablePropertyValues()
                    .add("userService", new RuntimeBeanReference("userService")));
            factory.registerBeanDefinition("goodsService", goodsServiceDef);
        }
        UserService bean = factory.getBean(UserService.class);
        System.out.println("bean = " + bean);
    }
}
