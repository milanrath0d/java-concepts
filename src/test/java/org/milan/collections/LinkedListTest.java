package org.milan.collections;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LinkedListTest {

    private LinkedList<String> linkedList;

    @BeforeEach
    void init() {
        linkedList = new LinkedList<>();

        linkedList.add("LD");
        linkedList.add("IS");
        linkedList.add("THE");
        linkedList.add("BEST");
        linkedList.add("ENGINEERING");
        linkedList.add("COLLEGE");
    }

    @Test
    void testAddOperation() {
        linkedList.addFirst("addFirst");
        linkedList.addLast("addLast");

        assertEquals("addFirst", linkedList.getFirst());
        assertEquals("addLast", linkedList.getLast());
    }

    @Test
    void testOfferOperation() {
        linkedList.offerFirst("offerFirst");
        linkedList.offerLast("offerLast");

        assertEquals("offerFirst", linkedList.getFirst());
        assertEquals("offerLast", linkedList.getLast());
    }

    @Test
    void testRemoveOperation() {
        assertEquals("LD", linkedList.removeFirst());
        assertEquals("COLLEGE", linkedList.removeLast());
        assertEquals("IS", linkedList.remove());
    }

    @Test
    void testPollOperation() {
        assertEquals("LD", linkedList.pollFirst());
        assertEquals("COLLEGE", linkedList.pollLast());
        assertEquals("IS", linkedList.poll());
    }

    @Test
    void testIterator() {
        Iterator<String> iterator = linkedList.iterator();

        while (iterator.hasNext()) {
            assertEquals("LD", iterator.next());
            break;
        }
    }

    @Test
    void testDescendingIterator() {
        Iterator<String> iterator = linkedList.descendingIterator();

        while (iterator.hasNext()) {
            assertEquals("COLLEGE", iterator.next());
            break;
        }
    }

}