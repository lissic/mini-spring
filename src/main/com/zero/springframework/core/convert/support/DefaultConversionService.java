package com.zero.springframework.core.convert.support;

import com.zero.springframework.core.convert.ConversionService;
import com.zero.springframework.core.convert.converter.ConverterRegistry;

/**
 * @author zero
 * @description DefaultConversionService
 * @date 2022/12/6 15:41
 */
public class DefaultConversionService extends GenericConversionService {
    public DefaultConversionService() {
        addDefaultConverters(this);
    }

    private void addDefaultConverters(ConverterRegistry converterRegistry) {
        // 添加各种类型转换工厂
        converterRegistry.addConverterFactory(new StringToNumberConverterFactory());
    }

}
