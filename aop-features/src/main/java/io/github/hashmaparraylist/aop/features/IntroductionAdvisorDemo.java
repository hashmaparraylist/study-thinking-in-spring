package io.github.hashmaparraylist.aop.features;

import io.github.hashmaparraylist.aop.overview.EchoService;
import org.springframework.aop.IntroductionInfo;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultIntroductionAdvisor;

import java.lang.reflect.Method;

/**
 * {@link org.springframework.aop.IntroductionAdvisor}
 *
 * @author
 * @date 2021/2/18
 */
public class IntroductionAdvisorDemo implements EchoService,Comparable<IntroductionAdvisorDemo> {

    public static void main(String[] args) {
        IntroductionAdvisorDemo target = new IntroductionAdvisorDemo();

        // 使用该狗在前会是的 IntroductionInfo 失效
        // ProxyFactory proxyFactory = new ProxyFactory(target);
        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.setTarget(target);

        // 添加 IntroductionAdvisor
        proxyFactory.addAdvisor(new DefaultIntroductionAdvisor(new MethodBeforeAdvice() {
            @Override
            public void before(Method method, Object[] args, Object target) throws Throwable {
                System.out.println("BeforeAdvice : " + method);
            }
        }, new IntroductionInfo() {
            @Override
            public Class<?>[] getInterfaces() {
                return new Class[] {EchoService.class};
            }
        }));

        Object proxy = proxyFactory.getProxy();
        EchoService echoService = (EchoService) proxy;

        echoService.echo("Hello, world.");
        Comparable comparable = (Comparable) proxy;
        comparable.compareTo(null);
    }

    @Override
    public String echo(String message) throws NullPointerException {
        return "IntroductionAdvisorDemo : " + message;
    }

    @Override
    public int compareTo(IntroductionAdvisorDemo o) {
        return 0;
    }
}
