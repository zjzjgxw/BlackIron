package com.gxw.store.project.common.utils.enumHandler;

import com.google.common.collect.Maps;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;

import java.util.Map;

/**
 *  转化器工厂
 */
public class IntegerToEnumConverterFactory implements ConverterFactory<Integer, BaseEnum> {
    private static final Map<Class, Converter> CONVERTERS = Maps.newHashMap();


    @Override
    public <T extends BaseEnum> Converter<Integer, T> getConverter(Class<T> targetType) {
        Converter<Integer, T> converter = CONVERTERS.get(targetType);
        if (converter == null) {
            converter = new IntegerToEnumConverter(BaseEnum.class);
            CONVERTERS.put(targetType, converter);
        }
        return converter;
    }
}
