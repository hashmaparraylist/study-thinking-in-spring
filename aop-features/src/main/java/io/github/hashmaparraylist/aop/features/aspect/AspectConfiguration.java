package io.github.hashmaparraylist.aop.features.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;

/**
 * TODO
 *
 * @author
 * @date 2021/1/15
 */
@Aspect
@Order
public class AspectConfiguration {

    @Pointcut("execution(public * *(..))")
    private void anyPublicMethod() {
        System.out.println("@Pointcut any public method.");
    }
    @Around("anyPublicMethod()")
    public Object aroundAnyPublicMethod(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("@Around any public method");
        return pjp.proceed();
    }
    @Before("anyPublicMethod()")
    public void beforeAnyPublicMethod() {
        System.out.println("@Before any public method");
    }
}
