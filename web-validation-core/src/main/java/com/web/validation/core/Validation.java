package com.web.validation.core;

import com.esotericsoftware.reflectasm.MethodAccess;
import com.web.validation.core.annotation.valid.*;
import com.web.validation.core.constants.Constants;
import com.web.validation.core.exception.ValidationFailException;
import com.web.validation.core.util.MethodUtil;
import com.web.validation.core.valid.*;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Collection;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 验证实体参数的validation类
 * @author chenhaiyang
 */
public class Validation {
    /**
     * 验证组列表
     */
    private static Map<String,ValidComponent> validcomponents = new ConcurrentHashMap<>();
    static{
        validcomponents.put(NotNull.class.getName(),new NotNullValidation());
        validcomponents.put(NotEmpty.class.getName(),new NotEmptyValidation());
        validcomponents.put(NotNullorEmpty.class.getName(),new NotNullOrEmptyValidation());
        validcomponents.put(StringLength.class.getName(),new StringLengthValidation());
        validcomponents.put(AssertBool.class.getName(),new AssertBoolValidation());
        validcomponents.put(AssertTrue.class.getName(),new AssertTrueValidation());
        validcomponents.put(AssertFalse.class.getName(),new AssertFalseValidation());
        validcomponents.put(Pattern.class.getName(),new PatternValidation());
        validcomponents.put(ValidChild.class.getName(),new ValidChildValidation());
        validcomponents.put(CollectionSize.class.getName(),new CollectionSizeValidation());
        validcomponents.put(IntNumber.class.getName(),new IntNumberValidation());
        validcomponents.put(LongNumber.class.getName(),new LongNumberValidation());
        validcomponents.put(DoubleNumber.class.getName(),new DoubleNumberValidation());
        validcomponents.put(DateStringFormat.class.getName(),new DateFormatStringValidation());
        validcomponents.put(In.class.getName(),new InValidation());

    }
    /**
     * 添加组件
     * @param clazz 类
     * @param component 组件
     */
    public static void addComponment(Class<?> clazz,ValidComponent component){
        Objects.requireNonNull(clazz,"clazz must not be null");
        Objects.requireNonNull(component,"component must not be null");

        validcomponents.putIfAbsent(clazz.getName(),component);
    }

    /**
     * 正式校验
     * @param t t
     * @param <T> 被校验的Bean的泛型
     */
    public static <T> void validate(T t) throws ValidationFailException {

        //数组集合等的不验证，直接跳过,只验证一元对象
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

            Object value;
            try {
                //没有get函数的字段直接略过
                value = methodAccess.invoke(t, MethodUtil.getGetMethodName(field.getName()));
            }catch (Exception e){
                continue;
            }
            Annotation[] annotations = field.getDeclaredAnnotations();
            for(Annotation annotation:annotations){
                String annotationName = annotation.annotationType().getName();
                if(validcomponents.get(annotationName)!=null){
                    validcomponents.get(annotationName).valid(field,value);
                }
            }
        }
    }
}
