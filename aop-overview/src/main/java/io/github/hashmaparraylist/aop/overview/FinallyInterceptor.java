package io.github.hashmaparraylist.aop.overview;

import java.lang.reflect.Method;

/**
 * 最终执行后置拦截器
 *
 * @author
 * @date 2021/1/8
 */
public interface FinallyInterceptor {
    /**
     * 最终执行
     * @param proxy
     * @param method
     * @param args
     * @param returnResult
     * @return
     */
    Object finalize(Object proxy, Method method, Object[] args, Object returnResult);
}
