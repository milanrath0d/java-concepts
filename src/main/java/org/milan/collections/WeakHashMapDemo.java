package org.milan.collections;

import java.util.WeakHashMap;

/**
 * Example of {@link java.util.WeakHashMap}
 *
 * @author Milan Rathod
 */
public class WeakHashMapDemo {

    public static void main(String[] args) {
        WeakHashMap<Integer, String> map = new WeakHashMap<>();

        Integer i = 129;

        map.put(i, "test");
        map.put(120, "test2");

        System.out.println(map.size());

        i = null;

        System.gc();

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(map.size());
    }

}
