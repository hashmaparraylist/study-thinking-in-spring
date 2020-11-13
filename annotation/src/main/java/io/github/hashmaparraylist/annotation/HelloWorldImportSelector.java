package io.github.hashmaparraylist.annotation;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * HelloWorld 模块 {@link ImportSelector} 实现
 *
 * @author
 * @date 2020/11/12
 * @see ImportSelector
 */
public class HelloWorldImportSelector implements ImportSelector {
    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        return new String[] {"io.github.hashmaparraylist.annotation.HelloWorldConfiguration"};
    }
}
