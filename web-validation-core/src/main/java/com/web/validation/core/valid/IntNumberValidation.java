package com.web.validation.core.valid;

import com.web.validation.core.ValidComponent;
import com.web.validation.core.annotation.valid.IntNumber;
import com.web.validation.core.exception.ValidationFailException;

import java.lang.reflect.Field;

/**
 * int类型数字验证
 * @author chenhaiyang
 */
public class IntNumberValidation implements ValidComponent{
    @Override
    public void valid(Field field, Object value) throws ValidationFailException {
        IntNumber intNumber = field.getAnnotation(IntNumber.class);
        if(value==null  || intNumber==null){
            return;
        }
        int min = intNumber.min();
        int max = intNumber.max();
        String intValue = value.toString();
        try{
            Integer intResult= Integer.parseInt(intValue);
            if(min!= -1 && intResult<min){
                throw new ValidationFailException(intNumber.code(),String.format(intNumber.message(),field.getName()));
            }
            if(max!= -1 && intResult> max){
                throw new ValidationFailException(intNumber.code(),String.format(intNumber.message(),field.getName()));
            }
        }catch (Exception e){
            throw new ValidationFailException(intNumber.code(),String.format(intNumber.message(),field.getName()));
        }
    }
}
