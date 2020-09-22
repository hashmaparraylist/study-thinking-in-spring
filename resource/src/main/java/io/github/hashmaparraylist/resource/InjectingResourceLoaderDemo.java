package io.github.hashmaparraylist.resource;

import io.github.hashmaparraylist.resource.util.ResourceUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import javax.annotation.PostConstruct;
import javax.swing.*;
import java.util.stream.Stream;

/**
 * 注入 {@link ResourceLoader} 对象示例
 *
 * @author
 * @date 2020/9/22
 *
 * @see Resource
 * @see Value
 * @see AnnotationConfigApplicationContext
 * @see ResourceLoader
 */
class InjectingResourceLoaderDemo implements ResourceLoaderAware {

    private ResourceLoader resourceLoader;

    @Autowired
    private ResourceLoader autowiredResourceLoader;

    @Autowired
    private AbstractApplicationContext applicationContext;

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    @PostConstruct
    public void init() {
        System.out.println("resourceLoader == autowiredResourceLoader : " + (this.resourceLoader == this.autowiredResourceLoader));
        System.out.println("resourceLoader == applicationContext : " + (this.resourceLoader == this.applicationContext));
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(InjectingResourceLoaderDemo.class);

        context.refresh();

        context.close();
    }

}
