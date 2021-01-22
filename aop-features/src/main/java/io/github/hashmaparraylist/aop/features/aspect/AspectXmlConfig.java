package io.github.hashmaparraylist.aop.features.aspect;

import org.aspectj.lang.ProceedingJoinPoint;

/**
 * TODO
 *
 * @author
 * @date 2021/1/19
 */
public class AspectXmlConfig {
    public void beforeAnyPublicMethod() {
        System.out.println("@Before any public method");
    }

    public Object aroundAnyPublicMethod(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("Around any public method : " + pjp.getSignature());
        return pjp.proceed();
    }
}
