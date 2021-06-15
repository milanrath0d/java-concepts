package org.milan.collections;

import java.util.*;

/**
 * Comparison between HashSet, LinkedHashSet and TreeSet
 *
 * @author Milan Rathod
 */
public class SetComparison {

    public static void main(String[] args) {
        Set<String> hashSet = new HashSet<>();

        Set<String> linkedHashSet = new LinkedHashSet<>();

        Set<String> treeSet = new TreeSet<>();

        for (String fruit : Arrays.asList("mango", "apple", "banana")) {
            hashSet.add(fruit);
            linkedHashSet.add(fruit);
            treeSet.add(fruit);
        }

        // No ordering in HashSet - elements are stored in random order
        System.out.println("HashSet: " + hashSet);

        // Insertion order or elements – LinkedHashSet stored elements as insertion
        System.err.println("LinkedHashSet: " + linkedHashSet);

        // Should be sorted order – TreeSet stores element in sorted order
        System.out.println("TreeSet: " + treeSet);

        // Use LinkedHashSet when copying one set from another
        System.out.println("Copied Set" + copy(hashSet));

        // Performance test to insert 10M elements in HashSet, LinkedHashSet and TreeSet
        Set<Integer> numbers = new HashSet<>();
        addElements(numbers, "HashSet");

        // LinkedHashSet performance Test – inserting 10M objects
        numbers = new LinkedHashSet<>();
        addElements(numbers, "LinkedHashSet");

        // TreeSet performance Test – inserting 10M objects
        numbers = new TreeSet<>();
        addElements(numbers, "TreeSet");
    }

    private static void addElements(Set<Integer> numbers, String type) {
        long startTime = System.nanoTime();
        for (int i = 0; i < 10000000; i++) {
            numbers.add(i);
        }

        long endTime = System.nanoTime();
        System.out.println("Total time to insert 10M elements in " + type + " in sec : "
                + (endTime - startTime));
    }

    private static <T> Set<T> copy(Set<T> source) {
        return new LinkedHashSet<>(source);
    }
}
