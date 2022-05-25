package com.zero.springframework.bean;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zero
 * @description UserDao
 * @date 2022/5/25 14:57
 */
public class UserDao {
    private static Map<String, String> hashMap = new HashMap<>();
    static {
        hashMap.put("1001","zero");
        hashMap.put("1002","alice");
        hashMap.put("1003","hayley");
    }

    public String queryUserName(String userId) {
        return hashMap.get(userId);
    }
}
