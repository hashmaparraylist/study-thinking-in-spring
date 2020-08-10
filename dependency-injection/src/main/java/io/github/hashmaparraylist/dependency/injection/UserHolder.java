package io.github.hashmaparraylist.dependency.injection;

import io.github.hashmaparraylist.ioc.overview.domain.User;

/**
 * TODO
 *
 * @author
 * @date 2020/8/10
 */
public class UserHolder {
    private User user;

    public UserHolder() {
    }

    public UserHolder(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "UserHolder{" +
                "user=" + user +
                '}';
    }
}
