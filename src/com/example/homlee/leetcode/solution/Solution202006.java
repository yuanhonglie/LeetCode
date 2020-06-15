package com.example.homlee.leetcode.solution;

public class Solution202006 {

    /**
     *20200613 leetcode70 爬楼梯
     * @param n
     * @return
     */
    public long climbStairs(int n) {
        if (n <= 2) {
            return n;
        }

        return climbStairs(n - 1) + climbStairs(n - 2);
    }

    /**
     * 爬楼梯，递归+备忘录
     * @param n
     * @param memo
     * @return
     */
    public long climbStairsWithMemo(int n, long[] memo) {
        if (n <= 2) {
            return n;
        }

        if (memo[n-1] != 0) {
            return memo[n-1];
        }

        long num = climbStairsWithMemo(n - 1, memo) + climbStairsWithMemo(n - 2, memo);
        memo[n-1] = num;

        return num;
    }

    /**
     * 爬楼梯，状态转移表（DP数组）
     * @param n
     * @return
     */
    public long climbStairsWithArray(int n) {
        long[] array = new long[n + 1];
        array[1] = 1;
        array[2] = 2;
        for (int i = 3; i <= n; i++) {
            array[i] = array[i - 1] + array[i - 2];
        }
        return array[n];
    }

    /**
     * 爬楼梯，状态转移表（DP数组）
     * @param n
     * @return
     */
    public long climbStairsWithArray1(int n) {
        if (n <= 2) {
            return n;
        }

        int p = 1, q = 2, m = 0;
        for (int i = 3; i <= n; i++) {
            m = p + q;
            p = q;
            q = m;
        }
        return m;
    }

    /**
     * 20200615 leetcode14 最长前缀子串
     */
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        int length = strs[0].length();
        int count = strs.length;
        for (int i = 0; i < length; i++) {
            char c = strs[0].charAt(i);
            for (int j = 1; j < count; j++) {
                if (i == strs[j].length() || strs[j].charAt(i) != c) {
                    return strs[0].substring(0, i);
                }
            }
        }
        return strs[0];
    }

    public static void main(String[] args) {
        Solution202006 solution202006 = new Solution202006();
        String[] input1 = {"flower", "flow", "flight"};
        String prefix1 = solution202006.longestCommonPrefix(input1);
        System.out.println("common prefix is " + prefix1);

        String[] input2 = {"dog", "racecar", "car"};
        String prefix2 = solution202006.longestCommonPrefix(input2);
        System.out.println("common prefix is " + prefix2);
        long start = System.currentTimeMillis();
        int i = 40;
        System.out.println("爬" + i + "个台阶总共有多少种爬法：" + solution202006.climbStairs(i));
        long time1 = System.currentTimeMillis();
        long[] memo = new long[i];
        System.out.println("爬" + i + "个台阶总共有多少种爬法：" + solution202006.climbStairsWithMemo(i, memo));
        long time2 = System.currentTimeMillis();
        System.out.println("爬" + i + "个台阶总共有多少种爬法：" + solution202006.climbStairsWithArray(i));
        long time3 = System.currentTimeMillis();
        System.out.println("爬" + i + "个台阶总共有多少种爬法：" + solution202006.climbStairsWithArray1(i));
        long time4 = System.currentTimeMillis();
        System.out.println("执行时间：" + (time1 - start) + ", " + (time2 - time1) + ", " + (time3 - time2) + ", " + (time4 - time3));

    }

}
