package com.zero.springframework.beans.factory;

import com.zero.springframework.beans.BeansException;
import com.zero.springframework.beans.factory.config.AutowireCapableBeanFactory;
import com.zero.springframework.beans.factory.config.BeanDefinition;
import com.zero.springframework.beans.factory.config.BeanPostProcessor;
import com.zero.springframework.beans.factory.config.ConfigurableBeanFactory;

/**
 * @author zero
 * @description ConfigurableListableBeanFactory
 * @date 2022/5/26 15:25
 */
public interface ConfigurableListableBeanFactory extends ListableBeanFactory, AutowireCapableBeanFactory, ConfigurableBeanFactory {
    BeanDefinition getBeanDefinition(String beanName) throws BeansException;

    void preInstantiateSingletons() throws BeansException;

    void addBeanPostProcessor(BeanPostProcessor beanPostProcessor);
}
