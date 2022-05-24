package com.zero.springframework.bean;

/**
 * @author zero
 * @description UserService
 * @date 2022/4/18 11:23
 */
public class UserService {
    private String name;

    public UserService() {
    }

    public UserService(String name) {
        this.name = name;
    }

    public void queryUserInfo() {
        System.out.println("查询用户信息: " + name);
    }
}
