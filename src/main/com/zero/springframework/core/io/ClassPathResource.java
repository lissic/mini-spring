package com.zero.springframework.core.io;

import cn.hutool.core.lang.Assert;
import com.zero.springframework.util.ClassUtils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author zero
 * @description ClassPathResource 读取类加载路径下的文件
 * @date 2022/5/25 16:14
 */
public class ClassPathResource implements Resource{
    private final String path;
    private ClassLoader classLoader;

    public ClassPathResource(String path) {
        this(path, null);
    }

    public ClassPathResource(String path, ClassLoader classLoader) {
        Assert.notNull(path, "Path must not null");
        this.path = path;
        this.classLoader = (classLoader != null ? classLoader: ClassUtils.getDefaultClassLoader());
    }

    @Override
    public InputStream getInputStream() throws IOException {
        InputStream is = classLoader.getResourceAsStream(path);
        if (is == null) {
            throw new FileNotFoundException(this.path + " cannot be opened because it dose not exit");
        }
        return is;
    }
}
