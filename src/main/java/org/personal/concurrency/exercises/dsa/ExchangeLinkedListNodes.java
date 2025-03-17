package org.personal.concurrency.exercises.dsa;

import org.personal.concurrency.exercises.dependencies.BasicLinkedList;
import org.personal.concurrency.exercises.dependencies.Node;

public class ExchangeLinkedListNodes {


    public static void main(String[] args) {

        BasicLinkedList<Integer> basicLinkedList = new BasicLinkedList<>();
        basicLinkedList.add(1);
        basicLinkedList.add(2);
        basicLinkedList.add(3);
        basicLinkedList.add(4);
        basicLinkedList.add(5);
        basicLinkedList.add(6);
        basicLinkedList.add(7);
        basicLinkedList.add(8);
        basicLinkedList.add(9);
        basicLinkedList.printList();
        System.out.println(swapKthNode(basicLinkedList.getHead(), 8));
        basicLinkedList.printList();


    }

    public static Node<Integer> swapKthNode(Node<Integer> head, int k) {
        if (head == null) return null;

        Node<Integer> first = head, second = head;
        Node<Integer> firstPrev = null, secondPrev = null;

        // 1: find the Kth node from the beginning and its previous node
        for (int i = 1; i < k; i++) {
            firstPrev = first;
            first = first.next;
        }

        // 2: find kth node from end and its previous node - maintain a temp value to detect end of list (null element)
        Node<Integer> temp = first;
        while (temp.next != null) {
            temp = temp.next;
            secondPrev = second;
            second = second.next;
        }

        // 3: no swap needed if first and second are the same (i.e. cant swap same values in place)
        if (first == second) return head;

        // 4: swap the nodes
        if (firstPrev != null) {
            firstPrev.next = second;
        } else {
            head = second;
        }

        if (secondPrev != null) {
            secondPrev.next = first;
        } else {
            head = first;
        }

        // reassign pointers as necessary
        Node<Integer> tempNext = first.next;
        first.next = second.next;
        second.next = tempNext;

        return head;
    }
}




