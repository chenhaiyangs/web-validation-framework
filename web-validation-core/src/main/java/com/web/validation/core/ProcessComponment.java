package com.web.validation.core;

import com.esotericsoftware.reflectasm.MethodAccess;

import java.lang.reflect.Field;

/**
 * 处理组件
 * 处理实体字段的process类
 * @author chenhaiyang
 */
public interface ProcessComponment{

    /**
     * 处理每个字段
     * @param t t范型
     * @param field 字段
     * @param methodAccess 反射method
     */
    <T> void  process(T  t, Field field, MethodAccess methodAccess);
}
