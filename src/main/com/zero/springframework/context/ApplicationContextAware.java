package com.zero.springframework.context;

import com.zero.springframework.beans.BeansException;
import com.zero.springframework.beans.factory.Aware;

/**
 * @author zero
 * @description ApplicationContextAware
 * @date 2022/6/1 14:25
 */
public interface ApplicationContextAware extends Aware {
    void setApplicationContext(ApplicationContext applicationContext) throws BeansException;
}
