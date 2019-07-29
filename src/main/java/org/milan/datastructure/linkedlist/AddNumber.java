package org.milan.datastructure.linkedlist;

/**
 * Problem: {@link @https://www.geeksforgeeks.org/add-1-number-represented-linked-list/}
 *
 * @author Milan Rathod
 */
public class AddNumber {

    public LinkedList.Node add(LinkedList list, int number) {

        LinkedList.Node reversedHead = list.reverseByRecursive(list.head, null);

        reversedHead = addNumber(reversedHead, number);

        return list.reverseByRecursive(reversedHead, null);
    }

    private LinkedList.Node addNumber(LinkedList.Node head, int number) {

        int sum;

        LinkedList.Node prev = null;

        int carry = number;

        LinkedList.Node temp = head;

        while (head != null) {
            sum = carry + head.data;

            carry = (sum >= 10) ? 1 : 0;

            sum %= 10;

            head.data = sum;

            prev = head;

            head = head.next;
        }

        if (carry > 0) {
            LinkedList.Node newNode = new LinkedList.Node(carry);
            prev.next = newNode;
        }

        return temp;
    }
}
