package com.example.homlee.leetcode;

import java.util.Arrays;

/**
 * 动态规划
 */
public class DynamicProgramming {
    /**
     * leetcode 322 零钱兑换(备忘录)
     * @param coins
     * @param amount
     * @return
     */
    public int coinChange(int[] coins, int amount, int[] memo) {
        if (amount == 0) {
            return 0;
        }
        for (int i = 0; i < coins.length; i++) {
            int coin = coins[i];
            int remain = amount - coin;
            if (remain == 0) {
                return 1;
            } else if (remain < 0) {
                return -1;
            }
        }

        if (memo[amount] != 0) {
            return memo[amount];
        }

        int min = Integer.MAX_VALUE;
        for (int i = 0; i < coins.length; i++) {
            int coin = coins[i];
            int count = coinChange(coins, amount - coin, memo) + 1;
            if (count > 0) {
                min = Math.min(min, count);
            }
        }

        memo[amount] = min == Integer.MAX_VALUE ? -1 : min;


        return memo[amount];
    }

    /**
     * leetcode 322 零钱兑换
     * @param coins
     * @param amount
     * @return
     */
    public int coinChange1(int[] coins, int amount) {
        if (amount == 0) {
            return 0;
        }
        for (int i = 0; i < coins.length; i++) {
            int coin = coins[i];
            int remain = amount - coin;
            if (remain == 0) {
                return 1;
            } else if (remain < 0) {
                return -1;
            }
        }

        int min = Integer.MAX_VALUE;
        for (int i = 0; i < coins.length; i++) {
            int coin = coins[i];
            int count = coinChange1(coins, amount - coin) + 1;
            if (count > 0) {
                min = Math.min(min, count);
            }
        }

        return min == Integer.MAX_VALUE ? -1 : min;
    }

    /**
     * leetcode 322 零钱兑换(状态转换表)
     * @param coins
     * @param amount
     * @return
     */
    public int coinChange2(int[] coins, int amount) {
        if (amount == 0) {
            return 0;
        }
        Arrays.sort(coins);
        int row = amount / coins[0] + 1;
        boolean dp[][] = new boolean[row][amount + 1];
        for (int i = 0; i < coins.length; i++) {
            if (coins[i] <= amount) {
                dp[0][coins[i]] = true;
            }
        }

        if (dp[0][amount]) {
            return 1;
        }

        for (int i = 1; i < row; i++) {
            for (int j = coins[0]; j <= amount; j++) {
                if (dp[i - 1][j] == true) {
                    for (int k = 0; k < coins.length; k++) {
                        int c = j + coins[k];
                        if (c < amount) {
                            dp[i][c] = true;
                        } else if (c == amount) {
                            return i + 1;
                        } else {
                            break;
                        }
                    }
                }
            }
        }

        return -1;
    }


    /**
     * leetcode 322 零钱兑换(状态转换表)
     * @param coins
     * @param amount
     * @return
     */
    public int coinChange3(int[] coins, int amount) {
        if (amount == 0) {
            return 0;
        }
        boolean dp[][] = new boolean[amount + 1][amount + 1];
        for (int i = 0; i < coins.length; i++) {
            if (coins[i] <= amount) {
                dp[0][coins[i]] = true;
            }
        }

        if (dp[0][amount]) {
            return 1;
        }

        for (int i = 1; i <= amount; i++) {
            for (int j = 1; j <= amount; j++) {
                if (dp[i - 1][j]) {
                    for (int k = 0; k < coins.length; k++) {
                        int c = j + coins[k];
                        if (c < amount) {
                            dp[i][c] = true;
                        } else if (c == amount) {
                            return i + 1;
                        }
                    }
                }
            }
        }

        return -1;
    }

    /**
     * leetcode 322 零钱兑换(状态转换表)
     * @param coins
     * @param amount
     * @return
     */
    public int coinChange4(int[] coins, int amount) {
        int max = amount + 1;
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, max);
        dp[0] = 0;
        for (int i = 1; i <= amount; i++) {
            for (int j = 0; j < coins.length; j++) {
                if (coins[j] <= i) {
                    dp[i] = Math.min(dp[i], dp[i - coins[j]] + 1);
                }
            }
        }

        return dp[amount] > amount ? -1 : dp[amount];
    }

    /**
     * leetcode 72 编辑距离
     * 给你两个单词 word1 和 word2，请你计算出将 word1 转换成 word2 所使用的最少操作数 。
     * 你可以对一个单词进行如下三种操作：
     *
     * 插入一个字符
     * 删除一个字符
     * 替换一个字符
     * @param word1
     * @param word2
     * @return
     */
    public int minDistance(String word1, String word2) {

        return -1;
    }

    public static void main(String[] args) {
        DynamicProgramming dp = new DynamicProgramming();

        int count = 11;
        int[] coins = {1, 2, 5};
        int[] memo = new int[count + 1];
        System.out.println("count = " + dp.coinChange(coins, count, memo));
        System.out.println("count = " + dp.coinChange1(coins, count));
        System.out.println("count = " + dp.coinChange2(coins, count));
        System.out.println("count = " + dp.coinChange3(coins, count));
        System.out.println("count = " + dp.coinChange4(coins, count));

        int count1 = 3;
        int[] coins1 = {2};
        int[] memo1 = new int[count + 1];
        System.out.println("count = " + dp.coinChange(coins1, count1, memo1));
        System.out.println("count = " + dp.coinChange1(coins1, count1));
        System.out.println("count = " + dp.coinChange2(coins1, count1));
        System.out.println("count = " + dp.coinChange3(coins1, count1));
        System.out.println("count = " + dp.coinChange4(coins1, count1));


    }
}
