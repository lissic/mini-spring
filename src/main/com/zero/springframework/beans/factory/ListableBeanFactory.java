package com.zero.springframework.beans.factory;

import com.zero.springframework.beans.BeansException;
import com.zero.springframework.beans.factory.BeanFactory;

import java.util.Map;

/**
 * @author zero
 * @description ListableBeanFactory
 * @date 2022/5/26 15:28
 */
public interface ListableBeanFactory extends BeanFactory {
    /**
     * 按照类型返回bean实例
     * @param type
     * @param <T>
     * @return
     * @throws BeansException
     */
    <T>Map<String, T> getBeansOfType(Class<T> type) throws BeansException;

    /**
     * 获取所有注册bean的名称
     * @return
     */
    String[] getBeanDefinitionNames();
}
