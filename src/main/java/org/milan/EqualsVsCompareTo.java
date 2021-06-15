package org.milan;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

/**
 * Example to show why equals and compareTo method should be consistent using {@linkplain java.math.BigDecimal}
 *
 * @author Milan Rathod
 */
public class EqualsVsCompareTo {

    public static void main(String[] args) {
        BigDecimal first = new BigDecimal("2.0");
        BigDecimal second = new BigDecimal("2.00");

        System.out.println(first.equals(second));
        System.out.println(first.compareTo(second));

        Set<BigDecimal> bigDecimals = new HashSet<>();

        bigDecimals.add(first);
        bigDecimals.add(second);

        // HashSet is using equals method to check duplicates
        System.out.println(bigDecimals.size());

        bigDecimals = new TreeSet<>();

        bigDecimals.add(first);
        bigDecimals.add(second);

        // TreeSet is using compareTo method to check duplicates
        System.out.println(bigDecimals.size());
    }
}
