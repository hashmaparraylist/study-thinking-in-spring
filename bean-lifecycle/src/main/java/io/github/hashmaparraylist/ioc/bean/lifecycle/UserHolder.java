package io.github.hashmaparraylist.ioc.bean.lifecycle;

import io.github.hashmaparraylist.ioc.overview.domain.User;

/**
 * TODO
 *
 * @author
 * @date 2020/9/2
 */
public class UserHolder {
    private final User user;
    private Integer number;
    private String description;

    public UserHolder(User user) {
        this.user = user;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "UserHolder{" +
                "user=" + user +
                ", number=" + number +
                ", description='" + description + '\'' +
                '}';
    }
}
