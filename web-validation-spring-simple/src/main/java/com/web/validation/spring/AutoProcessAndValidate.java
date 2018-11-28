package com.web.validation.spring;

import com.web.validation.core.Procession;
import com.web.validation.core.Validation;
import com.web.validation.core.exception.ValidationFailException;
import com.web.validation.spring.annotation.Process;
import com.web.validation.spring.annotation.Valid;
import com.web.validation.spring.aop.proxy.aspectj.AspectjProxyChain;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * 自动进行参数处理实现类
 * @author chenhaiyang
 */
public class AutoProcessAndValidate {

    /**
     * 自动进行参数处理
     * @param proxyChain 切面
     * @return 返回执行结果
     */
    public Object access(AspectjProxyChain proxyChain) throws Throwable {

        Object[] agruements = proxyChain.getArgs();
        Method method = proxyChain.getMethod();
        Annotation[][] parameterAnnotations = method.getParameterAnnotations();
        for(int i=0;i<agruements.length;i++){

            Map<String,Annotation> hasAnnotaions = new HashMap<>(2);
            Annotation[] annotations = parameterAnnotations[i];
            for(Annotation annotation :annotations){
                Process process =null;
                Valid valid =null;
                if(annotation.annotationType().getName().equals(Process.class.getName())){
                    process= (Process) annotation;
                }
                if(annotation.annotationType().getName().equals(Valid.class.getName())){
                    valid= (Valid) annotation;
                }
                if(process!=null){
                    hasAnnotaions.put(Process.class.getName(),process);
                }
                if(valid!=null){
                    hasAnnotaions.put(Valid.class.getName(),valid);
                }
            }
            Object agruement = agruements[i];
            //只添加了一个处理注解,只处理结果
            if(hasAnnotaions.size()==1){
                processSingleOne(hasAnnotaions,agruement);
            }
            //添加了2个处理注解，要按照顺序处理
            if(hasAnnotaions.size()==2){
                processByOrder(hasAnnotaions,agruement);
            }
        }
        return proxyChain.doProxyChain(agruements);
    }

    /**
     * 处理单个注解
     * @param hasAnnotaions 注解列表
     * @param agruement 参数
     */
    private void processSingleOne(Map<String, Annotation> hasAnnotaions,Object agruement) throws ValidationFailException {
        if(hasAnnotaions.get(Process.class.getName())!=null){
            Procession.process(agruement);
        }
        if(hasAnnotaions.get(Valid.class.getName())!=null){
            Validation.validate(agruement);
        }
    }

    /**
     * 处理process注解和valid注解都存在的情况
     * @param hasAnnotaions 注解集合
     * @param agruement 参数
     */
    private void processByOrder(Map<String, Annotation> hasAnnotaions, Object agruement) throws ValidationFailException {
        Process process = (Process) hasAnnotaions.get(Process.class.getName());
        int processOrder = process.order();
        Valid valid = (Valid) hasAnnotaions.get(Valid.class.getName());
        int validOrder = valid.order();
        if(processOrder>validOrder){
            Validation.validate(agruement);
            Procession.process(agruement);
        }else {
            Procession.process(agruement);
            Validation.validate(agruement);
        }


    }
}
