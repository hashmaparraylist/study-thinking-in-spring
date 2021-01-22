package io.github.hashmaparraylist.aop.features.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;

import java.util.Random;

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
    public void beforeAnyPublicMethod() throws Throwable {
        System.out.println("@Before any public method");
    }

    @After("anyPublicMethod()")
    public void finalizeAnyPublicMethod() {
        System.out.println("@After any public method");
    }

    @AfterReturning("anyPublicMethod()")
    public void afterAnyPublicMethod() {
        System.out.println("@AfterReturning any public method");
    }

    @AfterThrowing("anyPublicMethod()")
    public void afterThrowingAnyPublicMethod() {
        System.out.println("@AfterThrowing any public method");
    }
}
