package com.web.validation.spring.annotation;

import java.lang.annotation.*;

/**
 * 在aop的实现中，方法入参加该注解，表示进行参数验证
 * process和valid的order表示执行参数验证和参数处理的顺序，
 * 先处理order小的行为，默认先处理参数处理process，后参数验证
 * @author chenhaiyang
 */
@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Valid {

    int order() default 2;
}
