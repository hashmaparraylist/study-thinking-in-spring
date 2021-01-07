package io.github.hashmaparraylist.aop.overview;

import org.springframework.util.ReflectionUtils;
import sun.reflect.Reflection;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * AOP 目标过滤示例
 *
 * @author
 * @date 2021/1/7
 */
public class TargetFilterDemo {

    public static void main(String[] args) throws ClassNotFoundException {
        String targetClassName = "io.github.hashmaparraylist.aop.overview.EchoService";

        // 获取当前 ClassLoader
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        // 获取目标类
        Class<?> targetClass = classLoader.loadClass(targetClassName);
        // 方法定义: String echo(String name)
        // Spring 反射工具类
        Method targetMethod = ReflectionUtils.findMethod(targetClass, "echo", String.class);
        System.out.println(targetMethod);

        // 查找方法 throws 类型为 NullPointerException
        ReflectionUtils.doWithMethods(targetClass, method -> System.out.println("仅抛出 NullPointerException 方法为: " + method), method -> {
            Class[] exceptionTypes = method.getExceptionTypes();
            Class[] parameterTypes = method.getParameterTypes();
            return parameterTypes.length == 1
                    && String.class.equals(parameterTypes[0])
                    && exceptionTypes.length == 1
                    && NullPointerException.class.equals(exceptionTypes[0]);
        });
    }
}
