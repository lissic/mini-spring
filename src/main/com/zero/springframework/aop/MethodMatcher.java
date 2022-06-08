package com.zero.springframework.aop;

import java.lang.reflect.Method;

/**
 * @author zero
 * @description MethodMatcher
 * @date 2022/6/8 14:39
 */
public interface MethodMatcher {
    boolean matches(Method method, Class<?> targetClass);
}
