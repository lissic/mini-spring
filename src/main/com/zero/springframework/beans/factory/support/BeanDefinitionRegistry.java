package com.zero.springframework.beans.factory.support;

import com.zero.springframework.beans.BeansException;
import com.zero.springframework.beans.factory.config.BeanDefinition;

/**
 * @author zero
 * @description BeanDefinitionRegistry
 * @date 2022/4/18 11:52
 */
public interface BeanDefinitionRegistry {

    /**
     * 注册beanDefinition的信息
     * @param beanName
     * @param beanDefinition
     */
    void registerBeanDefinition(String beanName, BeanDefinition beanDefinition);

    /**
     * 判断是否包含指定beanName的BeanDefinition
     * @param beanName
     * @return
     */
    boolean containsBeanDefinition(String beanName);

    /**
     * 根据beanName获取BeanDefinition信息
     * @param beanName
     * @return
     */
    BeanDefinition getBeanDefinition(String beanName) throws BeansException;

    /**
     * 获取所有注册BeanDefinition的bean名称
     * @return
     */
    String[] getBeanDefinitionNames();
}
