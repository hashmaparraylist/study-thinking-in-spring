package io.github.hashmaparraylist.aop.features.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.Ordered;

/**
 * TODO
 *
 * @author
 * @date 2021/1/15
 */
@Aspect
public class AspectConfiguration2 implements Ordered {

    @Before("execution(public * *(..))")
    public void beforeAnyPublicMethod2() {
        System.out.println("@Before any public method (2)");
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
