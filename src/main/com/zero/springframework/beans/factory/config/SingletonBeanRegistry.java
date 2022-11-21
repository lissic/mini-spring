package com.zero.springframework.beans.factory.config;

/**
 * @author zero
 * @description SingletonBeanRegistry 单例对象注册接口
 * @date 2022/4/18 11:50
 */
public interface SingletonBeanRegistry {
    // 获取单例对象
    Object getSingleton(String beanName);
    // 注册单例对象
    void registerSingleton(String beanName, Object singletonObject);
}
