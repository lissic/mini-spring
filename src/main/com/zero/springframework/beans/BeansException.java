package com.zero.springframework.beans;

/**
 * @author zero
 * @description BeansException
 * @date 2022/4/18 11:51
 */
public class BeansException extends Throwable {
    private String errMsg;

    public BeansException(String errMsg) {
        this.errMsg = errMsg;
    }

    public BeansException(String errMsg, Throwable cause) {
        this.errMsg = errMsg;
    }
}
