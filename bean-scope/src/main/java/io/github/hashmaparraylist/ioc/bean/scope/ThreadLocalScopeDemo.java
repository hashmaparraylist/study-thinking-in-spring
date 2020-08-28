package io.github.hashmaparraylist.ioc.bean.scope;

import io.github.hashmaparraylist.ioc.overview.domain.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

import static io.github.hashmaparraylist.ioc.bean.scope.ThreadLocalScope.SCOPE_NAME;

/**
 * 自定义 Scope {@link ThreadLocalScope} 示例
 *
 * @author
 * @date 2020/8/28
 */
public class ThreadLocalScopeDemo {

    @Bean
    @Scope(SCOPE_NAME)
    public User user() {
        return createUser();
    }

    public static User createUser() {
        User user = new User();
        user.setId(System.nanoTime());
        return user;
    }


    public static void main(String[] args) {
        // 创建 BeanFactory 容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        // 注解 Configuration Class 配置类
        applicationContext.register(ThreadLocalScopeDemo.class);

        applicationContext.addBeanFactoryPostProcessor(beanFactory -> {
            // 注册自定义 Scope
            beanFactory.registerScope(SCOPE_NAME, new ThreadLocalScope());
        });

        // 启动 Spring 应用上下文
        applicationContext.refresh();

        scopedBeanByLookup(applicationContext);

        // 关闭容器
        applicationContext.close();
    }

    private static void scopedBeanByLookup(ApplicationContext applicationContext) {
        for (int i = 0; i < 3; i ++) {
            Thread thread = new Thread(() -> {
                User user = applicationContext.getBean("user", User.class);
                System.out.printf("[Thread id : %d] user = %s%n", Thread.currentThread().getId(), user);
            });

            thread.start();

            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
