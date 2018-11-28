package com.web.validation.core.valid;

import com.web.validation.core.ValidComponent;
import com.web.validation.core.annotation.valid.AssertBool;
import com.web.validation.core.annotation.valid.AssertTrue;
import com.web.validation.core.exception.ValidationFailException;

import java.lang.reflect.Field;

/**
 * 校验是否为true
 * @author chenhaiyang
 */
public class AssertTrueValidation implements ValidComponent{

    private static final String TRUE="true";

    @Override
    public void valid(Field field, Object value) throws ValidationFailException {
        AssertTrue assertBool = field.getAnnotation(AssertTrue.class);
        if(value==null || assertBool==null){
            return;
        }
        String stringValue = value.toString();
        if(!TRUE.equalsIgnoreCase(stringValue)){
            throw new ValidationFailException(assertBool.code(),String.format(assertBool.message(),field.getName()));
        }

    }
}
