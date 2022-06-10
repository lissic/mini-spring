package com.zero.springframework.aop;

import java.lang.reflect.Method;

/**
 * @author zero
 * @description MethodBeforeAdvice
 * @date 2022/6/10 10:34
 */
public interface MethodBeforeAdvice extends BeforeAdvice{
    void before(Method method, Object[] args, Object target) throws Throwable;
}
