package com.web.validation.spring.annotation;

import java.lang.annotation.*;

/**
 * 在aop的实现中，在方法行参添加该注解表示处理请求参数，特殊处理：去空格，大小写转换等
 * process和valid的order表示执行参数验证和参数处理的顺序，
 * 先处理order小的行为，默认先处理参数处理process，后参数验证
 * @author chenhaiyang
 */
@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Process {

    int order() default 1;
}
