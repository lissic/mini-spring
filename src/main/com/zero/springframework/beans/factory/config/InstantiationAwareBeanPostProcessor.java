package com.zero.springframework.beans.factory.config;

import com.zero.springframework.beans.BeansException;
import com.zero.springframework.beans.PropertyValues;

/**
 * @author zero
 * @description InstantiationAwareBeanPostProcessor
 * @date 2022/6/10 10:47
 */
public interface InstantiationAwareBeanPostProcessor extends BeanPostProcessor{
    Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException;
    boolean postProcessAfterInstantiation(Object bean, String beanName);
    PropertyValues postProcessPropertyValues(PropertyValues pvs, Object bean, String beanName) throws BeansException;
    default Object getEarlyBeanReference(Object bean, String beanName){
        return bean;
    }
}
