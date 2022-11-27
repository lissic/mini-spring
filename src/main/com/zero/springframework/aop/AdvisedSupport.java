package com.zero.springframework.aop;


import org.aopalliance.intercept.MethodInterceptor;

/**
 * @author zero
 * @description AdvisedSupport 将代理、拦截、匹配的各个属性包装到一个类中，方便在Proxy实现类中使用
 * @date 2022/6/8 14:46
 */
public class AdvisedSupport {
    private boolean  proxyTargetClass = false;
    private TargetSource targetSource;
    private MethodInterceptor methodInterceptor;
    private MethodMatcher methodMatcher;

    public boolean isProxyTargetClass(){
        return proxyTargetClass;
    }

    public void setProxyTargetClass(boolean proxyTargetClass) {
        this.proxyTargetClass = proxyTargetClass;
    }

    public TargetSource getTargetSource() {
        return targetSource;
    }

    public void setTargetSource(TargetSource targetSource) {
        this.targetSource = targetSource;
    }

    public MethodInterceptor getMethodInterceptor() {
        return methodInterceptor;
    }

    public void setMethodInterceptor(MethodInterceptor methodInterceptor) {
        this.methodInterceptor = methodInterceptor;
    }

    public MethodMatcher getMethodMatcher() {
        return methodMatcher;
    }

    public void setMethodMatcher(MethodMatcher methodMatcher) {
        this.methodMatcher = methodMatcher;
    }
}
