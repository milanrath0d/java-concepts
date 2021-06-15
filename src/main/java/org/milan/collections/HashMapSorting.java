package org.milan.collections;

import java.util.*;
import java.util.Map.Entry;
import java.util.stream.Collectors;

/**
 * Example of sorting {@link HashMap} using comparator or using their values
 *
 * @author Milan Rathod
 */
public class HashMapSorting {

    /**
     * Sort the values of given hashMap by using comparator
     *
     * @param map         given unsorted map
     * @param isDescOrder if true, order is DESC. Default to ASC
     * @return sorted map by values
     */
    public Map<String, Integer> sortByValuesUsingComparator(Map<String, Integer> map, final boolean isDescOrder) {
        List<Entry<String, Integer>> list = new LinkedList<>(map.entrySet());

        list.sort((o1, o2) -> isDescOrder ? o2.getValue().compareTo(o1.getValue()) : o1.getValue().compareTo(o2.getValue()));

        return list.stream().collect(Collectors.toMap(Entry::getKey, Entry::getValue, (a, b) -> b, LinkedHashMap::new));
    }

    /**
     * Sort the values of given hashMap
     *
     * @param map given unsorted map
     * @return sorted map by values
     */
    public Map<String, Integer> sortByValues(Map<String, Integer> map) {
        return map
                .entrySet()
                .stream()
                .sorted(Entry.comparingByValue())
                .collect(Collectors.toMap(Entry::getKey, Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
    }
}
