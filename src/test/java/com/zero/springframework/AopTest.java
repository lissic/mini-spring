package com.zero.springframework;

import com.zero.springframework.aop.AdvisedSupport;
import com.zero.springframework.aop.TargetSource;
import com.zero.springframework.aop.aspectj.AspectJExpressionPointcut;
import com.zero.springframework.aop.framework.Cglib2AopProxy;
import com.zero.springframework.aop.framework.JdkDynamicAopProxy;
import com.zero.springframework.bean.IUserService;
import com.zero.springframework.bean.UserService7;
import com.zero.springframework.bean.UserServiceInterceptor;
import com.zero.springframework.beans.BeansException;
import org.junit.Test;

/**
 * @author zero
 * @description AopTest
 * @date 2022/6/9 13:54
 */
public class AopTest {
    @Test
    public void test_aop() throws BeansException {
        IUserService userService = new UserService7();

        AdvisedSupport advisedSupport = new AdvisedSupport();
        advisedSupport.setTargetSource(new TargetSource(userService));
        advisedSupport.setMethodInterceptor(new UserServiceInterceptor());
        advisedSupport.setMethodMatcher(new AspectJExpressionPointcut("execution(* com.zero.springframework.bean.IUserService.*(..))"));

        IUserService proxy = (IUserService) new JdkDynamicAopProxy(advisedSupport).getProxy();
        System.out.println(proxy.queryUserInfo());
        IUserService proxy1 = (IUserService) new Cglib2AopProxy(advisedSupport).getProxy();
        System.out.println(proxy1.register("ttttt"));
    }
}
