package org.milan.datastructure.linkedlist;

import org.junit.Assert;
import org.junit.Test;

/**
 * Test Class for {@link AddNumber}
 *
 * @author Milan Rathod
 */
public class AddNumberTest {

    @Test
    public void add() {
        LinkedList linkedList = new LinkedList();

        linkedList.head = new LinkedList.Node(1);

        linkedList.insertAtEnd(2);
        linkedList.insertAtEnd(3);
        linkedList.insertAtEnd(4);
        linkedList.insertAtEnd(5);

        AddNumber addNumber = new AddNumber();

        addNumber.add(linkedList, 5);

        Assert.assertEquals(5, linkedList.get(3).data);
        Assert.assertEquals(0, linkedList.get(4).data);

        linkedList = new LinkedList();

        linkedList.head = new LinkedList.Node(9);

        linkedList.insertAtEnd(9);

        addNumber.add(linkedList, 5);

        Assert.assertEquals(1, linkedList.get(0).data);
        Assert.assertEquals(0, linkedList.get(1).data);
        Assert.assertEquals(4, linkedList.get(2).data);

    }
}