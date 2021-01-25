package io.github.hashmaparraylist.aop.features;

import io.github.hashmaparraylist.aop.features.aspect.AspectConfiguration;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.aop.aspectj.annotation.AspectJProxyFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 *
 *
 * @author
 * @date 2021/1/15
 */
public class AspectJAnnotationUsingAPIDemo {
    public static void main(String[] args) {
        // 创建一个 HashMap 缓存, 作为被代理对象
        Map<String, Object> cache = new HashMap<>();
        // 创建 ProxyFactory (AspectJ)
        AspectJProxyFactory proxyFactory = new AspectJProxyFactory(cache);
        // 增加 Aspect 配置类
        proxyFactory.addAspect(AspectConfiguration.class);

        proxyFactory.addAdvice(new MethodBeforeAdvice() {
            @Override
            public void before(Method method, Object[] args, Object target) throws Throwable {
                if ("put".equals(method.getName()) && args.length == 2) {
                    System.out.printf("[MethodBeforeAdvice] 当前存放是 Key : %s , Value : %s \n", args[0], args[1]);
                }
            }
        });

        // 添加 AfterReturningAdvice
        proxyFactory.addAdvice(new AfterReturningAdvice() {
            @Override
            public void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable {
                if ("put".equals(method.getName()) && args.length == 2) {
                    System.out.printf("[AfterReturningAdvice] 当前存放是 Key : %s , 新存放的 Value : %s , 之前关联的 Value : %s \n",
                            args[0],        // key
                            args[1],        // new value
                            returnValue     // old value
                    );
                }
            }
        });

        // 存储数据
        // cache.put("1", "A");
        // 通过代理对象存储
        Map<String, Object> proxy = proxyFactory.getProxy();
        proxy.put("1", "A");
        proxy.put("1", "B");
        System.out.println(cache.get("1"));
    }
}
