package io.github.hashmaparraylist.aop.features;

import org.springframework.aop.ThrowsAdvice;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * TODO
 *
 * @author
 * @date 2021/1/29
 */
public class MyThrowAdvice implements ThrowsAdvice {
    public void afterThrowing(Exception ex) {
        System.out.printf("Exception : %s\n", ex);
    }
    public void afterThrowing(Method method, Object[] args, Object target, Exception ex) {
        System.out.printf("Method: %s, args: : %s, target : %s, exception : %s\n",
                method,
                Arrays.asList(args),
                target,
                ex);
    }
}
