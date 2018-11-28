package com.web.validation.core.annotation.valid;

import java.lang.annotation.*;

/**
 * 通用验证，表示字符串必须符合正则才可以
 * @author chenhaiyang
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Pattern {

    /**
     * 正则表达式
     * @return 返回正则表达式
     */
    String regex();

    /**
     * 验证失败时错误码
     * @return code
     */
    String code() default "";

    /**
     * 验证失败时错误消息
     * @return message
     */
    String message() default "field %s validate error，rule : pattern";
}
