package io.github.hashmaparraylist.configuration.metadata;

import io.github.hashmaparraylist.ioc.overview.domain.User;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.AbstractSingleBeanDefinitionParser;
import org.springframework.beans.factory.xml.ParserContext;
import org.springframework.util.StringUtils;
import org.w3c.dom.Element;

/**
 * "user" 元素的 {@link org.springframework.beans.factory.xml.BeanDefinitionParser} 的实现
 *
 * @author
 * @date 2020/9/15
 */
public class UserBeanDefinitionParser extends AbstractSingleBeanDefinitionParser {

    @Override
    protected Class<?> getBeanClass(Element element) {
        return User.class;
    }

    @Override
    protected void doParse(Element element, ParserContext parserContext, BeanDefinitionBuilder beanDefinitionBuilder) {
        this.setPropertyValue(element, beanDefinitionBuilder, "id");
        this.setPropertyValue(element, beanDefinitionBuilder, "name");
        this.setPropertyValue(element, beanDefinitionBuilder, "city");
    }

    private void setPropertyValue(Element element, BeanDefinitionBuilder builder, String attributeName) {
        String attributeValue = element.getAttribute(attributeName);
        if (StringUtils.hasText(attributeValue)) {
            builder.addPropertyValue(attributeName, attributeValue);
        }
    }

}
