package com.zero.springframework.core.io;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author zero
 * @description Resource 资源接口
 * @date 2022/5/25 16:13
 */
public interface Resource {
    InputStream getInputStream() throws IOException;
}
