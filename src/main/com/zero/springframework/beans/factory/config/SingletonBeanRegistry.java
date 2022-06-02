package com.zero.springframework.beans.factory.config;

/**
 * @author zero
 * @description SingletonBeanRegistry
 * @date 2022/4/18 11:50
 */
public interface SingletonBeanRegistry {
    Object getSingleton(String beanName);
    void registerSingleton(String beanName, Object singletonObject);
}
