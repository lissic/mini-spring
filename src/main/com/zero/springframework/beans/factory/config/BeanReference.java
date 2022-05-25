package com.zero.springframework.beans.factory.config;

/**
 * @author zero
 * @description BeanReference
 * @date 2022/5/25 14:52
 */
public class BeanReference {
    private final String beanName;

    public BeanReference(String beanName) {
        this.beanName = beanName;
    }

    public String getBeanName() {
        return beanName;
    }
}
