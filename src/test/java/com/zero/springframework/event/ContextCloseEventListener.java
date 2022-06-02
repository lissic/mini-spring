package com.zero.springframework.event;

import com.zero.springframework.context.ApplicationListener;
import com.zero.springframework.context.event.ContextCloseEvent;

/**
 * @author zero
 * @description ContextCloseEventListener
 * @date 2022/6/2 15:04
 */
public class ContextCloseEventListener implements ApplicationListener<ContextCloseEvent> {
    @Override
    public void onApplicationEvent(ContextCloseEvent event) {
        System.out.println("关闭应用事件");
    }
}
