package com.zero.springframework.beans.factory;

/**
 * @author zero
 * @description DisposableBean 对象销毁接口
 * @date 2022/5/30 15:05
 */
public interface DisposableBean {
    /**
     * 销毁方法
     * @throws Exception
     */
    void destroy() throws Exception;
}
