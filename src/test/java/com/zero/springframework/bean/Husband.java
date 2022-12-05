package com.zero.springframework.bean;

/**
 * @author zero
 * @description Husband
 * @date 2022/12/5 16:24
 */
public class Husband {
    private Wife wife;
    public String queryWife() {
        return "Husband.wife";
    }

    public Wife getWife() {
        return wife;
    }

    public void setWife(Wife wife) {
        this.wife = wife;
    }
}

