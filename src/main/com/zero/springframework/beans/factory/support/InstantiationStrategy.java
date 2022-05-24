package com.zero.springframework.beans.factory.support;

import com.zero.springframework.beans.BeansException;
import com.zero.springframework.beans.factory.config.BeanDefinition;

import java.lang.reflect.Constructor;

/**
 * @author zero
 * @description InstantiationStrategy 实例化对象策略
 * @date 2022/5/24 15:46
 */
public interface InstantiationStrategy {
    Object instantiate(BeanDefinition beanDefinition, String BeanName, Constructor ctor, Object[] args) throws BeansException;
}
