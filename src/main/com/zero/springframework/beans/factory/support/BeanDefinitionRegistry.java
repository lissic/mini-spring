package com.zero.springframework.beans.factory.support;

import com.zero.springframework.beans.factory.config.BeanDefinition;

/**
 * @author zero
 * @description BeanDefinitionRegistry
 * @date 2022/4/18 11:52
 */
public interface BeanDefinitionRegistry {

    void registerBeanDefinition(String beanName, BeanDefinition beanDefinition);

    boolean containsBeanDefinition(String beanName);
}
