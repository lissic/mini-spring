package com.zero.springframework.bean;

import com.zero.springframework.beans.factory.DisposableBean;
import com.zero.springframework.beans.factory.InitializingBean;

/**
 * @author zero
 * @description UserService
 * @date 2022/4/18 11:23
 */
public class UserService4 implements InitializingBean, DisposableBean {
    private String userId;
    private String company;
    private String location;
    private UserDao2 userDao;

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

    @Override
    public void destroy() throws Exception {
        System.out.println("执行UserService的销毁方法");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("执行UserService的afterPropertiesSet方法");
    }
}
