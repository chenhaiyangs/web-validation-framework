package com.web.validation.core.util;

import java.util.Objects;

/**
 * 方法工具类
 * @author chenhaiyang
 */
public class MethodUtil {
    /**
     * 获取字段的get函数
     * @param fieldName fieldName
     * @return 返回结果
     */
    public static String getGetMethodName(String fieldName){
        Objects.requireNonNull(fieldName,"fieldName must not be null");
        return "get"+fieldName.substring(0,1).toUpperCase()+fieldName.substring(1);
    }

    /**
     * 根据字段名获取set方法
     * @param fieldName 字段名
     * @return 返回结果
     */
    public static String getSetMethodName(String fieldName) {
        Objects.requireNonNull(fieldName,"fieldName must not be null");
        return "set"+fieldName.substring(0,1).toUpperCase()+fieldName.substring(1);
    }
}
