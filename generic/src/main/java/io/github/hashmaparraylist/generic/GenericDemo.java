package io.github.hashmaparraylist.generic;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Java 泛型示例
 *
 * @author
 * @date 2020/10/21
 */
public class GenericDemo {
    public static void main(String[] args) {
        // Java 7 Diamond 语法
        Collection<String> list = new ArrayList<>();
        list.add("Hello");
        list.add("World");
        // 编译错误
        // list.add(1);

        // 泛型擦写
        Collection tmp = list;
        // 编译正确
        tmp.add(1);

        System.out.println(list);
    }
}
