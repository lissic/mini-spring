package com.zero.springframework;

import com.zero.springframework.bean.Husband;
import com.zero.springframework.bean.Wife;
import com.zero.springframework.beans.BeansException;
import com.zero.springframework.context.support.ClassPathXmlApplicationContext;
import org.junit.Test;

/**
 * @author zero
 * @description CircleTest
 * @date 2022/12/5 16:32
 */
public class CircleTest {
    @Test
    public void test_circle() throws BeansException {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:circle.xml");
        Husband husband = applicationContext.getBean("husband", Husband.class);
        Wife wife = applicationContext.getBean("wife", Wife.class);
        System.out.println("老公的媳妇：" + husband.queryWife());
        System.out.println("媳妇的老公：" + wife.queryHusband());
    }
}
