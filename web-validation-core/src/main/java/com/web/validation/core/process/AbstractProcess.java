package com.web.validation.core.process;

import com.esotericsoftware.reflectasm.MethodAccess;
import com.web.validation.core.ProcessComponment;
import com.web.validation.core.util.MethodUtil;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

/**
 * 公共处理逻辑抽象类
 * @author chenhaiyang
 */
public abstract class AbstractProcess implements ProcessComponment{

    /**
     * 处理字段，前后去空格等操作
     * 如果实例没有提供getset方法，则中断该字段的处理，忽略异常。
     * @param t t范型实例
     * @param field 字段
     * @param methodAccess 反射method
     * @param <T> 范型定义
     */
    @Override
    public <T> void process(T t, Field field, MethodAccess methodAccess) {
        Annotation annotation = getAnnotation(field);
        if(annotation==null){
            return;
        }
        Object value;
        try{
            value = methodAccess.invoke(t, MethodUtil.getGetMethodName(field.getName()));
        }catch (Exception e){
            return;
        }
        if(value!=null && value instanceof String){
            value = getProcessResult(value.toString());
            try{
                methodAccess.invoke(t, MethodUtil.getSetMethodName(field.getName()),value);
            }catch (Exception e){
                //ignore it
            }
        }
    }

    /**
     * 在字段里获取指定的注解类
     * @param field 字段
     * @return 返回注解类
     */
    protected abstract Annotation getAnnotation(Field field);
    /**
     * 对字段的结果进行处理
     * @param src src
     * @return 返回处理结果
     */
    protected abstract String getProcessResult(String src);

}
