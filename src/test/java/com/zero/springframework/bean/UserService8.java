package com.zero.springframework.bean;

import com.zero.springframework.beans.factory.annotation.Autowired;
import com.zero.springframework.beans.factory.annotation.Value;
import com.zero.springframework.stereotype.Component;

import java.util.Random;

/**
 * @author zero
 * @description UserService8
 * @date 2022/11/29 13:45
 */
@Component("userService")
public class UserService8 implements IUserService{
    @Value("${token}")
    private String token;
    @Autowired
    private UserDao userDao;
    @Override
    public String queryUserInfo() {
        try {
            Thread.sleep(new Random(1).nextInt(100));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return userDao.queryUserName("1001") + "," + token;
    }

    @Override
    public String register(String userName) {
        try {
            Thread.sleep(new Random(1).nextInt(100));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "注册用户" + userName + " success!";
    }

    @Override
    public String toString() {
        return "UserService8#token = {" + token + "}";
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
}
