package com.zero.springframework.beans.factory;

import com.zero.springframework.beans.BeansException;

/**
 * @author zero
 * @description BeanFactoryAware
 * @date 2022/6/1 14:23
 */
public interface BeanFactoryAware extends Aware{
    void setBeanFactory(BeanFactory beanFactory) throws BeansException;
}
