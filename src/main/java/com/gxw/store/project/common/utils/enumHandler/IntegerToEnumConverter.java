package com.gxw.store.project.common.utils.enumHandler;

import com.google.common.collect.Maps;
import org.springframework.core.convert.converter.Converter;

import java.util.Map;

/**
 * Integer 转 Enum
 */
public class IntegerToEnumConverter<T extends Enum<T> & BaseEnum> implements Converter<Integer, T> {
    private Map<Integer, T> enumMap =  Maps.newHashMap();

    public IntegerToEnumConverter(Class<T> enumType) {
        T[] enums = enumType.getEnumConstants();
        for (T e : enums) {
            enumMap.put(e.getIndex(), e);
        }
    }

    @Override
    public T convert(Integer integer) {
        T t = enumMap.get(integer);
        if (t == null) {
            throw new IllegalArgumentException("无法匹配对应的枚举类型");
        }
        return t;
    }
}
