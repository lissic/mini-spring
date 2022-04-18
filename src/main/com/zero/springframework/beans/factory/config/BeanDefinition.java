package com.zero.springframework.beans.factory.config;

/**
 * @author zero
 * @description BeanDefinition
 * @date 2022/4/16 23:38
 */
public class BeanDefinition {
    private Class beanClass;

    public BeanDefinition(Class beanClass) {
        this.beanClass = beanClass;
    }

    public Class getBeanClass() {
        return beanClass;
    }
}
