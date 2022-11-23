package com.zero.springframework.beans;

/**
 * @author zero
 * @description PropertyValue 对象属性
 * @date 2022/5/25 14:37
 */
public class PropertyValue {
    private final String name;
    private final Object value;

    public PropertyValue(String name, Object value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public Object getValue() {
        return value;
    }
}
