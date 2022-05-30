package com.zero.springframework.beans.factory;

/**
 * @author zero
 * @description InitializingBean
 * @date 2022/5/30 15:04
 */
public interface InitializingBean {

    void afterPropertiesSet() throws Exception;
}
