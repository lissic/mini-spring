package com.zero.springframework.common;

import com.zero.springframework.bean.UserService3;
import com.zero.springframework.beans.BeansException;
import com.zero.springframework.beans.factory.config.BeanPostProcessor;

/**
 * @author zero
 * @description MyBeanPostProcessor
 * @date 2022/5/26 16:46
 */
public class MyBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if("userService".equals(beanName)) {
            UserService3 userService3 = (UserService3) bean;
            userService3.setLocation("修改后的地址");
        }
        return null;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }
}
