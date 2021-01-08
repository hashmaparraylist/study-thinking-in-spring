package io.github.hashmaparraylist.aop.overview;

import java.lang.reflect.Method;

/**
 * 前置拦截器
 *
 * @author
 * @date 2021/1/8
 */
public interface BeforeInterceptor {
    /**
     * 前置执行
     * @param proxy
     * @param method
     * @param args
     * @return
     */
    Object before(Object proxy, Method method, Object[] args);
}
