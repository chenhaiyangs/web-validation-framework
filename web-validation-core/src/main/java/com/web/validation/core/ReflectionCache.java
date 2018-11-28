package com.web.validation.core;

import com.esotericsoftware.reflectasm.MethodAccess;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 方法反射缓存
 * @author chenhaiyang
 */
class ReflectionCache {
    /**
     * 反射缓存
     */
    private static final Map<Class<?>,MethodAccess> REFLECT_CACHE = new ConcurrentHashMap<>();
    /**
     * 获取MethodAccess
     * @param clazz clazz
     * @return 返回结果
     */
    static MethodAccess getByClass(Class<?> clazz){
        MethodAccess methodAccess = REFLECT_CACHE.get(clazz);
        if(methodAccess==null){
            synchronized (REFLECT_CACHE){
                methodAccess = MethodAccess.get(clazz);
                REFLECT_CACHE.put(clazz,methodAccess);
            }
        }
        return methodAccess;
    }
}
