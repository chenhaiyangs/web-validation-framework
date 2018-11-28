package com.web.validation.core.valid;

import com.web.validation.core.ValidComponent;
import com.web.validation.core.annotation.valid.DateStringFormat;
import com.web.validation.core.exception.ValidationFailException;

import java.lang.reflect.Field;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * 验证时间格式的字符串
 * @author chenhaiyang
 */
public class DateFormatStringValidation implements ValidComponent{
    @Override
    public void valid(Field field, Object value) throws ValidationFailException {
        DateStringFormat format =field.getAnnotation(DateStringFormat.class);
        if(format==null || value==null){
            return;
        }
        String formatStr = format.format();
        SimpleDateFormat dateFormat = new SimpleDateFormat(formatStr);
        dateFormat.setLenient(false);
        try {
            dateFormat.parse(value.toString());
        } catch (ParseException e) {
            throw new ValidationFailException(format.code(),String.format(format.message(),field.getName()));
        }

    }
}
