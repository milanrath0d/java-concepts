package org.milan.streams;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Difference between map and flatmap
 *
 * @author Milan Rathod
 */
class MapVsFlatMapTest {

    @Test
    void simple_useCase_mapWorks() {
        // It uses for transformation.
        // It applies function on each element of stream and
        // returns new stream
        // i.e. stream of strings to stream of integers transformation
        List<String> myList = Stream.of("a", "b")
                .map(String::toUpperCase)
                .collect(Collectors.toList());
        assertEquals(asList("A", "B"), myList);
    }

    @Test
    void complex_useCase_mapFails() {
        List<List<String>> list = Arrays.asList(
                Collections.singletonList("a"),
                Collections.singletonList("b"));

        // The key thing to remember is that the function used for transformation in the map() returns a single value.
        // If map() uses a function, which, instead of returning a single value returns a Stream of values than
        // you have a Stream of Stream of values, and flatmap() is used to flatten that into a Stream of values.
        Stream<Stream<String>> result = list.stream()
                .map(strings -> Stream.of("test"));

        assertNotNull(result);
    }

    @Test
    void complex_useCase_flatMapWorks() {
        List<List<String>> list = Arrays.asList(
                Collections.singletonList("a"),
                Collections.singletonList("b"));
        // flatMap will perform map and flat operation
        // first apply the map function and then flatten the result
        List<String> flatMapList = list.stream()
                .flatMap(Collection::stream)
                .collect(Collectors.toList());

        assertEquals(asList("a", "b"), flatMapList);
    }
}
