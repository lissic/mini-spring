package com.zero.springframework.context.event;

/**
 * @author zero
 * @description ContextCloseEvent 上下文关闭事件
 * @date 2022/6/2 14:08
 */
public class ContextCloseEvent extends ApplicationContextEvent{

    public ContextCloseEvent(Object source) {
        super(source);
    }
}
