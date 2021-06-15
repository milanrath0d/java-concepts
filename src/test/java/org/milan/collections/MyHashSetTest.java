package org.milan.collections;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MyHashSetTest {

    @Test
    void testHashSetOperations() {
        MyHashSet myHashSet = new MyHashSet();

        myHashSet.add(10);
        myHashSet.add(20);
        myHashSet.remove(10);

        assertTrue(myHashSet.contains(20));
        assertFalse(myHashSet.contains(10));
    }

}