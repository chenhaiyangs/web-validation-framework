package com.web.validation.core.annotation.process;

import java.lang.annotation.*;

/**
 * 给字符串trim，前后空格都去掉
 * 字段非null时生效
 * @author chenhaiyang
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface TrimPrefixAndSuffix {

}
