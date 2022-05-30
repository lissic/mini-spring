package com.zero.springframework.bean;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zero
 * @description UserDao
 * @date 2022/5/25 14:57
 */
public class UserDao2 {
    private static Map<String, String> hashMap = new HashMap<>();
    public void initDataMethod() {
        System.out.println("执行UserDao初始化方法");
        hashMap.put("1001","zero");
        hashMap.put("1002","alice");
        hashMap.put("1003","hayley");
    }

    public void destroyDataMethod() {
        System.out.println("执行UserDao销毁方法");
        hashMap.clear();
    }

    public String queryUserName(String userId) {
        return hashMap.get(userId);
    }
}
