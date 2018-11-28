package com.web.validation.core;

import com.web.validation.core.exception.ValidationFailException;

import java.lang.reflect.Field;

/**
 * 验证组件
 * @author chenhaiyang
 */
public interface ValidComponent {

    String STRING_CLASS="java.lang.String";
    /**
     * 验证
     * @param field 字段
     * @param value value
     * @throws ValidationFailException 验证异常
     */
    void valid(Field field, Object value) throws ValidationFailException;

}
