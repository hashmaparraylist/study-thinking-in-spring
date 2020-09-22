package io.github.hashmaparraylist.resource;

import io.github.hashmaparraylist.resource.util.ResourceUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.io.Resource;

import javax.annotation.PostConstruct;
import java.util.stream.Stream;

/**
 * 注入 {@link Resource} 对象示例
 *
 * @author
 * @date 2020/9/22
 *
 * @see Resource
 * @see Value
 * @see AnnotationConfigApplicationContext
 */
class InjectingResourceDemo {

    @Value("classpath:/META-INF/default.properties")
    private Resource resource;

    @Value("${user.dir}")
    private String currentProjectBasePath;

    @Value("classpath*:/META-INF/*.properties")
    private Resource[] resources;

    @PostConstruct
    public void init() {
        System.out.println(ResourceUtils.getContent(this.resource));
        System.out.println(currentProjectBasePath);
        Stream.of(resources).map(ResourceUtils::getContent).forEach(System.out::println);
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(InjectingResourceDemo.class);

        context.refresh();

        context.close();
    }
}
