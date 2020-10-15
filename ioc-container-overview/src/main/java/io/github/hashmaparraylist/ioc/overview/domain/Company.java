package io.github.hashmaparraylist.ioc.overview.domain;

/**
 * 公司
 *
 * @author
 * @date 2020/10/14
 */
public class Company {
    String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Company{" +
                "name='" + name + '\'' +
                '}';
    }
}
