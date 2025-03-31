package org.milan.reflection;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Proxy;

/**
 * @author Milan Rathod
 */
public class DefaultMethodDemo {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        final Object proxyInstance = Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(), new Class[]{HelloWorld.class}, (proxy, method, args1) -> {
            if (method.isDefault()) {
                return InvocationHandler.invokeDefault(proxy, method, args1);
            }
            return proxy;
        });
        System.out.println(proxyInstance.getClass().getMethod("hello").invoke(proxyInstance));
    }
}

interface HelloWorld {
    default String hello() {
        return "Hello World";
    }
}
