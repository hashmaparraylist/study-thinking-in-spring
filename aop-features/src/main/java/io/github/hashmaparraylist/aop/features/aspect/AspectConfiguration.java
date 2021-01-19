package io.github.hashmaparraylist.aop.features.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

/**
 * TODO
 *
 * @author
 * @date 2021/1/15
 */
@Aspect
public class AspectConfiguration {

    @Pointcut("execution(public * *(..))")
    private void anyPublicMethod() {
        System.out.println("@Pointcut any public method.");
    }

    @Before("anyPublicMethod()")
    public void beforeAnyPublicMethod() {
        System.out.println("@Before any public method");
    }

}
