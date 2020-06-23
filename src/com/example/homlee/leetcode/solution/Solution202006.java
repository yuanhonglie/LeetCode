package com.example.homlee.leetcode.solution;

import com.example.homlee.leetcode.data.TreeNode;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

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

    /**
     * 20200618 leetcode3 无重复字符的最长子串
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring(String s) {
        int max = 0;
        int k = 0;
        int start = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            int c = s.charAt(i);
            Integer j = map.get(c);
            if (j == null) {
                k++;
            } else {
                start = j > start ? j : start;
                k = i - start;
            }
            map.put(c, i);
            max = max >= k ? max : k;
        }

        return max;
    }

    /**
     * 20200619 leetcode 125 验证回文串
     * @param s "A man, a plan, a canal: Panama"
     * @return true
     */
    private boolean isPalindrome(String s) {
        if (s == null || s.length() == 0) {
            return true;
        }
        int i = 0;
        int j = s.length() - 1;

        while (i < j) {
            while (i < j && !Character.isLetterOrDigit(s.charAt(i))) {
                i++;
            }

            while (j > i && !Character.isLetterOrDigit(s.charAt(j))) {
                j--;
            }

            if (Character.toLowerCase(s.charAt(i)) == Character.toLowerCase(s.charAt(j))) {
                i++;
                j--;
            } else {
                return false;
            }
        }

        return true;
    }

    /**
     * 20200619
     * leetcode 1028 先序遍历还原二叉树
     * @param s "1-2--3--4-5--6--7"
     * @return
     */
    public TreeNode recoverFromPreorder(String s) {
        Stack<TreeNode> stack = new Stack<>();
        int i = 0;
        int level = 0;//结点的层数
        while (i < s.length()) {
            int depth = 0;
            while(s.charAt(i) == '-') {
                depth++;
                i++;
            }

            int value = 0;
            while (i < s.length() && Character.isDigit(s.charAt(i))) {
                value = value * 10 + s.charAt(i) - '0';
                i++;
            }

            TreeNode node = new TreeNode(value);
            if (depth >= level) {
                if (!stack.isEmpty()) {
                    stack.peek().left = node;
                }
            } else {
                while (level > depth) {
                    stack.pop();
                    level--;
                }
                stack.peek().right = node;
            }

            stack.push(node);
            level++;
        }

        while (stack.size() > 1) {
            stack.pop();
        }

        return stack.peek();
    }

    /**
     * 20200622 leetcode 8 字符串转换整数
     * @param text
     * @return
     */
    private int atoi(String text) {
        if (text == null) {
            return 0;
        }

        int i = 0;
        while (i < text.length() && text.charAt(i) == ' ') {
            i++;
        }

        int j = i;
        long value = 0;
        int sign = 1;
        while ( j < text.length()) {
            int ch = text.charAt(j);
            if (Character.isDigit(ch)) {
                value = value * 10 + ch - '0';
                if (value > Integer.MAX_VALUE) {
                    return sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
                }
            } else {
                if (j == i) {
                    if (ch == '-') {
                        sign = -1;
                    } else if (ch == '+') {
                        sign = 1;
                    } else {
                        break;
                    }
                } else {
                    break;
                }
            }
            j++;
        }

        return sign * (int)value;
    }

    /**
     *
     * @param pattern
     * @param value
     * @return
     */
    public boolean patternMatching(String pattern, String value) {
        return false;
    }

    /**
     * leetcode 10正则表达式匹配
     * @param s
     * @param p
     * @return
     */
    public boolean isMatch(String s, String p) {
        int sn = s.length();
        int pn = p.length();
        boolean[][] dp = new boolean[pn+1][sn+1];

        dp[0][0] = true;
        for (int i = 2; i <= pn; i++) {
            dp[i][0] = p.charAt(i-1) == '*' ? dp[i-2][0] : false;
        }

        for (int i = 1; i <= pn; i++) {
            for (int j = 1; j <= sn; j++) {
                char pCh = p.charAt(i-1);
                char sCh = s.charAt(j-1);
                if (pCh == '*') {
                    if (i == 1) {
                        dp[i][j] = pCh == sCh;
                    } else {
                        dp[i][j] = (dp[i-1][j-1] || dp[i][j-1]) && matches(p.charAt(i - 2), sCh) || dp[i-2][j];
                    }
                } else {
                    dp[i][j] = dp[i-1][j-1] && matches(pCh, sCh);
                }
            }
        }

        return dp[pn][sn];
    }

    private boolean matches(char pCh, char sCh) {
        return pCh == sCh || pCh == '.';
    }

    /**
     * 20200623 leetcode 67 二进制求和
     * @param a
     * @param b
     * @return
     */
    public String addBinary(String a, String b) {
        StringBuilder sb = new StringBuilder();
        int i = 0;
        int carry = 0;
        int max = Math.max(a.length(), b.length());
        while (i < max) {
            carry += i < a.length() ? a.charAt(a.length() - 1 - i) - '0' : 0;
            carry += i < b.length() ? b.charAt(b.length() - 1 - i) - '0' : 0;

            sb.append(carry % 2 == 0 ? '0' : '1');
            carry /= 2;
            i++;
        }

        if (carry == 1) {
            sb.append('1');
        }
        sb.reverse();
        return sb.toString();
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
        String word1 = "abcabcbb";
        System.out.println("\"" + word1 + "\"不含有重复字符的最长子串的长度是" + solution202006.lengthOfLongestSubstring(word1));
        word1 = "bbbb";
        System.out.println("\"" + word1 + "\"不含有重复字符的最长子串的长度是" + solution202006.lengthOfLongestSubstring(word1));
        word1 = "pwwkew";
        System.out.println("\"" + word1 + "\"不含有重复字符的最长子串的长度是" + solution202006.lengthOfLongestSubstring(word1));
        word1 = "abba";
        System.out.println("\"" + word1 + "\"不含有重复字符的最长子串的长度是" + solution202006.lengthOfLongestSubstring(word1));

        String input = "A man, a plan, a canal: Panama";
        //String input = "race a car";
        System.out.println(input + " --> " + solution202006.isPalindrome(input));

        //input = "1-2--3--4-5--6--7";
        input = "1-2--3---4-5--6---7";
        TreeNode node = solution202006.recoverFromPreorder(input);
        System.out.println(input);
        Solution202005.levelorderPrint(node);

        System.out.println();
        /* */
        input = "42";
        System.out.println("\"" + input + "\" atoi is " + solution202006.atoi(input));
        input = "    -42";
        System.out.println("\"" + input + "\" atoi is " + solution202006.atoi(input));
        input = "4193 with words";
        System.out.println("\"" + input + "\" atoi is " + solution202006.atoi(input));
        input = "words and 987";
        System.out.println("\"" + input + "\" atoi is " + solution202006.atoi(input));
        input = "-91283472332";
        System.out.println("\"" + input + "\" atoi is " + solution202006.atoi(input));

        input = "2147483648";
        System.out.println("\"" + input + "\" atoi is " + solution202006.atoi(input));

        String s = "aa";
        String p = "a";
        System.out.println("\"" + s + "\" matches \"" + p + "\" : " + solution202006.isMatch(s, p));

        s = "aa";
        p = "a*";
        System.out.println("\"" + s + "\" matches \"" + p + "\" : " + solution202006.isMatch(s, p));

        s = "ab";
        p = ".*";
        System.out.println("\"" + s + "\" matches \"" + p + "\" : " + solution202006.isMatch(s, p));

        s = "aab";
        p = "c*a*b*";
        System.out.println("\"" + s + "\" matches \"" + p + "\" : " + solution202006.isMatch(s, p));

        s = "mississippi";
        p = "mis*is*p*.";
        System.out.println("\"" + s + "\" matches \"" + p + "\" : " + solution202006.isMatch(s, p));

        s = "ab";
        p = ".*c";
        System.out.println("\"" + s + "\" matches \"" + p + "\" : " + solution202006.isMatch(s, p));

        s = "aaa";
        p = "ab*ac*a";
        System.out.println("\"" + s + "\" matches \"" + p + "\" : " + solution202006.isMatch(s, p));

        s = "*";
        p = "**";
        System.out.println("\"" + s + "\" matches \"" + p + "\" : " + solution202006.isMatch(s, p));

        String a = "1010";
        String b = "1011";
        System.out.println("\"" + a + "\" + \"" + b + "\" = " + solution202006.addBinary(a, b));
    }

}
