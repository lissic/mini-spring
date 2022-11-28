package com.zero.springframework.context.annotation;

import cn.hutool.core.util.ClassUtil;
import com.zero.springframework.beans.factory.config.BeanDefinition;
import com.zero.springframework.stereotype.Component;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * @author zero
 * @description ClassPathScanningCandidateComponentProvider 类路径下对象扫描处理
 * @date 2022/11/28 16:45
 */
public class ClassPathScanningCandidateComponentProvider {
    public Set<BeanDefinition> findCandidateComponents(String basePackages) {
        Set<BeanDefinition> candidates = new LinkedHashSet<>();
        Set<Class<?>> classes = ClassUtil.scanPackageByAnnotation(basePackages, Component.class);
        for (Class<?> clazz : classes) {
            candidates.add(new BeanDefinition(clazz));
        }
        return candidates;
    }
}
