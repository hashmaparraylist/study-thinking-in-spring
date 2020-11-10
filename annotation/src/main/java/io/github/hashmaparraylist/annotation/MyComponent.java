package io.github.hashmaparraylist.annotation;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * 自定义 {@link Component} 注解
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface MyComponent {
}
