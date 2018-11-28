package com.web.validation.core.annotation.process;

import java.lang.annotation.*;

/**
 * 将注解生效的字符串全部转大写
 * 字段非null时生效
 * 非null时生效
 * @author chenhaiyang
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ToUpper {
}
