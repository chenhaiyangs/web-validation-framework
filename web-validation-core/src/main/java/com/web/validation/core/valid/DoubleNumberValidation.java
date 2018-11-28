package com.web.validation.core.valid;

import com.web.validation.core.ValidComponent;
import com.web.validation.core.annotation.valid.DoubleNumber;
import com.web.validation.core.exception.ValidationFailException;

import java.lang.reflect.Field;

/**
 * double数字校验
 * @author chenhaiyang
 */
public class DoubleNumberValidation implements ValidComponent{
    @Override
    public void valid(Field field, Object value) throws ValidationFailException {
        DoubleNumber doubleNumber = field.getAnnotation(DoubleNumber.class);
        if(value==null || doubleNumber==null){
            return;
        }
        double min = doubleNumber.min();
        double max = doubleNumber.max();
        String doubleValue = value.toString();
        try{
            Double doubleResult= Double.parseDouble(doubleValue);
            if(min!= -1 && doubleResult<min){
                throw new ValidationFailException(doubleNumber.code(),String.format(doubleNumber.message(),field.getName()));
            }
            if(max!= -1 && doubleResult> max){
                throw new ValidationFailException(doubleNumber.code(),String.format(doubleNumber.message(),field.getName()));
            }
        }catch (Exception e){
            throw new ValidationFailException(doubleNumber.code(),String.format(doubleNumber.message(),field.getName()));
        }
    }
}
