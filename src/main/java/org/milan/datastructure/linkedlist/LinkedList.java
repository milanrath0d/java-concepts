package org.milan.datastructure.linkedlist;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Linked list with all basic operations
 *
 * @author Milan Rathod
 */
public class LinkedList {

    private static final Logger LOG = LoggerFactory.getLogger(LinkedList.class);

    Node head;

    /**
     * Insert node before head node
     */
    public void insertBeforeHead(int data) {
        Node newHead = new Node(data);
        newHead.next = this.head;
        this.head = newHead;
    }

    /**
     * Insert node after specified node
     */
    public void insertAfterSpecifiedNode(Node previousNode, int data) {
        if (previousNode == null) {
            LOG.warn("Previous node cannot be null");
            return;
        }
        Node newHead = new Node(data);
        newHead.next = previousNode.next;
        previousNode.next = newHead;
    }

    /**
     * Insert Node at the end of list
     */
    public void insertAtEnd(int data) {
        Node newHead = new Node(data);
        if (this.head == null) {
            LOG.warn("linked list is empty");
            return;
        }
        Node last = head;
        while (last.next != null) {
            last = last.next;
        }
        last.next = newHead;
    }

    /**
     * Delete a node by data key
     */
    public void deleteNodeByKey(int data) {
        Node temp = head;
        Node previous = null;

        // To be deleted node is a head node
        if (temp != null && temp.data == data) {
            head = temp.next;
            return;
        }

        //logic to identify the deletion node
        while (temp != null && temp.data != data) {
            previous = temp;
            temp = temp.next;
        }

        if (temp == null) {
            return;
        }
        previous.next = temp.next;
    }

    /**
     * Delete a node by its position
     */
    public void deleteNodeByPosition(int position) {

        // To be deleted node is a head node
        if (position == 1) {
            head = head.next;
            return;
        }

        int targetIndex = 1;

        Node temp = head;

        while (temp != null && targetIndex < position - 1) {
            temp = temp.next;
            targetIndex++;
        }

        if (temp == null || temp.next == null) return;

        temp.next = temp.next.next;

    }

    /**
     * Swap two nodes of linked list
     */
    public void swap(int x, int y) {
        if (x == y) {
            LOG.warn("both nodes are same, no changes required");
            return;
        }

        Node currentX = head;

        Node previousX = null;

        Node currentY = head;

        Node previousY = null;

        while (currentX != null && currentX.data != x) {
            previousX = currentX;
            currentX = currentX.next;
        }

        while (currentY != null && currentY.data != y) {
            previousY = currentY;
            currentY = currentY.next;
        }

        // Check to ensure that both nodes are present in linked list
        if (currentX == null || currentY == null) {
            LOG.warn("one of the nodes are not present in linked list");
            return;
        }

        if (previousX != null)
            previousX.next = currentY;
        else
            head = currentY;

        if (previousY != null)
            previousY.next = currentX;
        else
            head = currentX;

        //Do the swapping
        Node temp = currentX.next;
        currentX.next = currentY.next;
        currentY.next = temp;
    }

    /**
     * Swap linked list nodes in pair
     */
    public void swapPairs(Node head) {

        if (head != null && head.next != null) {
            int temp = head.data;
            head.data = head.next.data;
            head.next.data = temp;

            swapPairs(head.next.next);
        }

    }

    /**
     * Reverse a given linked list - iterative approach
     */
    public void reverseByIterative() {
        Node current = head;
        Node prev = null;
        Node next;
        while (current != null) {
            next = current.next;
            current.next = prev;
            prev = current;
            current = next;

        }
        head = prev;
    }

    /**
     * Reverse a given linked list - recursive approach
     */
    public Node reverseByRecursive(Node curr, Node prev) {
        if (curr.next == null) {
            head = curr;
            curr.next = prev;
            return null;
        }
        Node first = curr.next;
        curr.next = prev;
        reverseByRecursive(first, curr);
        return head;
    }

    //Merge two linkedlist - iterative approach
    public Node mergeByIterative(Node l1, Node l2) {

        Node current1 = l1;
        Node current2 = l2;

        Node temp;
        Node tail;
        if (current1.data < current2.data) {
            temp = current1;
            current1 = current1.next;
        } else {
            temp = current2;
            current2 = current2.next;
        }

        tail = temp;
        while (current1 != null && current2 != null) {
            if (current1.data <= current2.data) {
                temp.next = current1;
                current1 = current1.next;
            } else if (current1.data > current2.data) {
                temp.next = current2;
                current2 = current2.next;
            }
            temp = temp.next;
        }
        if (null == current1) {
            temp.next = current2;
        } else {
            temp.next = current1;
        }
        return tail;
    }

    //Merge two linkedlist - recursive approach
    public Node mergeByRecursive(Node head1, Node head2) {

        if (head1 == null) {
            return head2;
        }

        if (head2 == null) {
            return head1;
        }

        Node current = null;

        if (head1.data < head2.data) {
            current = head1;
            current.next = mergeByRecursive(head1.next, head2);
        } else {
            current = head2;
            current.next = mergeByRecursive(head1, head2.next);
        }

        return current;
    }

    //Reverse a linked list in group of given size
    public Node reverseByGroupSize(Node head, int size) {
        Node current = head;
        Node prev = null;
        Node next = null;
        int count = 0;
        while (count < size && current != null) {
            next = current.next;
            current.next = prev;
            prev = current;
            current = next;
            count++;
        }
        if (next != null)
            head.next = reverseByGroupSize(next, size);
        return prev;
    }

    /**
     * Return true if loop is present in linked list
     */
    public int detectAndCountLoop() {
        Node slow = head;
        Node fast = head;
        int count = 0;
        while (slow != null && fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                Node current = slow;
                do {
                    count++;
                    current = current.next;
                } while (current != slow);
                return count;
            }
        }
        return count;
    }

    //remove detected loop based on boolean flag
    public void removeLoop(Node loop, Node current) {
        Node ptr1 = current;
        while (true) {
            Node ptr2 = loop;
            while (ptr2.next != loop && ptr2.next != ptr1) {
                ptr2 = ptr2.next;
            }
            if (ptr2.next == ptr1) {
                ptr2.next = null;
                return;
            }
            ptr1 = ptr1.next;
        }
    }

    //add contents of two linked lists
    public Node addTwoLinkedList(Node n1, Node n2, int carry) {
        int x = 0;
        int y = 0;
        if (n1 == null && n2 == null) return null;
        if (n1 != null) {
            x = n1.data;
            n1 = n1.next;
        }
        if (n2 != null) {
            y = n2.data;
            n2 = n2.next;
        }
        int z = x + y + carry;
        if (z > 9) {
            carry = z / 10;
            z %= 10;

        } else {
            carry = 0;
        }
        Node n = new Node(z);
        n.next = addTwoLinkedList(n1, n2, carry);
        return n;
    }

    /**
     * Remove duplicates in ascending sorted linked list
     */
    public void removeDuplicatesSorted() {
        Node current = head;

        if (current == null) return;

        Node next = current.next;

        while (next != null) {
            if (current.data == next.data) {
                current.next = next.next;
            } else {
                current = current.next;
            }
            next = next.next;
        }
    }

    /**
     *
     */
    public void removeDuplicatesUnsorted() {

        Set<Integer> store = new HashSet<>();

        Node current = head;
        Node previous = null;

        while (current != null) {
            if (!store.contains(current.data)) {
                store.add(current.data);
                previous = current;
            } else {
                previous.next = current.next;
            }
            current = current.next;
        }

    }

    //
    public Node rotate(Node head, int k) {
        int count = 1;
        Node temp = head;
        Node newHead;
        while (count < k) {
            temp = temp.next;
            count++;
        }
        newHead = temp.next;
        Node tail = newHead;
        temp.next = null;
        while (tail.next != null) {
            tail = tail.next;
        }
        tail.next = head;
        return newHead;
    }

    //Circular Linked list - split into two halved
    public List<Node> split(Node head) {
        Node ptr1 = head;
        Node ptr2 = head;
        Node head1 = null;
        Node head2 = null;
        while (ptr2.next != head && ptr2.next.next != head) {
            ptr2 = ptr2.next.next;
            ptr1 = ptr1.next;
        }
        if (ptr2.next.next == head) {
            ptr2 = ptr2.next;
        }
        head1 = head;
        if (head.next != head) head2 = ptr1.next;
        ptr2.next = ptr1.next;
        ptr1.next = head;
        List<Node> l = new ArrayList<>();
        l.add(head1);
        l.add(head2);
        return l;
    }

    //Insert new node into the circular linked list
    public void insertByCircularList(Node node) {
        Node current = head;
        if (current == null) {
            node.next = node;
            head = node;
        } else if (node.data <= current.data) {
            while (current.next != head) {
                current = current.next;
            }
            current.next = node;
            node.next = head;
            head = node;
        } else {
            while (current.next != head && current.next.data < node.data) {
                current = current.next;
            }
            node.next = current.next;
            current.next = node;
        }
    }

    public void printList() {
        printList(head);
    }

    public void printList(Node node) {
        Node temp = node;
        while (temp != null) {
            System.out.print(temp.data + "-->");
            temp = temp.next;
        }
        System.out.println("null");
    }

    /**
     * Get Node based on requested position
     */
    public Node get(int position) {
        Node current = head;

        int count = 0;

        while (current != null) {
            if (count == position) {
                return current;
            }
            count++;
            current = current.next;
        }
        return null;
    }

    /**
     * Check if node is present in linked list or not
     */
    public boolean contains(int key) {
        Node current = head;
        while (current != null) {
            if (current.data == key) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    /**
     * Calculate size of a linked list - iterative approach
     */
    public int size() {
        int count = 0;
        Node temp = head;
        if (temp == null) {
            LOG.warn("linked list is empty");
            return -1;
        }
        while (temp != null) {
            count++;
            temp = temp.next;
        }
        return count;
    }

    /**
     * Calculate size of a linked list - recursive approach
     */
    public int size(Node head) {
        if (head == null) {
            return 0;
        } else {
            return 1 + size(head.next);
        }
    }

    static class Node {
        int data;

        Node next;

        Node(int d) {
            data = d;
            next = null;
        }
    }
}
