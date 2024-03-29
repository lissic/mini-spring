package com.zero.springframework.aop.framework.autoproxy;

import com.zero.springframework.aop.*;
import com.zero.springframework.aop.aspectj.AspectJExpressionPointcutAdvisor;
import com.zero.springframework.aop.framework.ProxyFactory;
import com.zero.springframework.beans.BeansException;
import com.zero.springframework.beans.PropertyValues;
import com.zero.springframework.beans.factory.BeanFactory;
import com.zero.springframework.beans.factory.BeanFactoryAware;
import com.zero.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import com.zero.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.aopalliance.aop.Advice;
import org.aopalliance.intercept.MethodInterceptor;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * @author zero
 * @description DefaultAdvisorAutoProxyCreator
 * @date 2022/6/10 10:45
 */
public class DefaultAdvisorAutoProxyCreator implements InstantiationAwareBeanPostProcessor, BeanFactoryAware {
    private DefaultListableBeanFactory beanFactory;
    private final Set<Object> earlyProxyReferences = Collections.synchronizedSet(new HashSet<>());
    @Override
    public void setBeanFactory(BeanFactory beanFactory) {
        this.beanFactory = (DefaultListableBeanFactory) beanFactory;
    }

    @Override
    public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
        return null;
    }

    @Override
    public boolean postProcessAfterInstantiation(Object bean, String beanName) {
        return true;
    }

    @Override
    public PropertyValues postProcessPropertyValues(PropertyValues pvs, Object bean, String beanName) throws BeansException {
        return pvs;
    }


    private boolean isInfrastructureClass(Class<?> beanClass) {
        return Advice.class.isAssignableFrom(beanClass) || Pointcut.class.isAssignableFrom(beanClass) || Advisor.class.isAssignableFrom(beanClass);
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) {
        return  null;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if(!earlyProxyReferences.contains(beanName)) {
            return wapIfNecessary(bean, beanName);
        }
        return bean;
    }

    protected Object wapIfNecessary(Object bean, String beanName) throws BeansException {
        if (isInfrastructureClass(bean.getClass())) return bean;

        Collection<AspectJExpressionPointcutAdvisor> advisors = beanFactory.getBeansOfType(AspectJExpressionPointcutAdvisor.class).values();

        for (AspectJExpressionPointcutAdvisor advisor : advisors) {
            ClassFilter classFilter = advisor.getPointcut().getClassFilter();
            if (!classFilter.matches(bean.getClass())) continue;

            AdvisedSupport advisedSupport = new AdvisedSupport();

            TargetSource targetSource = new TargetSource(bean);
            advisedSupport.setTargetSource(targetSource);
            advisedSupport.setMethodInterceptor((MethodInterceptor) advisor.getAdvice());
            advisedSupport.setMethodMatcher(advisor.getPointcut().getMethodMatcher());
            advisedSupport.setProxyTargetClass(true);

            return new ProxyFactory(advisedSupport).getProxy();

        }
        return bean;
    }

    @Override
    public Object getEarlyBeanReference(Object bean, String beanName) throws BeansException {
        earlyProxyReferences.add(beanName);
        return wapIfNecessary(bean, beanName);
    }
}
