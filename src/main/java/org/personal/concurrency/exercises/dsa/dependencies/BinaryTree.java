package org.personal.concurrency.exercises.dsa.dependencies;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.personal.concurrency.exercises.dsa.dependencies.Constants.*;

public class BinaryTree<T> {
    public TreeNode<T> root;

    // Constructor
    public BinaryTree() {
        this.root = null;
    }

    // Insert manually (since it's not a BST)
    public void insertLeft(TreeNode<T> parent, T value) {
        parent.left = new TreeNode<>(value);
    }

    public void insertRight(TreeNode<T> parent, T value) {
        parent.right = new TreeNode<>(value);
    }

    // Fancy Printer
    public void printTree(TreeNode<T> root) {
        if (root == null) {
            System.out.println("Tree is empty");
            return;
        }

        int height = getHeight(root);
        int width = (int) Math.pow(2, height) - 1; // Max width at the bottom
        List<List<String>> res = new ArrayList<>();

        for (int i = 0; i < height; i++) {
            res.add(new ArrayList<>(Collections.nCopies(width, "  ")));
        }

        fill(res, root, 0, 0, width - 1);
        for (List<String> line : res) {
            System.out.println(String.join("", line));
        }
    }

    // Recursively fill the list with node values
    private void fill(List<List<String>> res, TreeNode root, int level, int left, int right) {
        if (root == null) return;
        int mid = (left + right) / 2;
        res.get(level).set(mid, String.valueOf(root.value));
        fill(res, root.left, level + 1, left, mid - 1);
        fill(res, root.right, level + 1, mid + 1, right);
    }

    // Get the height of the tree
    private int getHeight(TreeNode<T> root) {
        if (root == null) return 0;
        return 1 + Math.max(getHeight(root.left), getHeight(root.right));
    }

    public ArrayList<T> traverseTree(TreeNode<T> root, String traversalType) {
        ArrayList<T> nodes = new ArrayList<>();
        switch(traversalType) {
            case IN_ORDER: traverseInOrder(root, nodes); break;
            case POST_ORDER: traversePostOrder(root, nodes); break;
            case PRE_ORDER: traversePreOrder(root, nodes); break;
        }
        return nodes;
    }

    // Helper function to perform inorder traversal
    private void traverseInOrder(TreeNode<T> root, ArrayList<T> nodes) {
        if (root == null) return;

        traverseInOrder(root.left, nodes);  // Visit left subtree
        nodes.add(root.value);        // Visit node
        traverseInOrder(root.right, nodes); // Visit right subtree
    }

    // Helper function to perform inorder traversal
    private void traversePostOrder(TreeNode<T> root, ArrayList<T> nodes) {
        if (root == null) return;
        traversePostOrder(root.left, nodes);  // Visit left subtree
        traversePostOrder(root.right, nodes); // Visit right subtree
        nodes.add(root.value);       // Visit node
    }

    // Helper function to perform inorder traversal
    private void traversePreOrder(TreeNode<T> root, ArrayList<T> nodes) {
        if (root == null) return;
        nodes.add(root.value);        // Visit node
        traversePreOrder(root.left, nodes);  // Visit left subtree
        traversePreOrder(root.right, nodes); // Visit right subtree
    }
}

