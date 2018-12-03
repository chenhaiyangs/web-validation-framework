package com.web.validation.core.annotation.valid;

import java.lang.annotation.*;

/**
 * 该字段必须包含在contains的描述中，只能验证基本类型,即包装类型在java.lang包内
 * 字段为null时不验证。如果想字段非null，且只能是指定值，请添加NotNULl注解。
 * 注意基础数据类型的默认值问题。
 * @author chenhaiyang
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface In {
    /**
     * 包含的可选值
     * @return 返回结果
     */
    String[] contains();
    /**
     * 验证失败时错误码
     * @return code
     */
    String code() default "";

    /**
     * 验证失败时错误消息
     * @return message
     */
    String message() default "field %s validate error，rule : in";
}
