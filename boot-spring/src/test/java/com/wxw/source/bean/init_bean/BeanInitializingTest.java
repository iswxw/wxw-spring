package com.wxw.source.bean.init_bean;

import com.wxw.wxw.domain.MessageList;
import org.junit.Test;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * Bean初始化测试
 *
 * @author: com.wxw.wxw
 * @date: 2020-10-06-0:22
 */
public class BeanInitializingTest {

    @Test
    public void messageListTest() {
        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
        factory.addBeanPostProcessor(new BeanPostProcessor() {
            @Override
            public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
                if (bean instanceof MessageList) {
                    ((MessageList) bean).addMessage("postProcessBeforeInitialization");
                }
                return bean;
            }

            @Override
            public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
                if (bean instanceof MessageList) {
                    ((MessageList) bean).addMessage("postProcessAfterInitialization");
                }
                return bean;
            }
        });
        RootBeanDefinition def = new RootBeanDefinition(MessageList.class);
        factory.registerBeanDefinition("messageList", def);
        MessageList bean = factory.getBean(MessageList.class);
        bean.printAllMessages();
    }
}
