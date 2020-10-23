package io.github.hashmaparraylist.generic;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.stream.Stream;

/**
 * Java 泛型 API 示例
 *
 * @author
 * @date 2020/10/21
 */
public class GenericAPIDemo {
    public static void main(String[] args) {
        // 原生类型 primitive types: int long float
        Class intClass = int.class;

        // 数组类型 array types: int[], Object[]
        Class objectArrayClass = Object[].class;

        // 原始类型 raw types: java.lang.String
        Class rawClass = String.class;

        // 泛型参数类型 parameterized type
        ParameterizedType parameterizedType = (ParameterizedType) ArrayList.class.getGenericSuperclass();
        System.out.println(parameterizedType.toString());

        // 泛型类型变量 Type Variable
        Type[] typeVariable = parameterizedType.getActualTypeArguments();
        Stream.of(typeVariable)
                .map(TypeVariable.class::cast) // Type -> TypeVariable
                .forEach(System.out::println);

    }
}
