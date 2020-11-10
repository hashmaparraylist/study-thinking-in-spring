package io.github.hashmaparraylist.annotation;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * 自定义 Component 派生注解
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@MyComponent
public @interface MyComponent2 {
}
