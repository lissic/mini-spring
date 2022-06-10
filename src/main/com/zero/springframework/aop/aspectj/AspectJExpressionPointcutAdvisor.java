package com.zero.springframework.aop.aspectj;

import com.zero.springframework.aop.Pointcut;
import com.zero.springframework.aop.PointcutAdvisor;
import org.aopalliance.aop.Advice;

/**
 * @author zero
 * @description AspectJExpressionPointcutAdvisor
 * @date 2022/6/10 10:36
 */
public class AspectJExpressionPointcutAdvisor implements PointcutAdvisor {
    private AspectJExpressionPointcut pointcut;
    private Advice advice;
    private String expression;

    public void setExpression(String expression) {
        this.expression = expression;
    }

    public void setAdvice(Advice advice) {
        this.advice = advice;
    }

    @Override
    public Advice getAdvice() {
        return advice;
    }

    @Override
    public Pointcut getPointcut() {
        if (null == pointcut) {
            pointcut = new AspectJExpressionPointcut(expression);
        }
        return pointcut;
    }
}
