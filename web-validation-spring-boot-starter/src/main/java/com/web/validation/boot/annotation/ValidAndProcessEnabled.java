package com.web.validation.boot.annotation;

import com.web.validation.boot.ImportedClassSelector;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * 启动注解
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Import({ImportedClassSelector.class})
public @interface ValidAndProcessEnabled {
}
