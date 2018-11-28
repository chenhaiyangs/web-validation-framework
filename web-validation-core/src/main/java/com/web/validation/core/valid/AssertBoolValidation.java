package com.web.validation.core.valid;

import com.web.validation.core.ValidComponent;
import com.web.validation.core.annotation.valid.AssertBool;
import com.web.validation.core.exception.ValidationFailException;

import java.lang.reflect.Field;

/**
 * 校验布尔值
 * @author chenhaiyang
 */
public class AssertBoolValidation implements ValidComponent{

    private static final String TRUE="true";
    private static final String FALSE="false";

    @Override
    public void valid(Field field, Object value) throws ValidationFailException {
        AssertBool assertBool = field.getAnnotation(AssertBool.class);
        if(value==null || assertBool==null ){
            return;
        }
        String stringValue = value.toString();
        if(!TRUE.equalsIgnoreCase(stringValue) && !FALSE.equalsIgnoreCase(stringValue)){
            throw new ValidationFailException(assertBool.code(),String.format(assertBool.message(),field.getName()));
        }

    }
}
