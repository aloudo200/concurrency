package org.personal.concurrency.exercises.dependencies;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.personal.concurrency.exercises.dependencies.Constants.*;

public class BinaryTree<T> {
    public TreeNode<T> root;

    public BinaryTree() {
        this.root = null;
    }

    public void insertLeft(TreeNode<T> parent, T value) {
        parent.left = new TreeNode<>(value);
    }

    public void insertRight(TreeNode<T> parent, T value) {
        parent.right = new TreeNode<>(value);
    }

    // somewhat graphical printer
    public void printTree(TreeNode<T> root) {
        if (root == null) {
            System.out.println("Tree is empty");
            return;
        }

        int height = getHeight(root);
        int width = (int) Math.pow(2, height) - 1; // get the max width of bottom level
        List<List<String>> res = new ArrayList<>();

        for (int i = 0; i < height; i++) {
            res.add(new ArrayList<>(Collections.nCopies(width, "  ")));
        }

        fill(res, root, 0, 0, width - 1);
        for (List<String> line : res) {
            System.out.println(String.join("", line));
        }
    }

    // recursive fill
    private void fill(List<List<String>> res, TreeNode root, int level, int left, int right) {
        if (root == null) return;
        int mid = (left + right) / 2;
        res.get(level).set(mid, String.valueOf(root.value));
        fill(res, root.left, level + 1, left, mid - 1);
        fill(res, root.right, level + 1, mid + 1, right);
    }

    private int getHeight(TreeNode<T> root) {
        if (root == null) return 0;
        return 1 + Math.max(getHeight(root.left), getHeight(root.right));
    }

    //using a switch statement to make it easy to pick what type of traversal we want to use
    public ArrayList<T> traverseTree(TreeNode<T> root, String traversalType) {
        ArrayList<T> nodes = new ArrayList<>();
        switch(traversalType) {
            case IN_ORDER: traverseInOrder(root, nodes); break;
            case POST_ORDER: traversePostOrder(root, nodes); break;
            case PRE_ORDER: traversePreOrder(root, nodes); break;
        }
        return nodes;
    }

    private void traverseInOrder(TreeNode<T> root, ArrayList<T> nodes) {
        if(root == null) return;
        traverseInOrder(root.left, nodes);
        nodes.add(root.value);
        traverseInOrder(root.right, nodes);

    }

    private void traversePostOrder(TreeNode<T> root, ArrayList<T> nodes) {
        if (root == null) return;
        traversePostOrder(root.left, nodes);
        traversePostOrder(root.right, nodes);
        nodes.add(root.value);
    }

    private void traversePreOrder(TreeNode<T> root, ArrayList<T> nodes) {
        if (root == null) return;
        nodes.add(root.value);
        traversePreOrder(root.left, nodes);
        traversePreOrder(root.right, nodes);
    }
}

