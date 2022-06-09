package com.zero.springframework.bean;

import java.util.Random;

/**
 * @author zero
 * @description UserService7
 * @date 2022/6/9 13:50
 */
public class UserService7 implements IUserService {
    @Override
    public String queryUserInfo() {
        try {
            Thread.sleep(new Random(1).nextInt(100));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "zero, 1111, xxx";
    }

    @Override
    public String register(String userName) {
        try {
            Thread.sleep(new Random(1).nextInt(100));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "注册用户" + userName + "成功！";
    }
}
