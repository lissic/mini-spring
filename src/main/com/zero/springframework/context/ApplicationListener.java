package com.zero.springframework.context;

import java.util.EventListener;

/**
 * @author zero
 * @description ApplicationListener
 * @date 2022/6/2 14:10
 */
public interface ApplicationListener<E extends ApplicationEvent> extends EventListener {
    void onApplicationEvent(E event);
}
