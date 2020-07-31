package io.github.hashmaparraylist.bean.factory;

import io.github.hashmaparraylist.ioc.overview.domain.User;

/**
 *
 * @author
 */
public interface UserFactory {
    default User create() {
        return User.createUser();
    }
}
