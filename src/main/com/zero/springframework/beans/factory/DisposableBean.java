package com.zero.springframework.beans.factory;

/**
 * @author zero
 * @description DisposableBean
 * @date 2022/5/30 15:05
 */
public interface DisposableBean {
    void destroy() throws Exception;
}
