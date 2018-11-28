package com.web.validation.core.process;

import com.esotericsoftware.reflectasm.MethodAccess;
import com.web.validation.core.ProcessComponment;
import com.web.validation.core.Procession;
import com.web.validation.core.annotation.process.ProcessChild;
import com.web.validation.core.util.MethodUtil;

import java.lang.reflect.Field;

/**
 * 是否也处理对象属性中的字段
 * @author chenhaiyang
 */
public class ProcessChildProcess implements ProcessComponment{
    @Override
    public <T> void process(T t, Field field, MethodAccess methodAccess) {
        ProcessChild processChild = field.getAnnotation(ProcessChild.class);
        if(processChild!=null){
            Object value = methodAccess.invoke(t, MethodUtil.getGetMethodName(field.getName()));
            if(value!=null){
                Procession.process(value);
            }
        }
    }
}
