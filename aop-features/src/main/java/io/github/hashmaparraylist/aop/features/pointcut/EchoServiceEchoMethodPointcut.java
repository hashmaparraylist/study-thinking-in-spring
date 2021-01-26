package io.github.hashmaparraylist.aop.features.pointcut;


import io.github.hashmaparraylist.aop.overview.EchoService;
import org.springframework.aop.ClassFilter;
import org.springframework.aop.MethodMatcher;
import org.springframework.aop.Pointcut;

import java.lang.reflect.Method;
import java.util.Objects;

/**
 * TODO
 *
 * @author
 * @date 2021/1/26
 */
public class EchoServiceEchoMethodPointcut implements Pointcut {
    public static final EchoServiceEchoMethodPointcut INSTANCE = new EchoServiceEchoMethodPointcut();

    private EchoServiceEchoMethodPointcut() {
    }

    @Override
    public ClassFilter getClassFilter() {
        return new ClassFilter() {
            @Override
            public boolean matches(Class<?> clazz) {
                return EchoService.class.isAssignableFrom(clazz); // 凡是 EchoService 接口或是子接口，子类均可
            }
        };
    }

    @Override
    public MethodMatcher getMethodMatcher() {
        return new MethodMatcher() {
            @Override
            public boolean matches(Method method, Class<?> targetClass) { // echo(String)
                return "echo".equals(method.getName())
                        && method.getParameterTypes().length == 1
                        && Objects.equals(String.class, method.getParameterTypes()[0]);
            }

            @Override
            public boolean isRuntime() {
                return false;
            }

            @Override
            public boolean matches(Method method, Class<?> targetClass, Object... args) {
                return false;
            }
        };
    }
}
