package com.zero.springframework.beans.factory;

import com.zero.springframework.beans.BeansException;
import com.zero.springframework.beans.factory.config.BeanDefinition;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author zero
 * @description BeanFactory
 * @date 2022/4/16 23:37
 */
public interface BeanFactory {
    Object getBean(String name) throws BeansException;
}
