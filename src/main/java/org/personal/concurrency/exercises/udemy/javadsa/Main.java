package org.personal.concurrency.exercises.udemy.javadsa;

import org.personal.concurrency.exercises.udemy.javadsa.datastructures.LinkedList;

public class Main {

    public static void main(String[] args) {
        LinkedList linkedList = new LinkedList(4);
        linkedList.printList();
        for(int i = 1; i <= 10; i++) {
            linkedList.append(i);
        }

        linkedList.printList();
        linkedList.removeLast();
        linkedList.printList();
        linkedList.prepend(11);
        linkedList.printList();

    }

}
