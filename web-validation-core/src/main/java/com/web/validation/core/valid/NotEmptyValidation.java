package com.web.validation.core.valid;

import com.web.validation.core.ValidComponent;
import com.web.validation.core.annotation.valid.NotEmpty;
import com.web.validation.core.exception.ValidationFailException;

import java.lang.reflect.Field;

/**
 * 验证是否是空串
 * @author chenhaiyang
 */
public class NotEmptyValidation implements ValidComponent{
    @Override
    public void valid(Field field, Object value) throws ValidationFailException {
        NotEmpty notEmpty = field.getAnnotation(NotEmpty.class);
        if(value == null || notEmpty==null || !STRING_CLASS.equals(value.getClass().getName())){
           return;
        }
        if(value.toString().trim().length()==0){
            throw new ValidationFailException(notEmpty.code(),String.format(notEmpty.message(),field.getName()));
        }
    }
}
