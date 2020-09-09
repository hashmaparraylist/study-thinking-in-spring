package io.github.hashmaparraylist.configuration.metadata;

import io.github.hashmaparraylist.ioc.overview.domain.User;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.util.ObjectUtils;

/**
 * Bean 配置元信息示例
 *
 * @author
 * @date 2020/9/9
 */
public class BeanConfigurationMetadataDemo {
    public static void main(String[] args) {
        // BeanDefinition 的定义
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(User.class);
        beanDefinitionBuilder.addPropertyValue("name", "addPropertyValue");
        // 获取 AbstractBeanDefinition
        AbstractBeanDefinition beanDefinition = beanDefinitionBuilder.getBeanDefinition();
        // 声明 BeanDefinition
        // 附加属性 (不影响 Bean 的 populate, initialize)
        beanDefinition.setAttribute("name", "setAttribute");
        // 当前 BeanDefinition 来自何方
        beanDefinition.setSource(BeanConfigurationMetadataDemo.class);
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        beanFactory.registerBeanDefinition("user", beanDefinitionBuilder.getBeanDefinition());

        beanFactory.addBeanPostProcessor(new BeanPostProcessor() {
            @Override
            public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
                if ((User.class.equals(bean.getClass())) && ObjectUtils.nullSafeEquals("user", beanName)) {
                    BeanDefinition bd = beanFactory.getBeanDefinition(beanName);
                    if (BeanConfigurationMetadataDemo.class.equals(bd.getSource())) {
                        String name = (String) bd.getAttribute("name");
                        User user = (User) bean;
                        user.setName(name);
                    }
                }

                return bean;
            }
        });

        User user = beanFactory.getBean("user", User.class);
        System.out.println(user);
    }
}
