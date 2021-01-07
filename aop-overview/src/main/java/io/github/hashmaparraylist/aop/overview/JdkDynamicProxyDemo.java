package io.github.hashmaparraylist.aop.overview;

import java.lang.reflect.Proxy;

/**
 * JDK 动态代理
 *
 * @author
 * @date 2021/1/7
 */
public class JdkDynamicProxyDemo {

    public static void main(String[] args) {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        Object target = Proxy.newProxyInstance(classLoader, new Class[]{EchoService.class}, (proxy, method, methodArgs) -> {
            if (EchoService.class.isAssignableFrom(method.getDeclaringClass())) {
                ProxyEchoService echoService = new ProxyEchoService(new DefaultEchoService());
                return echoService.echo((String) methodArgs[0]);
            }
            return null;
        });

        EchoService echoService = (EchoService) target;
        echoService.echo("Hello, World");
    }
}
