package io.github.hashmaparraylist.annotation;

import java.lang.annotation.*;

/**
 * 配置注解
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyConfiguration {

    String name();
}
