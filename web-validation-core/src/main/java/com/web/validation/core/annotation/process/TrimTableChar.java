package com.web.validation.core.annotation.process;

import java.lang.annotation.*;

/**
 * 移除字符串的制表符,例如，\r\n等
 * 非null时生效
 * @author chenhaiyang
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface TrimTableChar {
}
