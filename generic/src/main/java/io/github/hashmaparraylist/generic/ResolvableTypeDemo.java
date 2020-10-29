package io.github.hashmaparraylist.generic;

import org.springframework.core.ResolvableType;

/**
 * {@link ResolvableType} 示例
 *
 * @author
 * @date 2020/10/29
 * @see ResolvableType
 */
public class ResolvableTypeDemo {
    public static void main(String[] args) {
        // 工厂创建
        // StringList <- ArrayList <- AbstractList <- List <- Collection
        ResolvableType resolvableType = ResolvableType.forClass(StringList.class);

        resolvableType.getSuperType(); // ArrayList
        resolvableType.getSuperType().getSuperType(); // AbstractList

        System.out.println(resolvableType.asCollection().resolve());    // raw type
        System.out.println(resolvableType.asCollection().getGeneric(0));    // 获取泛型参数类型
    }
}
