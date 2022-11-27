package com.zero.springframework.aop;

import java.lang.reflect.Method;

/**
 * @author zero
 * @description MethodMatcher 方法匹配器，用户找到表达式范围内匹配的目标类和方法
 * @date 2022/6/8 14:39
 */
public interface MethodMatcher {
    boolean matches(Method method, Class<?> targetClass);
}
