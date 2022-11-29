package com.zero.springframework.autowired;

import com.zero.springframework.bean.IUserService;
import com.zero.springframework.beans.BeansException;
import com.zero.springframework.context.support.ClassPathXmlApplicationContext;
import org.junit.Test;

/**
 * @author zero
 * @description AutowiredTest
 * @date 2022/11/29 13:43
 */
public class AutowiredTest {
    @Test
    public void test_scan() throws BeansException {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:scan.xml");
        IUserService userService = applicationContext.getBean("userService", IUserService.class);
        System.out.println("测试结果" + userService.queryUserInfo());
    }
}
