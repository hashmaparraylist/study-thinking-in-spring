package io.github.hashmaparraylist.annotation;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;

/**
 * 自定义 {@link ComponentScan}
 *
 * @author
 * @date 2020/11/12
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@MyComponentScan
public @interface MyComponentScan2 {

    @AliasFor(annotation = MyComponentScan.class, attribute = "scanBasePackages") // 隐形别名
            // 多态，子注解提供新的属性方法引用"父"（元）注解中的属性方法
    String[] basePackages() default {};

    // @MyComponentScan2.basePackages
    //  -> @MyComponentScan.scanBasePackages
    //  -> @ComponentScan.basePackages
    //  -> @ComponentScan.value


}
