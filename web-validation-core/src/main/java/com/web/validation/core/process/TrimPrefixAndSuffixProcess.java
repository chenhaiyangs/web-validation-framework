package com.web.validation.core.process;

import com.web.validation.core.annotation.process.TrimPrefixAndSuffix;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

/**
 * 去除前后的空格
 * @author chenhaiyang
 */
public class TrimPrefixAndSuffixProcess extends AbstractProcess{

    @Override
    protected Annotation getAnnotation(Field field) {
        return field.getAnnotation(TrimPrefixAndSuffix.class);
    }

    @Override
    protected String getProcessResult(String src) {
        return src.trim();
    }
}
