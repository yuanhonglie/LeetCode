package com.example.homlee.leetcode.solution;

import com.example.homlee.leetcode.data.TreeNode;

public class Solution202007 {

    /**
     * 20200702
     * leetcode 378. 有序矩阵中第K小的元素
     * @param matrix
     * @param k
     * @return
     */
    public int kthSmallest(int[][] matrix, int k) {
        int n = matrix.length;
        int left = matrix[0][0];
        int right = matrix[n-1][n-1];
        while (left < right) {
            int mid = left + (right - left)/2;
            if (check(matrix, mid, k, n)) {
                //小于等于mid的数字个数大于等于k
                //所以第k大的数字不在mid的右边，二分查找的调整右边界
                right = mid;
            } else {
                //小于等于mid的数字个数小于k
                //所以第k大的数字在mid的右边，二分查找的调整左边界
                left = mid + 1;
            }
        }

        //如何证明left是存在于矩阵中数字？
        //当check()方法返回true时，矩阵中不比mid大的数的个数存在等于k的情况，但程序并未就此停止，而是将二分查找的上边界设置为mid，继续查找。
        //当查找停止时，left=right，此时left就是满足矩阵中数字比mid小的个数等于k的多个数字中的最小值。这个值一定存在于矩阵中。所以left就是矩阵第k大的数字
        return left;
    }

    /**
     * 判断小于等于mid的数字个数是否大于等于k
     * @param matrix
     * @param mid
     * @param k
     * @param n
     * @return true - 小于等于mid的数字个数大于等于k
     */
    private boolean check(int[][] matrix, int mid, int k, int n) {
        int num = 0;
        int i = n-1;
        int j = 0;
        while (i >= 0 && j < n) {
            if (matrix[i][j] <= mid) {
                num += i + 1;
                j++;
            } else {
                i--;
            }
        }

        return num >= k;
    }



    /**
     * 20200701
     * leetcode 718. 最长重复子数组
     * 给两个整数数组 A 和 B ，返回两个数组中公共的、长度最长的子数组的长度。
     */
    public int findCommonArrayLength(int[] A, int[] B) {
        int max = 0;
        int n1 = A.length+1;
        int n2 = B.length+1;
        int[][] dp = new int[n1][n2];

        for (int i = 1; i < n1; i++) {
            for (int j = 1; j < n2; j++) {
                if (A[i-1] == B[j-1]) {
                    dp[i][j] = dp[i-1][j-1] + 1;
                } else {
                    dp[i][j] = 0;
                }

                max = dp[i][j] > max ? dp[i][j] : max;
            }
        }
        return max;
    }

    /**
     * leetcode 108. 将有序数组转换为二叉搜索树
     */
    public TreeNode sortedArrayToBST(int[] nums) {
        return buildTree(nums, 0, nums.length-1);
    }

    private TreeNode buildTree(int[] nums, int left, int right) {
        if (left > right) {
            return null;
        }

        int mid = (left + right) / 2;
        TreeNode node = new TreeNode(nums[mid]);
        node.left = buildTree(nums, left, mid-1);
        node.right = buildTree(nums, mid+1, right);
        return node;
    }



    public static void main(String[] args) {
        Solution202007 solution202007 = new Solution202007();
        int[][] matrix = {{1,5,9},{10,11,13},{12,13,15}};
        System.out.println("num = " + solution202007.kthSmallest(matrix, 8));

        int[] a = {0,1,1,1,1};
        int[] b = {1,0,1,0,1};
        System.out.println("common sub array is " + solution202007.findCommonArrayLength(a, b));
        int[] sortedArray = {-10, -3, 0, 5, 9};
        TreeNode tree = solution202007.sortedArrayToBST(sortedArray);
        System.out.println("Binary Search Tree ");
    }
}
