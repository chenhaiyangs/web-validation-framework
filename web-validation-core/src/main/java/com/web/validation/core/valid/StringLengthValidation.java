package com.web.validation.core.valid;

import com.web.validation.core.ValidComponent;
import com.web.validation.core.annotation.valid.StringLength;
import com.web.validation.core.exception.ValidationFailException;

import java.lang.reflect.Field;

/**
 * 字符串长度校验
 * @author chenhaiyang
 */
public class StringLengthValidation implements ValidComponent{
    @Override
    public void valid(Field field, Object value) throws ValidationFailException {
        StringLength stringLength = field.getAnnotation(StringLength.class);
        if(value==null || stringLength==null ||!STRING_CLASS.equals(value.getClass().getName())){
            return;
        }

        int minLength =stringLength.min();
        int maxLength =stringLength.max();
        int length = value.toString().length();
        if(minLength!= -1 && length<minLength){
            throw new ValidationFailException(stringLength.code(),String.format(stringLength.message(),field.getName()));
        }
        if(maxLength!= -1 && length>maxLength){
            throw new ValidationFailException(stringLength.code(),String.format(stringLength.message(),field.getName()));
        }
    }
}
