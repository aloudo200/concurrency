package org.personal.concurrency.exercises.dependencies;

public class TreeNode<T> {
    public T value;
    public TreeNode<T> left;
    public TreeNode<T> right;

    // Constructor
    public TreeNode(T value) {
        this.value = value;
        this.left = null;
        this.right = null;
    }
}
