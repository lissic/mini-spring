package com.zero.springframework;

import com.zero.springframework.bean.UserService4;
import com.zero.springframework.bean.UserService5;
import com.zero.springframework.beans.BeansException;
import com.zero.springframework.context.support.ClassPathXmlApplicationContext;
import org.junit.Test;

/**
 * @author zero
 * @description SpringTest
 * @date 2022/4/18 11:23
 */
public class AwareTest {

    @Test
    public void test_xml() throws BeansException {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:aware.xml");
        applicationContext.registerShutdownHook();

        UserService5 userService = applicationContext.getBean("userService", UserService5.class);
        userService.queryUserInfo();
        System.out.println(userService.getApplicationContext());
        System.out.println(userService.getBeanFactory());
    }

}
