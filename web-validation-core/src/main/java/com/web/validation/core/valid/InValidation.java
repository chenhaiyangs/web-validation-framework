package com.web.validation.core.valid;

import com.web.validation.core.ValidComponent;
import com.web.validation.core.annotation.valid.In;
import com.web.validation.core.exception.ValidationFailException;

import java.lang.reflect.Field;

/**
 * in注解的逻辑验证
 * @author chenhaiyang
 */
public class InValidation implements ValidComponent {

    @Override
    public void valid(Field field, Object value) throws ValidationFailException {
        In in = field.getAnnotation(In.class);
        if(in==null || value==null || !value.getClass().getName().startsWith(BASIC_TYPE)){
            return;
        }
        String[] contails = in.contains();
        boolean match =false;
        for(String contail :contails){
            if(contail.equals(value.toString())){
                match=true;
                break;
            }
        }
        if(!match){
            throw new ValidationFailException(in.code(),String.format(in.message(),field.getName()));
        }
    }
}
