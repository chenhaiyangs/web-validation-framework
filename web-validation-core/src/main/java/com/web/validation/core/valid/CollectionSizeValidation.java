package com.web.validation.core.valid;

import com.web.validation.core.ValidComponent;
import com.web.validation.core.annotation.valid.CollectionSize;
import com.web.validation.core.exception.ValidationFailException;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.Map;

/**
 * 集合类校验规则
 * @author chenhaiyang
 */
public class CollectionSizeValidation implements ValidComponent{
    @Override
    public void valid(Field field, Object value) throws ValidationFailException {
        CollectionSize collectionSize = field.getAnnotation(CollectionSize.class);
        if(value==null || collectionSize==null){
            return;
        }
        int min = collectionSize.min();
        int max = collectionSize.max();
        int size = -1;
        if(value.getClass().isArray()){
            Object[] arr = (Object[]) value;
            size=arr.length;
        }
        if(value instanceof Collection){
            Collection collection = (Collection) value;
            size =collection.size();
        }
        if(value instanceof Map){
            Map map = (Map) value;
            size=map.size();
        }

        if(min!= -1 && size!=-1 && size<min){
            throw new ValidationFailException(collectionSize.code(),String.format(collectionSize.message(),field.getName()));
        }
        if(max!=-1 && size!= -1 &&size>max){
            throw new ValidationFailException(collectionSize.code(),String.format(collectionSize.message(),field.getName()));
        }

    }
}
