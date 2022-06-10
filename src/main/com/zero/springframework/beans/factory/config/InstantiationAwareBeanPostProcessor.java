package com.zero.springframework.beans.factory.config;

import com.zero.springframework.beans.BeansException;

/**
 * @author zero
 * @description InstantiationAwareBeanPostProcessor
 * @date 2022/6/10 10:47
 */
public interface InstantiationAwareBeanPostProcessor extends BeanPostProcessor{
    Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException;
}
