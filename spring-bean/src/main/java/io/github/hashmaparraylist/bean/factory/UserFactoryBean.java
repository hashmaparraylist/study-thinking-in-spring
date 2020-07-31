package io.github.hashmaparraylist.bean.factory;

import io.github.hashmaparraylist.ioc.overview.domain.User;
import org.springframework.beans.factory.FactoryBean;

/**
 * (@link User) Bean 的 (@link org.springframework.bean.factory.FactoryBean) 实现
 *
 * @author
 */
public class UserFactoryBean implements FactoryBean {
    @Override
    public Object getObject() throws Exception {
        return User.createUser();
    }

    @Override
    public Class<?> getObjectType() {
        return User.class;
    }
}
