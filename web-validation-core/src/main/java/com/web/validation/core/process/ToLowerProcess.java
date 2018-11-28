package com.web.validation.core.process;

import com.web.validation.core.annotation.process.ToLower;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

/**
 * 转小写
 * @author chenhaiyang
 */
public class ToLowerProcess extends AbstractProcess{

    @Override
    protected Annotation getAnnotation(Field field) {
        return field.getAnnotation(ToLower.class);
    }

    @Override
    protected String getProcessResult(String src) {
        return src.toLowerCase();
    }
}
