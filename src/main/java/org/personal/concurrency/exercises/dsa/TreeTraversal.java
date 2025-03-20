package org.personal.concurrency.exercises.dsa;

import org.personal.concurrency.exercises.dependencies.BinaryTree;
import org.personal.concurrency.exercises.dependencies.TreeNode;

import java.util.*;

import static org.personal.concurrency.exercises.dependencies.Constants.*;

public class TreeTraversal {

    private static int maxPathSum = Integer.MIN_VALUE;

    public static void main(String[] args) {

        BinaryTree<Integer> tree = new BinaryTree<>();
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

        System.out.println("Pre order traversal max sum: " + findMaxPathSum(tree.root) );
        System.out.printf("Post order traversal sum : %s\n ", postOrderTraversalSum(tree.root, new HashSet<>(), new ArrayList<>()));

        System.out.println("Ancestors: " + ancestors(tree.root, 42));
        System.out.println("Mindepth " + minDepth(tree.root));

    }

    public static ArrayList<Integer> ancestors(TreeNode<Integer> root, int target) {

        ArrayList<Integer> ancestors = new ArrayList<>();
        findPath(root, target, ancestors);
        return ancestors;

    }

    public static int minDepth(TreeNode<Integer> root) {
        if (root == null) return 0; // check if the root node is empty

        if (root.left == null && root.right == null) return 1; 

        if (root.left == null) return 1 + minDepth(root.right); // can't traverse left
        if (root.right == null) return 1 + minDepth(root.left); // can't traverse right

        return 1 + Math.min(minDepth(root.left), minDepth(root.right)); // return the min depth of left & right
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

    // function to eval subtree sums
    private static int findMaxPathSum(TreeNode<Integer> root) {
        if (root == null) return 0;

        // recursively compute, ignore negative paths
        int leftMax = Math.max(0, findMaxPathSum(root.left));
        int rightMax = Math.max(0, findMaxPathSum(root.right));

        // if current node as the highest point (root of the path), compute sum
        int currentPathSum = root.value + leftMax + rightMax;

        // update global max if the current path sum is the highest found and return
        maxPathSum = Math.max(maxPathSum, currentPathSum);
        return root.value + Math.max(leftMax, rightMax);
    }

    private static ArrayList<Integer> postOrderTraversalSum(TreeNode<Integer> root, HashSet<Integer> seen, ArrayList<Integer> nodeVals) {
        if(root.value != null && !seen.contains(root.value)) {
            seen.add(root.value);
            nodeVals.add(root.value);
            return nodeVals;
        }

        postOrderTraversalSum(root.left, seen, nodeVals);
        postOrderTraversalSum(root.right, seen, nodeVals);

        return nodeVals;

    }


    public static List<List<Integer>> printLevelOrder(TreeNode<Integer> root) {

        // maintain a 2D array of each level
        List<List<Integer>> levels = new ArrayList<>();
        if(root == null) return levels;

        // leverage the FIFO structure of a queue to detect new levels
        Queue<TreeNode<Integer>> queue = new LinkedList<>();
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

