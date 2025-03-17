package org.personal.concurrency.exercises.dependencies;

public class Node<T> {
    public T value;
    public Node<T> next;

    public Node(T data) {
        this.value = data;
    }
}
