package com.web.validation.core.annotation.valid;

import java.lang.annotation.*;

/**
 * 检查一个元素的size是否在合理的范围内
 * 要求：元素非null值才开启验证
 * 可以是数组，集合，map等
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CollectionSize {
    /**
     * 最小值，默认-1 表示不验证最小值
     * @return 最小值
     */
    int min() default -1;

    /**
     * 最大值，默认-1 表示不验证最大值
     * @return  结果
     */
    int max() default -1;

    /**
     * 验证失败时错误码
     * @return code
     */
    String code() default "";

    /**
     * 验证失败时错误消息
     * @return message
     */
    String message() default "field %s validate error，rule : collectionSize";

}
