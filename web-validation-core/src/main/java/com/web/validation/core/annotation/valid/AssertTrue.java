package com.web.validation.core.annotation.valid;

import java.lang.annotation.*;

/**
 * 必须是布尔类型，且必须为true
 * 必须非null时验证才生效
 * @author chenhaiyang
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AssertTrue {
    /**
     * 验证失败时错误码
     * @return code
     */
    String code() default "";

    /**
     * 验证失败时错误消息
     * @return message
     */
    String message() default "field %s validate error，rule : assertTrue";
}
