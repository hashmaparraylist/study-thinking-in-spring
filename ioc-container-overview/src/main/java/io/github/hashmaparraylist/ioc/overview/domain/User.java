package io.github.hashmaparraylist.ioc.overview.domain;

import io.github.hashmaparraylist.ioc.overview.enums.City;
import org.springframework.core.io.Resource;

/**
 *
 */
public class User {
    private String name;
    private Long id;
    private City city;
    private Resource configFileLocation;

    public Resource getConfigFileLocation() {
        return configFileLocation;
    }

    public void setConfigFileLocation(Resource configFileLocation) {
        this.configFileLocation = configFileLocation;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", city=" + city +
                ", configFileLocation=" + configFileLocation +
                '}';
    }

    public static User createUser() {
        User user = new User();
        user.setId(2L);
        user.setName("createUser");
        user.setCity(City.BEIJING);
        return user;
    }
}
