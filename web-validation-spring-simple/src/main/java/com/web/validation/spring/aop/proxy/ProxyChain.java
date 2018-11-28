package com.web.validation.spring.aop.proxy;

import java.lang.reflect.Method;

/**
 * 拦截层
 * @author chenhaiyang
 */
public interface ProxyChain {
    /**
     * 获取参数
     * @return 参数
     */
    Object[] getArgs();

    /**
     * 获取目标实例
     * @return 目标实例
     */
    Object getTarget();

    /**
     * 获取方法
     * @return Method
     */
    Method getMethod();

    /**
     * 执行方法
     * @param arguments 参数
     * @return 执行结果
     * @throws Throwable Throwable
     */
    Object doProxyChain(Object[] arguments) throws Throwable;
}
