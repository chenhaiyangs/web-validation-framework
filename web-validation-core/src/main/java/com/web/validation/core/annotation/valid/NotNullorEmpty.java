package com.web.validation.core.annotation.valid;

import java.lang.annotation.*;

/**
 * 不允许为null也不允许为空，空格也不允许
 * @author chenhaiyang
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface NotNullorEmpty {
    /**
     * 验证失败时错误码
     * @return code
     */
    String code() default "";

    /**
     * 验证失败时错误消息
     * @return message
     */
    String message() default "field %s validate error，rule : notNullorEmpty";
}
