package com.zero.springframework.event;

import com.zero.springframework.context.ApplicationListener;

import java.util.Date;

/**
 * @author zero
 * @description CustomEventListener
 * @date 2022/6/2 14:53
 */
public class CustomEventListener implements ApplicationListener<CustomEvent> {
    @Override
    public void onApplicationEvent(CustomEvent event) {
        System.out.println("收到：" + event.getSource() + "消息；时间：" + new Date());
        System.out.println("消息：" + event.getId() + ":" + event.getMessage());
    }
}
