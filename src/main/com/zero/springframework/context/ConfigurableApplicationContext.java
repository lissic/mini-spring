package com.zero.springframework.context;

import com.zero.springframework.beans.BeansException;

/**
 * @author zero
 * @description ConfigurableApplicationContext
 * @date 2022/5/26 15:18
 */
public interface ConfigurableApplicationContext extends ApplicationContext{
    /**
     * 刷新容器
     * @throws BeansException
     */
    void refresh() throws BeansException;

    void registerShutdownHook();

    void close();
}
