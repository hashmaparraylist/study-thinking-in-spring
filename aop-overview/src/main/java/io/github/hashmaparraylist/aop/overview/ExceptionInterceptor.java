package io.github.hashmaparraylist.aop.overview;

import java.lang.reflect.Method;

/**
 * 异常拦截器
 */
public interface ExceptionInterceptor {
    /**
     * 异常拦截
     * @param proxy
     * @param method
     * @param args
     * @param throwable
     */
    void intercept(Object proxy, Method method, Object[] args, Throwable throwable);
}
