package org.milan.classloader;

import java.util.ArrayList;

/**
 * Example of Class loading in java
 *
 * @author Milan Rathod
 */
public class PrintClassLoader {

    public void print() {
        // Application/system class loader
        System.out.println("Classloader of this class: " + PrintClassLoader.class.getClassLoader());

        // Bootstrap class loader
        System.out.println("Classloader of ArrayList:" + ArrayList.class.getClassLoader());
    }

}
