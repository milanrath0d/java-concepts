package org.milan.collections;

import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Milan Rathod
 */
public class TeeingCollectionDemo {
    public static void main(String[] args) {
        givenSetOfNumbers_thenCalculateAverage();
    }

    private static void givenSetOfNumbers_thenCalculateAverage() {
        double mean = Stream.of(1, 2, 3, 4, 5)
          .collect(Collectors.teeing(Collectors.summingDouble(i -> i),
            Collectors.counting(), (sum, count) -> sum / count));
        System.out.println(mean);
    }
}
