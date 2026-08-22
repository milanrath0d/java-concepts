package org.milan.collections;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * @author Milan Rathod
 */
class FlatteningNestedCollectionsTest {

    private final FlatteningNestedCollections flatteningNestedCollections = new FlatteningNestedCollections();

    private List<List<String>> nestedList() {
        return List.of(
                List.of("one:one"),
                List.of("two:one", "two:two", "two:three"),
                List.of("three:one", "three:two", "three:three", "three:four"));
    }

    @Test
    void flatteningNestedCollectionWithUsingForEachMethod() {
        List<String> result = flatteningNestedCollections.usingForEach(nestedList());

        assertNotNull(result);
        assertEquals(8, result.size());
    }

    @Test
    void flatteningNestedCollectionWithUsingFlatMapMethod() {
        List<String> result = flatteningNestedCollections.usingFlatMap(nestedList());

        assertNotNull(result);
        assertEquals(8, result.size());
    }
}
