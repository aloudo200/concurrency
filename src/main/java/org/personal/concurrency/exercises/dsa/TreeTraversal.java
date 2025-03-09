package org.personal.concurrency.exercises.dsa;

import org.personal.concurrency.exercises.dsa.dependencies.BinaryTree;
import org.personal.concurrency.exercises.dsa.dependencies.TreeNode;
import org.springframework.data.relational.core.sql.In;

import java.util.*;

import static org.personal.concurrency.exercises.dsa.dependencies.Constants.*;

public class TreeTraversal {

    public static void main(String[] args) {


        BinaryTree tree = new BinaryTree<Integer>();
        tree.root = new TreeNode<>(5);
        tree.insertLeft(tree.root, 10);
        tree.insertRight(tree.root, 15);

        tree.insertLeft(tree.root.left, 56);
        tree.insertRight(tree.root.left, 47);

        tree.insertLeft(tree.root.right, 12);
        tree.insertRight(tree.root.right, 42);

        tree.printTree(tree.root);
        System.out.println(printLevelOrder(tree.root));

        System.out.println("In order traversal: " + tree.traverseTree(tree.root, IN_ORDER));
        System.out.println("Pre order traversal: " + tree.traverseTree(tree.root, PRE_ORDER));
        System.out.println("Post order traversal: " + tree.traverseTree(tree.root, POST_ORDER));

        System.out.println("Ancestors: " + ancestors(tree.root, 42));
        System.out.println("Mindepth " + minDepth(tree.root));


    }

    public static ArrayList<Integer> ancestors(TreeNode<Integer> root, int target) {

        ArrayList<Integer> ancestors = new ArrayList<>();
        findPath(root, target, ancestors);
        return ancestors;

    }

    public static int minDepth(TreeNode<Integer> root) {
        if (root == null) return 0; // Base case: empty tree

        if (root.left == null && root.right == null) return 1; // Leaf node → depth is 1

        if (root.left == null) return 1 + minDepth(root.right); // No left subtree
        if (root.right == null) return 1 + minDepth(root.left); // No right subtree

        return 1 + Math.min(minDepth(root.left), minDepth(root.right)); // Take the min depth of left & right
    }

    private static boolean findPath(TreeNode<Integer> node, int target, ArrayList<Integer> ancestors) {
        if (node == null) return false;
        if (node.value == target) return true;

        //Can we continue left or right? Recursively check this and ultimately return false when we reach a node with no children
        if(findPath(node.left, target, ancestors) || findPath(node.right, target, ancestors)) {
            ancestors.add(node.value);
            return true;
        }
        return false;
    }


    public static List<List<Integer>> printLevelOrder(TreeNode<Integer> root) {

        //Maintain a 2D array of each level
        List<List<Integer>> levels = new ArrayList<>();
        if(root == null) return levels;

        //Leverage the FIFO structure of a queue to detect new levels
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while(!queue.isEmpty()) {
            int levelSize = queue.size();
            List<Integer> level = new ArrayList<>();

            for(int i = 0; i < levelSize; i++) {
                TreeNode<Integer> node = queue.poll();
                assert node != null;
                level.add(node.value);

                if(node.left != null) queue.add(node.left);
                if(node.right != null) queue.add(node.right);
            }
            levels.add(level);
        }
        return levels;
    }
}

