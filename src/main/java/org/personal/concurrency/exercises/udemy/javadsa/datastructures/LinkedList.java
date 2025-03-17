package org.personal.concurrency.exercises.udemy.javadsa.datastructures;

import org.personal.concurrency.exercises.dependencies.Node;

public class LinkedList {

    private Node<Integer> head;
    private Node<Integer> tail;
    private int length;

    public LinkedList(int value) {
        Node<Integer> node = new Node<>(value);
        head = node;
        tail = node;
        length = 1;
    }

    public void append (int value) {
        Node<Integer> node = new Node<>(value);
        if(length == 0) {
            head = node;
            tail = node;
            length++;
        } else {
            tail.next = node;
            tail = node;

        }
        length++;
    }

    public void prepend (int value) {
        Node<Integer> node = new Node<>(value);
        if(length == 0) {
            head = node;
            tail = node;
        } else {
            node.next = head;
            head = node;
        }
        length++;
    }

    public Node<Integer> removeLast() {
        if(length == 0) return null;

        Node<Integer> temp = head;
        Node<Integer> pre = head;

        while(temp.next != null) {
            pre = temp;
            temp = temp.next;
        }

        tail = pre;
        tail.next = null;
        length--;
        if(length == 0) {
            head = null;
            tail = null;
        }
        return temp;
    }

    public void insert (int index, int value) {

    }

    public void printList() {
        Node temp = head;
        while(temp != null) {
            System.out.print(temp.value + " -> ");
            temp = temp.next;
        }
        System.out.print("null\n");
    }

    public void getHead() {
        System.out.println("Head: " + head.value);
    }

    public void getTail() {
        System.out.println("Tail: " + tail.value);
    }

    public void getLength() {
        System.out.println("Length: " + length);    }
}
