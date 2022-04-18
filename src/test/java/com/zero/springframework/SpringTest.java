package com.zero.springframework;

import com.zero.springframework.bean.UserService;
import com.zero.springframework.beans.BeansException;
import com.zero.springframework.beans.factory.BeanFactory;
import com.zero.springframework.beans.factory.config.BeanDefinition;
import com.zero.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.junit.jupiter.api.Test;

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
}
