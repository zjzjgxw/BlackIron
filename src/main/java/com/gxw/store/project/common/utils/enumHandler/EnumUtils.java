package com.gxw.store.project.common.utils.enumHandler;

public class EnumUtils {
    public static <T extends Enum<?> & BaseEnum> T indexOf(Class<T> enumClass,Integer index){
        T[] enumConstants = enumClass.getEnumConstants();
        for(T t:enumConstants){
            if(t.getIndex() == index){
                return t;
            }
        }
        return null;
    }
}
