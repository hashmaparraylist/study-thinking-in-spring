package io.github.hashmaparraylist.dependency.injection.annotation;

import java.lang.annotation.*;

/**
 * 自定义依赖注入， 仅关注{@link io.github.hashmaparraylist.ioc.overview.domain.User} 的注入
 *
 * @author
 * @date
 */
@Target({ElementType.CONSTRUCTOR, ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface InjectUser {
}
