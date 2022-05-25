package com.zero.springframework;

import com.zero.springframework.bean.UserDao;
import com.zero.springframework.bean.UserService;
import com.zero.springframework.bean.UserService2;
import com.zero.springframework.beans.BeansException;
import com.zero.springframework.beans.PropertyValue;
import com.zero.springframework.beans.PropertyValues;
import com.zero.springframework.beans.factory.config.BeanDefinition;
import com.zero.springframework.beans.factory.config.BeanReference;
import com.zero.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * @author zero
 * @description SpringTest
 * @date 2022/4/18 11:23
 */
public class SpringTest {

    @Test
    public void test_BeanFactory() throws BeansException {
        // 1、初始化BeanFactory
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        // 2、注册Bean
        BeanDefinition beanDefinition = new BeanDefinition(UserService.class);
        beanFactory.registerBeanDefinition("userService", beanDefinition);

        // 3、第一次获取
        UserService userService = (UserService) beanFactory.getBean("userService");
        System.out.println(userService);
        // 4、第二次从单例池中获取
        UserService userService1 = (UserService) beanFactory.getBean("userService");
        System.out.println(userService1);
        userService1.queryUserInfo();
    }

    @Test
    public void test_BeanFactory2() throws BeansException {
        // 1、初始化BeanFactory
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        // 2、注入bean
        BeanDefinition beanDefinition = new BeanDefinition(UserService.class);
        beanFactory.registerBeanDefinition("userService", beanDefinition);

        // 3、获取bean
        UserService userService = (UserService) beanFactory.getBean("userService", "Zero");
        userService.queryUserInfo();
    }

    @Test
    public void test_newInstance() throws Exception {
        UserService userService = UserService.class.newInstance();
        System.out.println(userService);
    }

    @Test
    public void test_constructor() throws Exception {
        Class<UserService> userServiceClass = UserService.class;
        Constructor<UserService> cotr = userServiceClass.getDeclaredConstructor(String.class);
        UserService userService = cotr.newInstance("Zero");
        userService.queryUserInfo();
    }

    @Test
    public void test_BeanFactory_with_Reference() throws BeansException {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        beanFactory.registerBeanDefinition("userDao", new BeanDefinition(UserDao.class));
        PropertyValues pvs = new PropertyValues();
        pvs.addPropertyValue(new PropertyValue("userId", "1001"));
        pvs.addPropertyValue(new PropertyValue("userDao", new BeanReference("userDao")));
        beanFactory.registerBeanDefinition("userService",new BeanDefinition(UserService2.class, pvs));
        UserService2 userService = (UserService2)beanFactory.getBean("userService");
        userService.queryUserInfo();
    }
}
