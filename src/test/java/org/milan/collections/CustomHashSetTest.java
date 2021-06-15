package org.milan.collections;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CustomHashSetTest {

    @Test
    void testHashSetOperations() {
        CustomHashSet customHashSet = new CustomHashSet();

        customHashSet.add(10);
        customHashSet.add(20);
        customHashSet.remove(10);

        assertTrue(customHashSet.contains(20));
        assertFalse(customHashSet.contains(10));
    }

}