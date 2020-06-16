package com.example.homlee.leetcode;

import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * 二叉查找数
 */
public class BinarySearchTreeTest {
    private static class TreeNode<T> {
        T value;
        TreeNode<T> left;
        TreeNode<T> right;
        public TreeNode(T value) {
            this.value = value;
        }
    }

    /**
     * leetcode 104 二叉树的最大深度
     * @param node
     * @return
     */
    public int maxDepth(TreeNode node) {
        if (node == null) {
            return 0;
        }

        return Math.max(maxDepth(node.left), maxDepth(node.right)) + 1;
    }


    /**
     * leetcode 450 删除二叉搜索树中的节点
     * @param root
     * @param key
     * @return
     */
    public TreeNode<Integer> deleteNode(TreeNode<Integer> root, int key) {

        TreeNode<Integer> node = root;
        TreeNode<Integer> p = null;

        while (node != null) {
            if (node.value == key) {
                break;
            } else {
                p = node;
                if (key > node.value) {
                    node = node.right;
                } else {
                    node = node.left;
                }
            }
        }

        if (node != null) {
            //被删除的是根节点
            if (p != null && node.left == null && node.right == null) {
                if (p.left == node) {
                    p.left = null;
                } else {
                    p.right = null;
                }
            } else {

            }
        }

        return root;
    }

    public static void main(String[] args) {
        TreeNode<Integer> root = new TreeNode<>(3);
        root.left = new TreeNode<>(9);
        TreeNode<Integer> right = new TreeNode<>(20);
        right.left = new TreeNode<>(15);
        right.right = new TreeNode<>(7);
        root.right = right;

        BinarySearchTreeTest test = new BinarySearchTreeTest();
        int depth = test.maxDepth(root);
        System.out.println("depth = " + depth);
    }
}
