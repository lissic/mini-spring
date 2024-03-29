package com.zero.springframework.beans.factory;

import com.zero.springframework.beans.BeansException;

/**
 * @author zero
 * @description BeanFactory
 * @date 2022/4/16 23:37
 */
public interface BeanFactory {

    /**
     * 获取无参构造方法的对象
     * @param name 实例名称
     * @return object 实例对象
     * @throws BeansException
     */
    Object getBean(String name) throws BeansException;

    /**
     * 通过此方法可以获取带参构造方法的对象
     * @param name 实例名称
     * @param args 构造方法参数
     * @return objects 实例对象
     * @throws BeansException 异常
     */
    Object getBean(String name, Object... args) throws BeansException;

    /**
     * 返回指定泛型的对象
     * @param name bean的名称
     * @param requiredType 类型
     * @param <T> 泛型
     * @return 实例化的bean对象
     * @throws BeansException 异常
     */
    <T> T getBean(String name, Class<T> requiredType) throws BeansException;

    /**
     * 返回指定泛型的对象
     * @param requiredType 类型
     * @param <T> 泛型
     * @return 实例化的bean对象
     * @throws BeansException 异常
     */
    <T> T getBean(Class<T> requiredType) throws BeansException;
}
