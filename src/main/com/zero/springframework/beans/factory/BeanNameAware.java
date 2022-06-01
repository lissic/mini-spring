package com.zero.springframework.beans.factory;

/**
 * @author zero
 * @description BeanNameAware
 * @date 2022/6/1 14:25
 */
public interface BeanNameAware extends Aware{
    void setBeanName(String name);
}
