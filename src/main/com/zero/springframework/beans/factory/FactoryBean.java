package com.zero.springframework.beans.factory;

/**
 * @author zero
 * @description FactoryBean
 * @date 2022/6/1 15:21
 */
public interface FactoryBean<T> {
    T getObject() throws Exception;
    Class<?> getObjectType();
    boolean isSingleton();
}
