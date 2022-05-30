package com.zero.springframework;

import com.zero.springframework.bean.UserDao;
import com.zero.springframework.bean.UserService;
import com.zero.springframework.bean.UserService2;
import com.zero.springframework.bean.UserService4;
import com.zero.springframework.beans.BeansException;
import com.zero.springframework.beans.PropertyValue;
import com.zero.springframework.beans.PropertyValues;
import com.zero.springframework.beans.factory.config.BeanDefinition;
import com.zero.springframework.beans.factory.config.BeanReference;
import com.zero.springframework.beans.factory.support.DefaultListableBeanFactory;
import com.zero.springframework.context.support.ClassPathXmlApplicationContext;
import org.junit.Test;

import java.lang.reflect.Constructor;

/**
 * @author zero
 * @description SpringTest
 * @date 2022/4/18 11:23
 */
public class InitAndDestroyTest {

    @Test
    public void test_xml() throws BeansException {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:initAndDestroy.xml");
        applicationContext.registerShutdownHook();

        UserService4 userService = applicationContext.getBean("userService", UserService4.class);
        userService.queryUserInfo();
    }

}
