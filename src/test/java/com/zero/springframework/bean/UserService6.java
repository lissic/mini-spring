package com.zero.springframework.bean;

/**
 * @author zero
 * @description UserService
 * @date 2022/4/18 11:23
 */
public class UserService6 {
    private String userId;
    private String company;
    private String location;
    private IUserDao userDao;

    public void queryUserInfo() {
        System.out.println("查询用户信息: " + userDao.queryUserName(userId) + " 公司为：" + company + " 地址为：" + location);
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public IUserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(IUserDao userDao) {
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
}
