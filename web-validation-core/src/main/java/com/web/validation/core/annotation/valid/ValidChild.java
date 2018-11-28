package com.web.validation.core.annotation.valid;

import java.lang.annotation.*;

/**
 * 是否校验复合字段的值：
 * 例如，A字段是一个对象B,如果该字段有注解@ProcessChild 也会校验B中的每一个字段的值。
 * 要求，字段B不能是集合,考虑到验证集合中的每一个属性对象耗费性能，因此不验证
 * 默认是不校验复合字段的值的。
 * 必须非null时验证才生效
 * @author chenhaiyang
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ValidChild {
}
