package io.github.hashmaparraylist.ioc.overview.domain;

import io.github.hashmaparraylist.ioc.overview.annotation.Super;

/**
 * @author
 */
@Super
public class SuperUser extends User {
    private String address;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "SuperUser{" +
                "address='" + address + '\'' +
                "} " + super.toString();
    }
}
