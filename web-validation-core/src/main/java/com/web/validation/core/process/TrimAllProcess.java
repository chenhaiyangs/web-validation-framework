package com.web.validation.core.process;

import com.web.validation.core.annotation.process.TrimAll;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

/**
 * 去掉所有空格，前面，后面，和中间
 * @author chenhaiyang
 */
public class TrimAllProcess extends AbstractProcess{

    @Override
    protected Annotation getAnnotation(Field field) {
        return field.getAnnotation(TrimAll.class);
    }

    @Override
    protected String getProcessResult(String src) {
        return src.replaceAll(" ","");

    }
}
