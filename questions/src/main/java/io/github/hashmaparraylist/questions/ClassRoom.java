package io.github.hashmaparraylist.questions;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;

/**
 * 教室类
 *
 * @author
 * @date 2020/11/24
 */
public class ClassRoom {
    private String name;
    @Autowired private Collection<Student> students;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Collection<Student> getStudents() {
        return students;
    }

    public void setStudents(Collection<Student> students) {
        this.students = students;
    }

    @Override
    public String toString() {
        return "ClassRoom{" +
                "name='" + name + '\'' +
                ", students=" + students +
                '}';
    }

}
