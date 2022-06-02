package com.zero.springframework;

import com.zero.springframework.bean.UserService5;
import com.zero.springframework.beans.BeansException;
import com.zero.springframework.context.support.ClassPathXmlApplicationContext;
import com.zero.springframework.event.CustomEvent;
import org.junit.Test;

/**
 * @author zero
 * @description SpringTest
 * @date 2022/4/18 11:23
 */
public class EventTest {

    @Test
    public void test_event() throws BeansException {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:event.xml");
        applicationContext.publishEvent(new CustomEvent(applicationContext, 12344556L, "成功了！！！"));
        applicationContext.registerShutdownHook();
    }

}
