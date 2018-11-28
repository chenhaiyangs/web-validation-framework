package com.web.validation.core.annotation.valid;

import java.lang.annotation.*;

/**
 * 给时间格式字符串约定其必须符合某种格式
 * @author chenhaiyang
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DateStringFormat {

    /**
     * 时间戳格式
     * 如yyyy-MM-dd
     *  yyyy-MM-dd HH:mm:ss
     *  ......
     * @return format
     */
    String format();

    /**
     * 验证失败时错误码
     * @return code
     */
    String code() default "";

    /**
     * 验证失败时错误消息
     * @return message
     */
    String message() default "field %s validate error，rule : dataStringFormat";
}
