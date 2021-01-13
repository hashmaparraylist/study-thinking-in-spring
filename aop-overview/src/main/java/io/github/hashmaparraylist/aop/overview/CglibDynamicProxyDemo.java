package io.github.hashmaparraylist.aop.overview;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * CGLIB 动态代理示例
 *
 * @author
 * @date 2021/1/12
 */
public class CglibDynamicProxyDemo {
    public static void main(String[] args) {
        Enhancer enhancer = new Enhancer();
        // 指定 super class = DefaultEchoService.class
        Class<?> superClass = DefaultEchoService.class;
        enhancer.setSuperclass(superClass);
        // 指定拦截接口
        enhancer.setInterfaces(new Class[] {EchoService.class});
        enhancer.setCallback(new MethodInterceptor() {
            @Override
            public Object intercept(Object source, Method method, Object[] args,
                                    MethodProxy methodProxy) throws Throwable {
                long startTime = System.currentTimeMillis();
                // Source -> CGLIB 子类
                // 目标类 -> DefaultEchoService
//                Object result = method.invoke(source, args);
                Object result = methodProxy.invokeSuper(source, args);
                long costTime = System.currentTimeMillis() - startTime;
                System.out.println("[CGLIB 字节码] echo 方法执行的时间: " + costTime + " ms.");
                return result;
            }
        });

        // 创建代理对象
        EchoService echoService = (EchoService) enhancer.create();
        System.out.println(echoService.echo("Hello, world!"));
    }
}
