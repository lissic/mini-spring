package com.zero.springframework;

import com.zero.springframework.bean.UserService5;
import com.zero.springframework.bean.UserService6;
import com.zero.springframework.beans.BeansException;
import com.zero.springframework.context.support.ClassPathXmlApplicationContext;
import org.junit.Test;

/**
 * @author zero
 * @description SpringTest
 * @date 2022/4/18 11:23
 */
public class FactoryBeanTest {

    @Test
    public void test_prototype() throws BeansException {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:factorybean.xml");
        applicationContext.registerShutdownHook();

        UserService6 userService = applicationContext.getBean("userService", UserService6.class);
        UserService6 userService2 = applicationContext.getBean("userService", UserService6.class);
        System.out.println(userService);
        System.out.println(userService2);
    }

    @Test
    public void test_factory_bean() throws BeansException {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:factorybean.xml");
        applicationContext.registerShutdownHook();

        UserService6 userService = applicationContext.getBean("userService", UserService6.class);
        userService.queryUserInfo();
    }

}
