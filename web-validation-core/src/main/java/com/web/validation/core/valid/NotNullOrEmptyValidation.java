package com.web.validation.core.valid;

import com.web.validation.core.ValidComponent;
import com.web.validation.core.annotation.valid.NotNullorEmpty;
import com.web.validation.core.exception.ValidationFailException;

import java.lang.reflect.Field;

/**
 * 不允许为null也不允许为空，空格也不允许
 * @author chenhaiyang
 */
public class NotNullOrEmptyValidation implements ValidComponent {
    @Override
    public void valid(Field field, Object value) throws ValidationFailException {
        NotNullorEmpty notNullorEmpty = field.getAnnotation(NotNullorEmpty.class);
        if(notNullorEmpty==null){
            return;
        }
        if(value==null || value.toString().trim().length()==0){
            throw new ValidationFailException(notNullorEmpty.code(),String.format(notNullorEmpty.message(),field.getName()));
        }
    }
}
