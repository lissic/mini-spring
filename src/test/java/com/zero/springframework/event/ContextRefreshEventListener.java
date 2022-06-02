package com.zero.springframework.event;

import com.zero.springframework.context.ApplicationListener;
import com.zero.springframework.context.event.ContextRefreshedEvent;

/**
 * @author zero
 * @description ContextRefreshEventListener
 * @date 2022/6/2 15:04
 */
public class ContextRefreshEventListener implements ApplicationListener<ContextRefreshedEvent> {
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        System.out.println("刷新应用事件执行");
    }
}
