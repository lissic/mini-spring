package com.zero.springframework.beans.factory;

/**
 * @author zero
 * @description BeanClassLoaderAware
 * @date 2022/6/1 14:24
 */
public interface BeanClassLoaderAware extends Aware{
    void setBeanClassLoader(ClassLoader classLoader);
}
