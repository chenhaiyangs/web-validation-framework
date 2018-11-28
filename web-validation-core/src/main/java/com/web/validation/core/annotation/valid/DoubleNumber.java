package com.web.validation.core.annotation.valid;

import java.lang.annotation.*;

/**
 * 该字段必须为double类型的number
 * 非null时才开启验证
 * @author chenhaiyang
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DoubleNumber {

    /**
     * 最小值，默认-1 表示不验证最小值
     * @return 最小值
     */
    double min() default -1;

    /**
     * 最大值，默认-1 表示不验证最大值
     * @return  结果
     */
    double max() default -1;
    /**
     * 验证失败时错误码
     * @return code
     */
    String code() default "";

    /**
     * 验证失败时错误消息
     * @return message
     */
    String message() default "field %s validate error，rule : doubleNumber";



}
