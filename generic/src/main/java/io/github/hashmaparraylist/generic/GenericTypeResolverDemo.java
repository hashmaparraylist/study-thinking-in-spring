package io.github.hashmaparraylist.generic;

import org.springframework.core.GenericTypeResolver;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.springframework.core.GenericTypeResolver.*;

/**
 * {@link GenericTypeResolver} 示例
 *
 * @author
 * @date 2020/10/23
 * @see GenericTypeResolver
 */
public class GenericTypeResolverDemo {

    public static void main(String[] args) throws NoSuchMethodException {
        // String 是 Comparable<String> 的具体化
        displayReturnTypeGenericInfo(GenericTypeResolverDemo.class, Comparable.class, "getString");
        // List<Object> 是 List 泛型参数类型的具体化
        displayReturnTypeGenericInfo(GenericTypeResolverDemo.class, List.class, "getList");
        // StringList 是 List 泛型参数类型的具体化
        displayReturnTypeGenericInfo(GenericTypeResolverDemo.class, List.class, "getStringList");

        // 具备 ParameterizedType 返回，否则 null

        // TypeVariable
        Map<TypeVariable, Type> typeVariableMap = GenericTypeResolver.getTypeVariableMap(StringList.class);
        System.out.println(typeVariableMap);
    }

    public static StringList getStringList() {
        return null;
    }

    public static List<Object> getList() {
        return null;
    }

    public static String getString() {
        return null;
    }

    private static void displayReturnTypeGenericInfo(Class<?> containingClass, Class<?> genericIfc, String methodName, Class... argumentType) throws NoSuchMethodException {
        Method method = containingClass.getMethod(methodName);

        // 声明类 GenericTypeResolverDemo.class
        Class<?> returnType = resolveReturnType(method, containingClass);

        // 常规类作为方法返回值
        System.out.printf("GenericTypeResolver.resolveReturnType(%s, %s) = %s\n", methodName, containingClass.getSimpleName(), returnType);
        // 常规类型不具备泛型参数类型
        Class<?> returnTypeArgument = resolveReturnTypeArgument(method, genericIfc);
        System.out.printf("GenericTypeResolver.resolveReturnTypeArgument(%s, %s) = %s\n", methodName, containingClass.getSimpleName(), returnTypeArgument);
    }

    static class StringList extends ArrayList<String> {     // 泛型参数具体化

    }
}
