package io.github.hashmaparraylist.ioc.bean.lifecycle;

import io.github.hashmaparraylist.ioc.overview.domain.SuperUser;
import io.github.hashmaparraylist.ioc.overview.domain.User;
import org.springframework.beans.BeansException;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.PropertyValue;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.beans.factory.support.BeanDefinitionReader;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.util.ObjectUtils;

/**
 * Bean 实例化生命周期示例
 *
 * @author
 * @date 2020/9/2
 */
public class BeanInstantiationLifecycleDemo {
    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        // 添加 BeanPostProcessor 的实现
        beanFactory.addBeanPostProcessor(new MyInstantiationAwareBeanPostProcessor());
        // 基于 XML 资源的 BeanDefinitionReader 的实现
        BeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);
        String[] locations = {"classpath:META-INF/dependency-lookup-context.xml", "classpath:META-INF/bean-constructor-dependency-injection.xml"};
        int beanDefinitionCount = beanDefinitionReader.loadBeanDefinitions(locations);
        System.out.printf("已加载 BeanDefinition 数量: %d%n", beanDefinitionCount);

        User user = beanFactory.getBean("user", User.class);
        System.out.println(user);

        User superUser = beanFactory.getBean("superUser", User.class);
        System.out.println(superUser);

        // 构造器注入是按照类型去选择参数， resolveDependency
        UserHolder userHolder = beanFactory.getBean("userHolder", UserHolder.class);
        System.out.println(userHolder);
    }

    static class MyInstantiationAwareBeanPostProcessor implements InstantiationAwareBeanPostProcessor {
        @Override
        public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
            if (ObjectUtils.nullSafeEquals("superUser", beanName) && SuperUser.class.equals(beanClass)) {
                // 把配置完成 superUser 替换掉
                return new SuperUser();
            }

            return null;
        }

        @Override
        public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
            if (ObjectUtils.nullSafeEquals("user", beanName) && User.class.equals(bean.getClass())) {
                User user = (User) bean;
                user.setId(2L);
                // "user" 对象不允许属性赋值(填入) 配置元信息 → 属性
                return false;
            }
            return true;
        }

        @Override
        public PropertyValues postProcessProperties(PropertyValues pvs, Object bean, String beanName) throws BeansException {
            if (ObjectUtils.nullSafeEquals("userHolder", beanName) && UserHolder.class.equals(bean.getClass())) {
                final MutablePropertyValues propertyValues;
                if (pvs instanceof MutablePropertyValues) {
                    propertyValues = (MutablePropertyValues) pvs;
                } else {
                    propertyValues = new MutablePropertyValues();
                }
                // 把配置完成 superUser 替换掉
                propertyValues.addPropertyValue("number", "1");
                if (propertyValues.contains("description")) {
                    // PropertyValue 是只读的属性
//                    PropertyValue propertyValue = propertyValues.getPropertyValue("description");
                    propertyValues.removePropertyValue("description");
                    propertyValues.addPropertyValue("description", "The user holder V2.");
                }
                return propertyValues;
            }

            return null;
        }
    }
}
