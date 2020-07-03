package com.example.homlee.leetcode.solution

import com.example.homlee.leetcode.data.TreeNode

class Solution202007 {

    /**
     * 20200701
     * leetcode 718. 最长重复子数组
     * 给两个整数数组 A 和 B ，返回两个数组中公共的、长度最长的子数组的长度。
     */
    fun findCommonArrayLength(A: IntArray, B: IntArray): Int {
        var max = 0;
        val n1 = A.size+1;
        val n2 = B.size+1;
        val dp = Array(n1) { IntArray(n2) }

        for (i in 1 until n1) {
            for (j in 1 until n2) {
                dp[i][j] = if (A[i-1] == B[j-1]) dp[i-1][j-1] + 1 else 0
                max = if (dp[i][j] > max) dp[i][j] else max
            }
        }
        return max
    }

    /**
     * leetcode 108. 将有序数组转换为二叉搜索树
     */
    fun sortedArrayToBST(nums: IntArray): TreeNode? {
        return buildTree(nums, 0, nums.size-1)
    }

    fun buildTree(nums: IntArray, left: Int, right: Int): TreeNode? {
        if (left > right) return null
        val mid = (left + right) / 2
        val node = TreeNode(nums[mid])
        node.left = buildTree(nums, left, mid-1)
        node.right = buildTree(nums, mid+1, right)
        return node
    }
}

fun main(args: Array<String>) {
    val a = intArrayOf(0,1,1,1,1)
    val b = intArrayOf(1,0,1,0,1)
    val solution = Solution202007()
    println("common sub array is " + solution.findCommonArrayLength(a, b));
    val sortedArray = intArrayOf(-10, -3, 0, 5, 9)
    var tree = solution.sortedArrayToBST(sortedArray);
    println("Binary Search Tree ")
}