package org.milan.collections;

import java.util.NavigableMap;
import java.util.TreeMap;

/**
 * Example of {@link java.util.NavigableMap} methods
 *
 * @author Milan Rathod
 */
public class NavigableMapDemo {

    public static void main(String[] args) {
        NavigableMap<String, String> navigableMap = new TreeMap<>();

        navigableMap.put("C++", "Good programming language");
        navigableMap.put("Java", "Another good programming language");
        navigableMap.put("Scala", "Another JVM language");
        navigableMap.put("Python", "Language which Google use");

        System.out.println("SortedMap: " + navigableMap);

        System.out.println("Lower Key: " + navigableMap.lowerKey("Java"));

        System.out.println("Lower Key: " + navigableMap.floorKey("Java"));

        System.out.println("Lower Key: " + navigableMap.higherKey("Java"));

        System.out.println("Lower Key: " + navigableMap.ceilingKey("Java"));

        NavigableMap<String, String> headMap = navigableMap.headMap("Python", true);

        System.out.println("Head Map: " + headMap);

        NavigableMap<String, String> tailMap = navigableMap.tailMap("Python", true);

        System.out.println("Tail Map: " + tailMap);

        NavigableMap<String, String> subMap = navigableMap.subMap("Java", false, "Scala", false);

        System.out.println("Sub Map: " + subMap);
    }
}
