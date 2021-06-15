package org.milan.classloader;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;

/**
 * Test class for {@link CustomClassLoader}
 *
 * @author Milan Rathod
 */
class CustomClassLoaderTest {

    @Test
    public void customLoader() throws Exception {
        CustomClassLoader customClassLoader = new CustomClassLoader();
        Class<?> c = customClassLoader.findClass(PrintClassLoader.class.getName());

        Object ob = c.newInstance();

        Method md = c.getMethod("print");
        md.invoke(ob);
    }

}