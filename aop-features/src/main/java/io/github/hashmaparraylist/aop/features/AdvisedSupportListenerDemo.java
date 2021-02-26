package io.github.hashmaparraylist.aop.features;

import io.github.hashmaparraylist.aop.features.Interceptor.EchoServiceMethodInterceptor;
import io.github.hashmaparraylist.aop.overview.DefaultEchoService;
import io.github.hashmaparraylist.aop.overview.EchoService;
import org.aopalliance.aop.Advice;
import org.springframework.aop.framework.AdvisedSupport;
import org.springframework.aop.framework.AdvisedSupportListener;
import org.springframework.aop.framework.ProxyCreatorSupport;
import org.springframework.aop.framework.ProxyFactory;

/**
 * {@link AdvisedSupportListener} 示例
 *
 * @author
 * @date 2021/2/26
 * @see ProxyCreatorSupport
 */
public class AdvisedSupportListenerDemo {

    public static void main(String[] args) {
        DefaultEchoService defaultEchoService = new DefaultEchoService();
        // 注入目标对象 (被代理)
        ProxyFactory proxyFactory = new ProxyFactory(defaultEchoService);
        proxyFactory.setTarget(DefaultEchoService.class);
        // 添加 Advice 实现 MethodInterceptor < Interceptor < Advice
        proxyFactory.addAdvice(new EchoServiceMethodInterceptor());
        proxyFactory.addListener(new AdvisedSupportListener() {
            @Override
            public void activated(AdvisedSupport advised) {
                System.out.println("AOP 配置对象 : " + advised + " 已激活");
            }

            @Override
            public void adviceChanged(AdvisedSupport advised) {
                System.out.println("AOP 配置对象 : " + advised + " 已变化");
            }
        });
        // 获取代理对象
        // 激活事件触发 createAopProxy() <- getProxy()
        EchoService echoService = (EchoService) proxyFactory.getProxy();
        proxyFactory.addAdvice(new Advice() {
        });
    }
}
