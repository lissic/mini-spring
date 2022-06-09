package com.zero.springframework.bean;

/**
 * @author zero
 * @description IUserService
 * @date 2022/6/9 13:49
 */
public interface IUserService {
    String queryUserInfo();
    String register(String userName);
}
