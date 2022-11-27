package com.zero.springframework.aop;


/**
 * @author zero
 * @description Pointcut 切点接口，通过切点表达式获取类和方法信息
 * @date 2022/6/8 14:35
 */
public interface Pointcut {
    ClassFilter getClassFilter();

    MethodMatcher getMethodMatcher();
}
