package io.github.hashmaparraylist.dependency.lookup;

import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * {@link org.springframework.beans.factory.NoUniqueBeanDefinitionException} 的示例
 *
 * @author
 * @date 2020/8/7
 */
public class NoUniqueBeanDefinitionExceptionDemo {
    public static void main(String[] args) {
        // 创建 BeanFactory 容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        // 注解 Configuration Class 配置类
        applicationContext.register(NoUniqueBeanDefinitionExceptionDemo.class);
        // 启动 Spring 应用上下文
        applicationContext.refresh();

        try {
            // 由于包含2个 String 类型的 Bean
            applicationContext.getBean(String.class);
        } catch (NoUniqueBeanDefinitionException e) {
            System.err.printf("当前 Spring 应用上下文存在 %d 个 %s 类型的 Bean, 具体原因: %s \n", e.getNumberOfBeansFound(), String.class.getName(), e.getMessage());
        }

        // 关闭容器
        applicationContext.close();
    }

    @Bean
    public String bean1() {
        return "1";
    }

    @Bean
    public String bean2() {
        return "2";
    }

    @Bean
    public String bean3() {
        return "3";
    }
}
