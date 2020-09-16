package io.github.hashmaparraylist.configuration.metadata;

import org.springframework.beans.factory.config.YamlMapFactoryBean;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;

import java.util.Map;

/**
 * 基于 XML 资源的 YAML 外部化配置
 *
 * @author
 * @date 2020/9/16
 */
public class XmlBasedYamlPropertySourceDemo {

    public static void main(String[] args) {
        // 创建 IoC 底层容器
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        // 创建 Xml 资源的 BeanDefinitionReader
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        // 读取 Xml 资源
        reader.loadBeanDefinitions("classpath:/META-INF/yaml-property-source-context.xml");

        // 获取 User 对象
        Map<String, Object> yamlMap = beanFactory.getBean("yamlMap", Map.class);

        System.out.println(yamlMap);
    }
}
