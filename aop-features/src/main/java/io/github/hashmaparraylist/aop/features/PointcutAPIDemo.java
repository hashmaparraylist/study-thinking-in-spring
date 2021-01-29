package io.github.hashmaparraylist.aop.features;

import io.github.hashmaparraylist.aop.features.Interceptor.EchoServiceMethodInterceptor;
import io.github.hashmaparraylist.aop.features.pointcut.EchoServiceEchoMethodPointcut;
import io.github.hashmaparraylist.aop.features.pointcut.EchoServicePointcut;
import io.github.hashmaparraylist.aop.overview.DefaultEchoService;
import io.github.hashmaparraylist.aop.overview.EchoService;
import org.springframework.aop.Pointcut;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.ComposablePointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;

/**
 * TODO
 *
 * @author
 * @date 2021/1/19
 */
public class PointcutAPIDemo {
    public static void main(String[] args) {
        EchoServicePointcut pointcut = new EchoServicePointcut("echo", EchoService.class);

        ComposablePointcut pointcuts = new ComposablePointcut(EchoServiceEchoMethodPointcut.INSTANCE);
        // 组合实现
        pointcuts.intersection(pointcut.getClassFilter());
        pointcuts.intersection(pointcut.getMethodMatcher());

        // 将 Pointcut 适配成 Advisor
        DefaultPointcutAdvisor advisor =
                new DefaultPointcutAdvisor(pointcuts, new EchoServiceMethodInterceptor());

        DefaultEchoService defaultEchoService = new DefaultEchoService();
        ProxyFactory proxyFactory =new ProxyFactory(defaultEchoService);
        // 添加 Advisor
        proxyFactory.addAdvisor(advisor);

        // 获取代理对象
        EchoService echoService = (EchoService) proxyFactory.getProxy();
        System.out.println(echoService.echo("Hello, World"));

    }
}
