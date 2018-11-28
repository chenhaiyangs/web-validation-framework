package com.web.validation.core.annotation.valid;

import java.lang.annotation.*;

/**
 * 可以为null，但不能是空串,只验证字符串
 * @author chenhaiyang
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface NotEmpty {

    /**
     * 验证失败时错误码
     * @return code
     */
    String code() default "";

    /**
     * 验证失败时错误消息
     * @return message
     */
    String message() default "field %s validate error，rule : notEmpty";
}
