package com.zero.springframework.beans.factory.support;

import com.zero.springframework.beans.BeansException;
import com.zero.springframework.beans.factory.BeanFactory;
import com.zero.springframework.beans.factory.config.BeanDefinition;

/**
 * @author zero
 * @description AbstractBeanFactory
 * @date 2022/4/18 11:52
 */
public abstract class AbstractBeanFactory extends DefaultSingletonBeanRegistry implements BeanFactory {


    @Override
    public Object getBean(String name) throws BeansException {
        Object bean = getSingleton(name);
        if (bean != null) {
            return bean;
        }

        BeanDefinition beanDefinition = getBeanDefinition(name);
        return createBean(name, beanDefinition);
    }

    protected abstract BeanDefinition getBeanDefinition(String beanName) throws BeansException;

    protected abstract Object createBean(String beanName, BeanDefinition beanDefinition) throws BeansException;
}
