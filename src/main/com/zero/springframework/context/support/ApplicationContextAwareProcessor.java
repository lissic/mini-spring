package com.zero.springframework.context.support;

import com.zero.springframework.beans.BeansException;
import com.zero.springframework.beans.factory.config.BeanPostProcessor;
import com.zero.springframework.context.ApplicationContext;
import com.zero.springframework.context.ApplicationContextAware;

/**
 * @author zero
 * @description ApplicationContextAwareProcessor
 * @date 2022/6/1 14:26
 */
public class ApplicationContextAwareProcessor implements BeanPostProcessor {
    private final ApplicationContext applicationContext;

    public ApplicationContextAwareProcessor(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if(bean instanceof ApplicationContextAware) {
            ((ApplicationContextAware) bean).setApplicationContext(applicationContext);
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }
}
