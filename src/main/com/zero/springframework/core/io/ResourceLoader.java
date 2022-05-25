package com.zero.springframework.core.io;

/**
 * @author zero
 * @description ResourceLoader
 * @date 2022/5/25 16:27
 */
public interface ResourceLoader {
    // 类加载路径前缀
    String CLASSPATH_URL_PREFIX = "classpath:";
    Resource getResource(String location);
}
