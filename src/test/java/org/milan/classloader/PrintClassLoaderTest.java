package org.milan.classloader;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

/**
 * @author Milan Rathod
 */
class PrintClassLoaderTest {

    @Test
    @Disabled
    void givenAppClassLoader_whenParentClassLoader_thenClassNotFoundException() throws Exception {
        PrintClassLoader printClassLoader = (PrintClassLoader) Class.forName(PrintClassLoader.class.getName()).newInstance();

        printClassLoader.print();

        Class.forName(PrintClassLoader.class.getName(), true, PrintClassLoader.class.getClassLoader().getParent());
    }

}