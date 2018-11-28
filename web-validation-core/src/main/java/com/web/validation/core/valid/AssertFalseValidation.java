package com.web.validation.core.valid;

import com.web.validation.core.ValidComponent;
import com.web.validation.core.annotation.valid.AssertBool;
import com.web.validation.core.annotation.valid.AssertFalse;
import com.web.validation.core.exception.ValidationFailException;

import java.lang.reflect.Field;

/**
 * 校验是否为false
 * @author chenhaiyang
 */
public class AssertFalseValidation implements ValidComponent{
    private static final String FALSE="false";

    @Override
    public void valid(Field field, Object value) throws ValidationFailException {
        AssertFalse assertBool = field.getAnnotation(AssertFalse.class);
        if(value==null || assertBool==null){
            return;
        }
        String stringValue = value.toString();
        if(!FALSE.equalsIgnoreCase(stringValue)){
            throw new ValidationFailException(assertBool.code(),String.format(assertBool.message(),field.getName()));
        }

    }
}
