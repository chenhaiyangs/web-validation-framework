package com.web.validation.boot.interceptor;

import com.web.validation.spring.AutoProcessAndValidate;
import com.web.validation.spring.aop.proxy.aspectj.AspectjProxyChain;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

@Aspect
@Order(99)
@Configuration
@SuppressWarnings("all")
public class ValidationInterceptor {
    /**
     * 处理验证和加工javaBean参数的核心实现类
     */
    private AutoProcessAndValidate autoProcessAndValidate = new AutoProcessAndValidate();
    /**
     * requestMapping
     * putMapping
     * postMapping
     * patchMapping
     * modelAttribute
     * getMapping
     * deleteMapping
     * CrossOrigin
     */
    @Pointcut("@annotation(org.springframework.web.bind.annotation.RequestMapping)" +
            "||@annotation(org.springframework.web.bind.annotation.PutMapping)"+
            "||@annotation(org.springframework.web.bind.annotation.PostMapping)"+
            "||@annotation(org.springframework.web.bind.annotation.PatchMapping)"+
            "||@annotation(org.springframework.web.bind.annotation.ModelAttribute)"+
            "||@annotation(org.springframework.web.bind.annotation.GetMapping)" +
            "||@annotation(org.springframework.web.bind.annotation.DeleteMapping)"+
            "||@annotation(org.springframework.web.bind.annotation.CrossOrigin)" +
            "||execution(* *(@com.web.validation.spring.annotation.Valid (*), ..))" +
            "||execution(* *(@com.web.validation.spring.annotation.Process (*), ..))")
    public void match() {

    }

    /**
     * 执行结果
     * @param joinPoint 切点
     * @return 返回结果
     */
    @Around("match()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        return autoProcessAndValidate.access(new AspectjProxyChain(joinPoint));
    }
}
