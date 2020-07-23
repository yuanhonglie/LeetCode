package com.example.homlee.leetcode.solution;

import com.example.homlee.leetcode.data.TreeNode;
import com.example.homlee.leetcode.data.Trie;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

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




    /**
     * leetcode 32. 最长有效括号
     * @param s
     */
    public int longestValidParentheses(String s) {
        Stack<Integer> stack = new Stack<>();
        int i = 0;
        int max = 0;
        stack.push(-1);
        while (i < s.length()) {
            char c = s.charAt(i);
            if (c == '(') {
                stack.push(i);
            } else {
                stack.pop();
                if (stack.isEmpty()) {
                    stack.push(i);
                } else {
                    max = Math.max(max, i - stack.peek());
                }
            }
            i++;
        }
        return max;
    }

    /**
     * leetcode 32. 最长有效括号（动态规划）
     * @param s
     * @return
     */
    public int longestValidParentheses1(String s) {
        int max = 0;
        int[] dp = new  int[s.length()];
        for (int i = 1; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == ')') {
                if (c == ')' && s.charAt(i-1) == '(') {
                    dp[i] = (i >= 2 ? dp[i-2] : 0) + 2;
                } else if(i - dp[i-1] > 0 && s.charAt(i - dp[i-1] - 1) == '(') {
                    dp[i] = dp[i-1] + (i-dp[i-1] >= 2 ? dp[i-dp[i-1]-2] : 0) + 2;
                }
            }
            max = Math.max(max, dp[i]);
        }

        return max;
    }

    /**
     * leetcode 44. 通配符匹配
     * @param s
     * @param p
     * '?' 可以匹配任何单个字符。
     * '*' 可以匹配任意字符串（包括空字符串）。
     * @return
     */
    public boolean isMatch(String s, String p) {
        int sn = s.length();
        int pn = p.length();
        boolean[][] dp = new boolean[pn+1][sn+1];
        dp[0][0] = true;
        for (int i = 1; i <= pn; i++) {
            if (p.charAt(i-1) == '*') {
                dp[i][0] = true;
            } else {
                break;
            }
        }

        for (int i = 1; i <= pn; i++) {
            char pc = p.charAt(i - 1);
            for (int j = 1; j <= sn; j++) {
                char sc = s.charAt(j - 1);
                if (pc == '*') {
                    dp[i][j] = dp[i - 1][j] || dp[i][j - 1];
                } else if (pc == sc || pc == '?') {
                    dp[i][j] = dp[i - 1][j - 1];
                }
            }
        }

        return dp[pn][sn];
    }

    /**
     * leetcode 63. 不同路径 II
     * @param obstacleGrid
     * @return
     */
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid[0].length;
        int n = obstacleGrid.length;
        int[][] memo = new int[n][m];
        return move(obstacleGrid, 0, 0, memo);
    }

    public int move(int[][] obstacleGrid, int x, int y, int[][] memo) {
        int count = 0;
        if (obstacleGrid[x][y] == 1) {
            return 0;
        }

        if (x == obstacleGrid.length - 1 && y == obstacleGrid[0].length - 1) {
            return 1;
        }

        if (memo[x][y] != 0) {
            return memo[x][y];
        }

        if (x < obstacleGrid.length - 1) {
            count += move(obstacleGrid, x + 1, y, memo);
        }

        if (y < obstacleGrid[0].length - 1) {
            count += move(obstacleGrid, x, y+1, memo);
        }

        memo[x][y] = count;
        return count;
    }

    /**
     * leetcode 63. 不同路径 II(动态规划)
     * @param obstacleGrid
     * @return
     */
    public int uniquePathsWithObstacles1(int[][] obstacleGrid) {
        int m = obstacleGrid[0].length;
        int n = obstacleGrid.length;
        int[] f = new int[m];
        f[0] = obstacleGrid[0][0] == 0 ? 1 : 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (obstacleGrid[i][j] == 1) {
                    f[j] = 0;
                    continue;
                }
                if (j - 1 >= 0 && obstacleGrid[i][j-1] == 0) {
                    f[j] += f[j-1];
                }
            }
        }
        return f[m - 1];
    }

    /**
     * leetcode 112. 路径总和
     * @param root
     * @param sum
     * @return
     */
    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) {
            return false;
        }

        if (root.left == null && root.right == null) {
            return sum == root.value;
        }

        return hasPathSum(root.left, sum - root.value) || hasPathSum(root.right, sum - root.value);
    }

    /**
     * leetcode 面试题 16.11. 跳水板
     * @param shorter
     * @param longer
     * @param k
     * @return
     */
    public int[] divingBoard(int shorter, int longer, int k) {
        if (k == 0) {
            return new int[0];
        }

        if (shorter == longer || shorter == 0) {
            return new int[] {longer * k};
        }

        int[] lengths = new int[k+1];
        for (int i = 0; i <= k; i++) {
            lengths[i] = shorter * (k - i) + longer * i;
        }
        return lengths;
    }

    /**
     * leetcode 面试题 17.13. 恢复空格
     * @param dictionary
     * @param sentence
     * @return
     */
    public int respace(String[] dictionary, String sentence) {
        Trie root = new Trie();
        for (int i = 0; i < dictionary.length; i++) {
            root.insert(dictionary[i]);
        }

        int n = sentence.length();
        int[] dp = new int[n + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        for (int i = 1; i <= n; i++) {
            dp[i] = dp[i-1] + 1;

            Trie node = root;
            for (int j = i; j >= 1; j--) {
                int index = sentence.charAt(j - 1) - 'a';
                if (node.next[index] == null) {
                    break;
                } else if (node.next[index].isEnd) {
                    dp[i] = Math.min(dp[i], dp[j-1]);
                }

                if (dp[i] == 0) {
                    break;
                }
                node = node.next[index];
            }
        }

        return dp[n];
    }

    /**
     * leetcode 120.三角形最小路径和
     * @param triangle
     * @return
     */
    public int minimumTotal(List<List<Integer>> triangle) {
        int size = triangle.size();
        int dp[] = new int[size];

        dp[0] = triangle.get(0).get(0);
        for (int i = 1; i < size; i++) {
            List<Integer> row = triangle.get(i);
            int min = row.get(0) + dp[i-1];
            for (int j = 1; j < row.size(); j++) {
                min = Math.min(min, row.get(j) + dp[i-1]);
            }
            dp[i] = min;
        }

        return dp[size - 1];
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

        String text = ")(()(())";
        System.out.println("longestValidParentheses of \"" + text + "\" is " + solution202007.longestValidParentheses1(text));
        int[][] obstacleGrid = {{0, 0, 0},{0, 1, 0},{0, 0, 0}};
        System.out.println("path count = " + solution202007.uniquePathsWithObstacles1(obstacleGrid));

        String ss = "adceb";
        String ps = "*a*b";
        System.out.println("\""+ ps +"\" matches \"" + ss + "\" = " + solution202007.isMatch(ss, ps));//true
        ss = "acdcb";
        ps = "a*c?b";
        System.out.println("\""+ ps +"\" matches \"" + ss + "\" = " + solution202007.isMatch(ss, ps));//false
        ss = "aa";
        ps = "a";
        System.out.println("\""+ ps +"\" matches \"" + ss + "\" = " + solution202007.isMatch(ss, ps));//false
        ss = "aa";
        ps = "*";
        System.out.println("\""+ ps +"\" matches \"" + ss + "\" = " + solution202007.isMatch(ss, ps));//true
        ss = "cb";
        ps = "?a";
        System.out.println("\""+ ps +"\" matches \"" + ss + "\" = " + solution202007.isMatch(ss, ps));//false
        ss = "mississippi";
        ps = "m??*ss*?i*pi";
        System.out.println("\""+ ps +"\" matches \"" + ss + "\" = " + solution202007.isMatch(ss, ps));//false
        ss = "";
        ps = "*";
        System.out.println("\""+ ps +"\" matches \"" + ss + "\" = " + solution202007.isMatch(ss, ps));//true

        System.out.println("divingBoard = " + solution202007.divingBoard(1, 2, 3));

        String s = "jesslookedjustliketimherbrother";
        String[] dictionary = {"looked", "just", "like", "her", "brother"};
        System.out.println("respace count = " + solution202007.respace(dictionary, s));
    }
}
