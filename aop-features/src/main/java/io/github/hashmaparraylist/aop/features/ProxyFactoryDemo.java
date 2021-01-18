package io.github.hashmaparraylist.aop.features;

import io.github.hashmaparraylist.aop.features.Interceptor.EchoServiceMethodInterceptor;
import io.github.hashmaparraylist.aop.overview.DefaultEchoService;
import io.github.hashmaparraylist.aop.overview.EchoService;
import org.springframework.aop.framework.ProxyFactory;

/**
 * TODO
 *
 * @author
 * @date 2021/1/18
 */
public class ProxyFactoryDemo {
    public static void main(String[] args) {
        DefaultEchoService defaultEchoService = new DefaultEchoService();
        // 注入目标对象 (被代理)
        ProxyFactory proxyFactory = new ProxyFactory(defaultEchoService);
        proxyFactory.setTarget(DefaultEchoService.class);
        // 添加 Advice 实现 MethodInterceptor < Interceptor < Advice
        proxyFactory.addAdvice(new EchoServiceMethodInterceptor());
        // 获取代理对象
        EchoService echoService = (EchoService) proxyFactory.getProxy();

        System.out.println(echoService.echo("Hello, world!"));
    }
}
