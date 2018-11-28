package com.web.validation.core.process;

import com.web.validation.core.annotation.process.ToUpper;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

/**
 * 大写处理类
 * @author chenhaiyang
 */
public class ToUpperProcess extends AbstractProcess {

    @Override
    protected Annotation getAnnotation(Field field) {
        return field.getAnnotation(ToUpper.class);
    }

    @Override
    protected String getProcessResult(String src) {
        return src.toUpperCase();
    }
}
