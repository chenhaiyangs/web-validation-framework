package com.web.validation.core.valid;

import com.web.validation.core.ValidComponent;
import com.web.validation.core.annotation.valid.NotNull;
import com.web.validation.core.exception.ValidationFailException;

import java.lang.reflect.Field;

/**
 * 校验notNull规则
 * @author chenhaiyang
 */
public class NotNullValidation implements ValidComponent{

    @Override
    public void valid(Field field, Object value) throws ValidationFailException {
        NotNull notNull =field.getAnnotation(NotNull.class);
        if(notNull==null){
            return;
        }
        if(value==null){
            throw new ValidationFailException(notNull.code(),String.format(notNull.message(),field.getName()));
        }
    }
}
