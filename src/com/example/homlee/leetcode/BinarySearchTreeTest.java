package com.example.homlee.leetcode;

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
        //到这里说明node节点不为null，子节点的最大深度+1
        return Math.max(maxDepth(node.left), maxDepth(node.right)) + 1;
    }


    /**
     * leetcode 450 删除二叉搜索树中的节点
     * @param root
     * @param key
     * @return
     */
    public TreeNode<Integer> deleteNode(TreeNode<Integer> root, int key) {

        if (root == null) {
            return null;
        }

        if (key > root.value) {
            //如果目标节点大于当前节点，则继续在右子树查找；
            root.right = deleteNode(root.right, key);
        } else if (key < root.value) {
            //如果目标节点小于当前节点，则继续在左子树查找；
            root.left = deleteNode(root.left, key);
        } else {
            //如果当前节点值等于指定的值
            if (root.left == null && root.right == null) {
                //如果是叶子节点，直接删除
                root = null;
            } else if (root.left != null) {
                //左子树不为空，则查找前驱节点的值；
                Integer value = maxLeftNodeVal(root);
                root.value = value;
                root.left = deleteNode(root.left, value);
            } else if (root.right != null) {
                //右子树不为空，则查找后继节点的值；
                Integer value = minRightNodeVal(root);
                root.value = value;
                root.right = deleteNode(root.right, value);
            }
        }

        return root;
    }

    //中序遍历的后继节点
    private Integer minRightNodeVal(TreeNode<Integer> root) {
        //先取当前节点的右节点，然后一直取该节点的左节点，直到左节点为空，则最后指向的节点为后继节点
        root = root.right;
        while (root.left != null) {
            root = root.left;
        }
        return root.value;
    }

    //中序遍历的前驱节点
    private Integer maxLeftNodeVal(TreeNode<Integer> root) {
        //先取当前节点的左节点，然后取该节点的右节点，直到右节点为空，则最后指向的节点为前驱节点
        root = root.left;
        while (root.right != null) {
            root = root.right;
        }
        return root.value;
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
