package com.web.validation.core.valid;

import com.web.validation.core.ValidComponent;
import com.web.validation.core.Validation;
import com.web.validation.core.annotation.valid.ValidChild;
import com.web.validation.core.exception.ValidationFailException;

import java.lang.reflect.Field;

/**
 * 是否校验子节点
 * @author chenhaiyang
 */
public class ValidChildValidation implements ValidComponent{
    @Override
    public void valid(Field field, Object value) throws ValidationFailException {
        ValidChild validChild = field.getAnnotation(ValidChild.class);
        if(value==null || validChild==null){
            return;
        }
        Validation.validate(value);
    }
}
