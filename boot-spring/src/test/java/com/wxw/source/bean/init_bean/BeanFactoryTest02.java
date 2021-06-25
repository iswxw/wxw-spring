package com.wxw.source.bean.init_bean;

import org.junit.Test;
import org.springframework.beans.BeansException;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.RootBeanDefinition;


/**
 * 简化bean的创建过程
 * @Author: com.wxw.wxw
 * @create: 2020-10-05-16:28
 */
public class BeanFactoryTest02 {

    @Test
    public void createBeanTest(){
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        beanFactory.addBeanPostProcessor(new BeanPostProcessor() {
            @Override
            public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
                if (bean instanceof AService){
                    return new AService$proxy((AService) bean);
                }
                return bean;
            }
        });
        /**
         * 来源：https://www.bilibili.com/video/BV1dy4y1C7MP
         * 简化bean的创建过程
         *  1. 实例化bean:AbstractAutowireCapableBeanFactory#createBeanInstance()
         *  2. 填充属性:AbstractAutowireCapableBeanFactory#populateBean()
         *  3. 后置处理（proxy）:AbstractAutowireCapableBeanFactory#initializeBean()
         *  4. 添加至单例池：AbstractAutowireCapableBeanFactory#addSingleton()
         */
        RootBeanDefinition mbd = new RootBeanDefinition();
        mbd.setPropertyValues(new MutablePropertyValues().add("id", "8888"));
//        AService bean = (AService) beanFactory.doCreateBean("aService", mbd, null);
//        System.out.println("bean = " + bean);
//        // 添加至单例池(方法一)
//        beanFactory.addSingleton("aService", bean);
//        // 添加至单例池(方法二)
//        beanFactory.getSingleton("aService",()->bean);

    }

    @Test
    public void getBeanTest(){
        // 4.DefaultSingletonBeanRegistry
        // 3.AbstractBeanFactory
        // 2.AbstractAutowireCapableBeanFactory
        // 1.DefaultListableBeanFactory
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        RootBeanDefinition mbd = new RootBeanDefinition();
        mbd.setPropertyValues(new MutablePropertyValues().add("id", "8888"));
        beanFactory.registerBeanDefinition("service", mbd);
        beanFactory.getBean(AService.class);
    }

    // 测试bean
    public static class AService{
        private String id;

        public String getId() {
            return id;
        }
        public void setId(String id) {
            this.id = id;
        }
    }
    // 代理类
    public static class AService$proxy extends AService{
         AService target;
         public AService$proxy(AService target){
             this.target =target;
         }
    }
}
