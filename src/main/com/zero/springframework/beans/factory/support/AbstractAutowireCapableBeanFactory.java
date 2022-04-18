package com.zero.springframework.beans.factory.support;

import com.zero.springframework.beans.BeansException;
import com.zero.springframework.beans.factory.config.BeanDefinition;

/**
 * @author zero
 * @description AbstractAutowireCapableBeanFactory
 * @date 2022/4/18 11:52
 */
public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory {

    @Override
    protected Object createBean(String beanName, BeanDefinition beanDefinition) throws BeansException {
        Object bean = null;
        try {
            bean = beanDefinition.getBeanClass().newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new BeansException("Instantiation of bean failed", e);
        }
        addSingleton(beanName, bean);
        return bean;
    }
}
