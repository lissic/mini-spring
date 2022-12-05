package com.zero.springframework.beans.factory;

import com.zero.springframework.beans.BeansException;

/**
 * @author zero
 * @description ObjectFactory
 * @date 2022/12/5 15:50
 */
public interface ObjectFactory<T> {
    T getObject() throws BeansException;
}
