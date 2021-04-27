package io.github.hashmaparraylist.aop.features.event;

import org.springframework.aop.Pointcut;
import org.springframework.aop.PointcutAdvisor;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.StaticMethodMatcherPointcut;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.event.EventListener;
import org.springframework.context.event.EventPublicationInterceptor;

import java.lang.reflect.Method;

/**
 * {@link EventPublicationInterceptor} 示例
 *
 */
@Configuration
@EnableAspectJAutoProxy
public class EventPublicationInterceptorDemo {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(EventPublicationInterceptorDemo.class, Executor.class, StaticExecutor.class);
        applicationContext.refresh();

        // 5. 执行目标方法
        Executor executor = applicationContext.getBean(Executor.class);
        StaticExecutor staticExecutor = applicationContext.getBean(StaticExecutor.class);

        executor.execute();
        staticExecutor.execute();

        applicationContext.close();
    }

    // 1. 将 EventPublicationInterceptor 声明为 Spring Bean
    @Bean
    public static EventPublicationInterceptor eventPublicationInterceptor() {
        EventPublicationInterceptor eventPublicationInterceptor = new EventPublicationInterceptor();

        // 关联目标 (自定义) 事件类型 - ExecuteEvent
        eventPublicationInterceptor.setApplicationEventClass(ExecutedEvent.class);
        return eventPublicationInterceptor;
    }

    // 2. 实现一个 PointCut
    @Bean
    public static Pointcut pointcut() {
        return new StaticMethodMatcherPointcut() {
            @Override
            public boolean matches(Method method, Class<?> targetClass) {
                return "execute".equals(method.getName()) && Executor.class.equals(targetClass);
            }
        };
    }

    // 3. 声明一个 Advisor Bean
    @Bean
    public static PointcutAdvisor pointcutAdvisor(Pointcut pointcut, EventPublicationInterceptor eventPublicationInterceptor) {
        // EventPulicationInterceptor is MethodInterceptor is Advice
        return new DefaultPointcutAdvisor(pointcut, eventPublicationInterceptor);
    }

    // 4. 处理事件 - ExecuteEvent
    @EventListener(ExecutedEvent.class)
    public void executed(ExecutedEvent event) {
        System.out.println("Executed : " + event);
    }
}
