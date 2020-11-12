package io.github.hashmaparraylist.annotation;

import java.lang.annotation.*;

/**
 * 自定义 应用注解
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@MyComponent2
@MyConfiguration(name = "my-application")
public @interface MyApplication {
}
