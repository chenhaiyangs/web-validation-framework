package com.web.validation.core;

import com.esotericsoftware.reflectasm.MethodAccess;
import com.web.validation.core.annotation.process.*;
import com.web.validation.core.constants.Constants;
import com.web.validation.core.process.*;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Collection;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 处理实体字段的process类
 * @author chenhaiyang
 */
public class Procession {
    /**
     * 处理组列表
     */
    private static Map<String,ProcessComponment> validcomponents = new ConcurrentHashMap<>();
    static {
        validcomponents.put(ToUpper.class.getName(),new ToUpperProcess());
        validcomponents.put(ToLower.class.getName(),new ToLowerProcess());
        validcomponents.put(TrimAll.class.getName(),new TrimAllProcess());
        validcomponents.put(TrimPrefixAndSuffix.class.getName(),new TrimPrefixAndSuffixProcess());
        validcomponents.put(TrimTableChar.class.getName(),new TrimTableCharProcess());
        validcomponents.put(ProcessChild.class.getName(),new ProcessChildProcess());
    }
    /**
     * 添加组件
     * @param clazz 类
     * @param processComponment 组件
     */
    public static void addComponment(Class<?> clazz,ProcessComponment processComponment){
        Objects.requireNonNull(clazz,"clazz must not be null");
        Objects.requireNonNull(processComponment,"component must not be null");

        validcomponents.putIfAbsent(clazz.getName(),processComponment);
    }

    public static <T> void process(T t){
        //数组集合等的不处理，直接跳过,只验证一元对象里面的基本类型字段
        if(t==null
                || t.getClass().getName().startsWith(Constants.NOT_VALIDATE)
                || t.getClass().isArray()
                || t instanceof Collection
                || t instanceof Map){
            return;
        }
        Class clazz = t.getClass();
        MethodAccess methodAccess = ReflectionCache.getByClass(clazz);
        //获得对象的所有成员变量
        for(Field field :clazz.getDeclaredFields()){
            Annotation[] annotations = field.getDeclaredAnnotations();
            for(Annotation annotation:annotations){
                String annotationName = annotation.annotationType().getName();
                if(validcomponents.get(annotationName)!=null){
                    validcomponents.get(annotationName).process(t,field,methodAccess);
                }
            }
        }
    }
}
