package com.zero.springframework.aop;


/**
 * @author zero
 * @description Pointcut
 * @date 2022/6/8 14:35
 */
public interface Pointcut {
    ClassFilter getClassFilter();

    MethodMatcher getMethodMatcher();
}
