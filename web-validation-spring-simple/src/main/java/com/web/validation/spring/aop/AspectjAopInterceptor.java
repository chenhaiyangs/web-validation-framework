package com.web.validation.spring.aop;


import com.web.validation.spring.AutoProcessAndValidate;
import com.web.validation.spring.aop.proxy.aspectj.AspectjProxyChain;
import org.aspectj.lang.ProceedingJoinPoint;

/**
 * 使用aspectj 实现AOP拦截
 * 注意，要拦截的类的要拦截的方法不能有重名方法
 * @author chenhaiyang
 */
public class AspectjAopInterceptor {
    /**
     * 自动处理参数和校验的组件
     */
    private AutoProcessAndValidate autoProcession = new AutoProcessAndValidate();
    /**
     * 进行参数处理和参数校验的切面
     * @param aopProxyChain 切面
     * @return 返回结果
     * @throws Throwable 异常
     */
    public Object process(ProceedingJoinPoint aopProxyChain) throws Throwable {
        return autoProcession.access(new AspectjProxyChain(aopProxyChain));
    }
}
