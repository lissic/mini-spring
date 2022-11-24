package com.zero.springframework.beans.factory.config;

import com.zero.springframework.beans.BeansException;

/**
 * @author zero
 * @description BeanPostProcessor Bean对象初始化之前或之后扩展接口
 * @date 2022/5/26 15:15
 */
public interface BeanPostProcessor {

    /**
     * 初始化bean对象之前执行此方法
     * @param bean bean
     * @param beanName beanName
     * @return Object
     * @throws BeansException
     */
    Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException;

    /**
     * 初始化bean对象之后执行方法
     * @param bean bean
     * @param beanName beanName
     * @return Object
     * @throws BeansException
     */
    Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException;
}
