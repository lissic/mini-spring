package com.zero.springframework;

import com.zero.springframework.bean.UserService3;
import com.zero.springframework.beans.BeansException;
import com.zero.springframework.beans.factory.support.DefaultListableBeanFactory;
import com.zero.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import com.zero.springframework.common.MyBeanFactoryPostProcessor;
import com.zero.springframework.common.MyBeanPostProcessor;
import com.zero.springframework.context.support.ClassPathXmlApplicationContext;
import org.junit.Test;

/**
 * @author zero
 * @description SpringTest
 * @date 2022/4/18 11:23
 */
public class ImproveTest {

    @Test
    public void test_beanFactoryProcessorAndBeanPostProcessor_without_context() throws BeansException {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        reader.loadBeanDefinitions("classpath:improvebean.xml");

        MyBeanFactoryPostProcessor factoryPostProcessor = new MyBeanFactoryPostProcessor();

        factoryPostProcessor.postProcessBeanFactory(beanFactory);

        MyBeanPostProcessor beanPostProcessor = new MyBeanPostProcessor();
        beanFactory.addBeanPostProcessor(beanPostProcessor);

        UserService3 userService = beanFactory.getBean("userService", UserService3.class);
        userService.queryUserInfo();
    }

    @Test
    public void test_bfppAndbpp_with_context() throws BeansException {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:improvebean.xml");
        UserService3 userService = applicationContext.getBean("userService", UserService3.class);
        userService.queryUserInfo();
    }
}
