package io.github.hashmaparraylist.aop.overview;

import java.lang.reflect.Proxy;

/**
 * JDK 动态代理
 *
 * @author
 * @date 2021/1/7
 */
public class JdkDynamicProxyDemo {

    // class $Proxy 0 implements EchoService {}

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

        // $Proxy1
        Object proxy2 = Proxy.newProxyInstance(classLoader, new Class[] {Comparable.class}, (proxy1, method1, args1) -> {
            return null;
        });

        System.out.println(proxy2);
    }
}
