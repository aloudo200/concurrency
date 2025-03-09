package org.personal.concurrency.exercises.dsa;

import org.personal.concurrency.exercises.dsa.dependencies.BasicLinkedList;
import org.personal.concurrency.exercises.dsa.dependencies.Node;

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

        // Step 1: Find the Kth node from the beginning and its previous node
        for (int i = 1; i < k; i++) {
            firstPrev = first;
            first = first.next;
        }

        // Step 2: Find the Kth node from the end and its previous node
        Node<Integer> temp = first;
        while (temp.next != null) {
            temp = temp.next;
            secondPrev = second;
            second = second.next;
        }

        // Step 3: If the Kth node from the start and end are the same, no swap needed
        if (first == second) return head;

        // Step 4: Swap the nodes
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

        // Swap their next pointers
        Node<Integer> tempNext = first.next;
        first.next = second.next;
        second.next = tempNext;

        return head;
    }
}




