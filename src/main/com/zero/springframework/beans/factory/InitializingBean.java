package com.zero.springframework.beans.factory;

/**
 * @author zero
 * @description InitializingBean 初始化对象接口
 * @date 2022/5/30 15:04
 */
public interface InitializingBean {

    /**
     * 在Bean对象属性填充完成后调用
     * @throws Exception
     */
    void afterPropertiesSet() throws Exception;
}
