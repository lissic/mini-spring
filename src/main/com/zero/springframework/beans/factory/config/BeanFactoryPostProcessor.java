package com.zero.springframework.beans.factory.config;

import com.zero.springframework.beans.BeansException;
import com.zero.springframework.beans.factory.ConfigurableListableBeanFactory;

/**
 * @author zero
 * @description BeanFactoryPostProcessor Bean实例化之前扩展Bean定义信息接口
 * @date 2022/5/26 15:13
 */
public interface BeanFactoryPostProcessor {
    /**
     * 所有beanDefinitions加载完成后，对象实例化之前 提供修改bean定义信息的修改接口
     * @param beanFactory
     * @throws BeansException
     */
    void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException;
}
