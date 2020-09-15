package io.github.hashmaparraylist.configuration.metadata;

import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

/**
 * "users.xsd" {@link org.springframework.beans.factory.xml.NamespaceHandler} 的实现
 *
 * @author
 * @date 2020/9/15
 */
public class UsersNamespaceHandler extends NamespaceHandlerSupport {
    @Override
    public void init() {
        // 为 "user" 注册对应的 BeanDefinitionParser
        registerBeanDefinitionParser("user", new UserBeanDefinitionParser());
    }
}
