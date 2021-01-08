package io.github.hashmaparraylist.aop.overview;

import java.lang.reflect.Method;

/**
 * (方法返回)后置拦截器
 *
 * @author
 * @date 2021/1/8
 */
public interface AfterReturnInterceptor {
    /**
     * 后置拦截
     * @param proxy
     * @param method
     * @param args
     * @param returnResult
     * @return
     */
    Object after(Object proxy, Method method, Object[] args, Object returnResult);
}
