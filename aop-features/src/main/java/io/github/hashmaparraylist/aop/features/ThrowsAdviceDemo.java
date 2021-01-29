package io.github.hashmaparraylist.aop.features;

import io.github.hashmaparraylist.aop.overview.ExceptionInterceptor;
import org.springframework.aop.ThrowsAdvice;
import org.springframework.aop.framework.ProxyFactory;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Random;

/**
 * TODO
 *
 * @author
 * @date 2021/1/29
 */
public class ThrowsAdviceDemo {
    public static void main(String[] args) {
        ThrowsAdviceDemo instance = new ThrowsAdviceDemo();
        ProxyFactory proxyFactory = new ProxyFactory(instance);

        proxyFactory.addAdvice(new MyThrowAdvice());

        ThrowsAdviceDemo proxy = (ThrowsAdviceDemo) proxyFactory.getProxy();
        proxy.execute();
        proxy.execute();
    }

    public void execute() {
        Random random = new Random();
        if (random.nextBoolean()) {
            throw new RuntimeException("For purpose");
        }
        System.out.println("Executing...");
    }
}
