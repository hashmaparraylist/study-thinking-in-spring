package io.github.hashmaparraylist.aop.overview;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * AOP 拦截器模式
 *
 * @author
 * @date 2021/1/8
 */
public class AopInterceptorDemo {
    public static void main(String[] args) {
        // 前置模式
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        Object target = Proxy.newProxyInstance(classLoader, new Class[]{EchoService.class}, (proxy, method, methodArgs) -> {
            if (EchoService.class.isAssignableFrom(method.getDeclaringClass())) {
                BeforeInterceptor beforeInterceptor = (proxy12, method12, args12) -> System.currentTimeMillis();
                Long startTime = 0L;
                Long endTime = 0L;
                Object result = null;
                try {
                    startTime = (Long) beforeInterceptor.before(proxy, method, args);
                    EchoService echoService = new DefaultEchoService();
                    result = echoService.echo((String) methodArgs[0]); // 目标对象执行
                    // 方法执行后置拦截器
                    AfterReturnInterceptor afterReturnInterceptor = (proxy1, method1, args1, returnResult) -> System.currentTimeMillis();
                    endTime = (Long) afterReturnInterceptor.after(proxy, method, args, result);
                    return result;
                } catch (Exception ex) {
                    // 异常拦截
                    ExceptionInterceptor interceptor = (proxy13, method13, args13, throwable) -> {
                        // TODO
                    };
                } finally {
                    // finally 后置拦截器
                    FinallyInterceptor intercept = new TimeFinallyIntercept(startTime, endTime);
                    Long costTime = (Long) intercept.finalize(proxy, method, args, result);
                    System.out.println("echo 方法执行时间: " + costTime + " ms");
                }
            }
            return null;
        });

        EchoService echoService = (EchoService) target;
        echoService.echo("Hello, World");
    }
}

class TimeFinallyIntercept implements FinallyInterceptor {

    private final Long startTime;
    private final Long endTime;

    TimeFinallyIntercept(Long startTime, Long endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public Object finalize(Object proxy, Method method, Object[] args, Object returnResult) {
        // 方法执行时间
        Long costTime = endTime - startTime;
        return costTime;
    }
}