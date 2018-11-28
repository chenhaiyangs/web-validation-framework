package com.web.validation.core.valid;

import com.web.validation.core.ValidComponent;
import com.web.validation.core.annotation.valid.LongNumber;
import com.web.validation.core.exception.ValidationFailException;

import java.lang.reflect.Field;

/**
 * long类型数字验证
 * @author chenhaiyang
 */
public class LongNumberValidation implements ValidComponent{
    @Override
    public void valid(Field field, Object value) throws ValidationFailException {
        LongNumber longNumber = field.getAnnotation(LongNumber.class);
        if(value==null || longNumber==null){
            return;
        }
        long min = longNumber.min();
        long max = longNumber.max();
        String longValue = value.toString();
        try{
            Long longResult= Long.parseLong(longValue);
            if(min!= -1 && longResult<min){
                throw new ValidationFailException(longNumber.code(),String.format(longNumber.message(),field.getName()));
            }
            if(max!= -1 && longResult> max){
                throw new ValidationFailException(longNumber.code(),String.format(longNumber.message(),field.getName()));
            }
        }catch (Exception e){
            throw new ValidationFailException(longNumber.code(),String.format(longNumber.message(),field.getName()));
        }
    }
}
