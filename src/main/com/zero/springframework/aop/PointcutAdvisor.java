package com.zero.springframework.aop;

/**
 * @author zero
 * @description PointcutAdvisor
 * @date 2022/6/10 10:35
 */
public interface PointcutAdvisor extends Advisor{
    Pointcut getPointcut();
}
