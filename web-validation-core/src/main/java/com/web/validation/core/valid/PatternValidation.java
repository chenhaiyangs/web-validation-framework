package com.web.validation.core.valid;

import com.web.validation.core.ValidComponent;
import com.web.validation.core.annotation.valid.Pattern;
import com.web.validation.core.exception.ValidationFailException;

import java.lang.reflect.Field;

/**
 * 正则检验
 * @author chenhaiyang
 */
public class PatternValidation implements ValidComponent{
    @Override
    public void valid(Field field, Object value) throws ValidationFailException {
        Pattern pattern = field.getAnnotation(Pattern.class);
        if(pattern==null){
            return;
        }
        String regex = pattern.regex();
        if(value==null){
            throw new ValidationFailException(pattern.code(),String.format(pattern.message(),field.getName()));
        }
        if(value.getClass().getName().equalsIgnoreCase(STRING_CLASS) && !value.toString().matches(regex)){
            throw new ValidationFailException(pattern.code(),String.format(pattern.message(),field.getName()));
        }
    }
}
