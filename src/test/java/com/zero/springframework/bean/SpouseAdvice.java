package com.zero.springframework.bean;

import com.zero.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

/**
 * @author zero
 * @description SpouseAdvice
 * @date 2022/12/5 16:30
 */
public class SpouseAdvice implements MethodBeforeAdvice {
    @Override
    public void before(Method method, Object[] args, Object target) throws Throwable {
        System.out.println("关怀小两口（切面）：" + method);
    }
}
