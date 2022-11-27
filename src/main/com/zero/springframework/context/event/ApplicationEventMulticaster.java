package com.zero.springframework.context.event;

import com.zero.springframework.context.ApplicationEvent;
import com.zero.springframework.context.ApplicationListener;

/**
 * @author zero
 * @description ApplicationEventMulticaster 事件广播器接口
 * @date 2022/6/2 14:09
 */
public interface ApplicationEventMulticaster {

    // 添加监听器
    void addApplicationListener(ApplicationListener<?> listener);

    // 移除监听器
    void removeApplicationListener(ApplicationListener<?> listener);

    // 发布广播事件
    void multicastEvent(ApplicationEvent event);
}
