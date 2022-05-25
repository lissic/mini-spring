package com.zero.springframework.core.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author zero
 * @description FileSystemResource 读取指定路径下的文件
 * @date 2022/5/25 16:21
 */
public class FileSystemResource implements Resource{
    private final File file;
    private final String path;

    public FileSystemResource(File file) {
        this.file = file;
        this.path = file.getPath();
    }

    public FileSystemResource(String path) {
        this.file = new File(path);
        this.path = path;
    }

    @Override
    public InputStream getInputStream() throws IOException {
        return new FileInputStream(this.file);
    }

    public String getPath() {
        return path;
    }
}
