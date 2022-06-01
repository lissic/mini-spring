package com.zero.springframework.bean;

import com.zero.springframework.beans.BeansException;
import com.zero.springframework.beans.factory.*;
import com.zero.springframework.context.ApplicationContext;
import com.zero.springframework.context.ApplicationContextAware;

/**
 * @author zero
 * @description UserService
 * @date 2022/4/18 11:23
 */
public class UserService5 implements BeanNameAware, BeanClassLoaderAware, ApplicationContextAware, BeanFactoryAware {
    private String userId;
    private String company;
    private String location;
    private UserDao2 userDao;

    private ApplicationContext applicationContext;
    private BeanFactory beanFactory;

    public void queryUserInfo() {
        System.out.println("查询用户信息: " + userDao.queryUserName(userId) + " 公司为：" + company + " 地址为：" + location);
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public UserDao2 getUserDao2() {
        return userDao;
    }

    public void setUserDao2(UserDao2 userDao) {
        this.userDao = userDao;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }


    public ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public BeanFactory getBeanFactory() {
        return beanFactory;
    }

    @Override
    public void setBeanClassLoader(ClassLoader classLoader) {
        System.out.println("ClassLoader: " + classLoader);
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }

    @Override
    public void setBeanName(String name) {
        System.out.println("bean name is: " + name);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
