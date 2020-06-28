package com.example.homlee.leetcode.solution;

import com.example.homlee.leetcode.data.TreeNode;

import java.util.*;

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

    /**
     * leetcode 16 最接近的三数之和
     * @param nums
     * @param target
     * @return
     */
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int closest = 0;
        int num1, num2, num3, sum;

        for (int i = 0; i < nums.length - 2; i++) {
            num1 = nums[i];

            int j = i + 1;
            int k = nums.length - 1;
            if (i == 0) {
                closest = num1 + nums[j] + nums[k];
            }
            while (j < k) {
                num2 = nums[j];
                num3 = nums[k];
                sum = num1 + num2 + num3;
                if (Math.abs(target - sum) < Math.abs(target - closest)) {
                    closest = sum;
                }

                if (sum < target) {
                    while (j + 1 < k && nums[j] == nums[j+1]) {
                        j++;
                    }
                    j++;
                } else if (sum > target) {
                    while (k - 1 > j && nums[k] == nums[k-1]) {
                        k--;
                    }
                    k--;
                } else {
                    return target;
                }
            }
        }


        return closest;
    }

    /**
     * leetcode 15 三数之和为0
     * @param nums
     * @return
     */
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> list = new ArrayList<>();
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }

            int k = nums.length - 1;
            for (int j = i + 1; j < k; j++) {
                if (j > i + 1 && nums[j] == nums[j - 1]) {
                    continue;
                }

                while (k > j && nums[i] + nums[j] + nums[k] > 0) {
                    k--;
                }

                if (j == k) {
                    break;
                }

                if (nums[i] + nums[j] + nums[k] == 0) {
                    List<Integer> l = new ArrayList<>();
                    l.add(nums[i]);
                    l.add(nums[j]);
                    l.add(nums[k]);
                    list.add(l);
                }
            }
        }

        return list;
    }


    /**
     * leetcode 15 三数之和为0
     * @param nums
     * @return
     */
    public List<List<Integer>> threeSum1(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);
        List<List<Integer>> ans = new ArrayList<List<Integer>>();
        // 枚举 a
        for (int first = 0; first < n; ++first) {
            // 需要和上一次枚举的数不相同
            if (first > 0 && nums[first] == nums[first - 1]) {
                continue;
            }
            // c 对应的指针初始指向数组的最右端
            int third = n - 1;
            int target = -nums[first];
            // 枚举 b
            for (int second = first + 1; second < n; ++second) {
                // 需要和上一次枚举的数不相同
                if (second > first + 1 && nums[second] == nums[second - 1]) {
                    continue;
                }
                // 需要保证 b 的指针在 c 的指针的左侧
                while (second < third && nums[second] + nums[third] > target) {
                    --third;
                }
                // 如果指针重合，随着 b 后续的增加
                // 就不会有满足 a+b+c=0 并且 b<c 的 c 了，可以退出循环
                if (second == third) {
                    break;
                }
                if (nums[second] + nums[third] == target) {
                    List<Integer> list = new ArrayList<Integer>();
                    list.add(nums[first]);
                    list.add(nums[second]);
                    list.add(nums[third]);
                    ans.add(list);
                }
            }
        }
        return ans;
    }

    /**
     * leetcode 11. 盛最多水的容器
     * @param height
     * @return
     */
    public int maxArea(int[] height) {
        int max = 0;
        int i = 0;
        int j = height.length - 1;
        while (i < j) {
            max = Math.max(Math.min(height[i], height[j]) * (j - i), max);
            if (height[i] < height[j]) {
                i++;
            } else {
                j--;
            }
        }
        return max;
    }

    /**
     * leetcode 165 比较版本号
     * @param version1
     * @param version2
     * @return 如果 version1 > version2 返回 1，如果 version1 < version2 返回 -1，除此之外返回 0。
     */
    public int compareVersion(String version1, String version2) {
        String[] vnums1 = version1.split("\\.");
        String[] vnums2 = version2.split("\\.");

        int n = vnums1.length >= vnums2.length ? vnums1.length : vnums2.length;
        for (int i = 0; i < n; i++) {
            String text1 = i < vnums1.length ? vnums1[i] : null;
            String text2 = i < vnums2.length ? vnums2[i] : null;

            if (text2Num(text1) < text2Num(text2)) {
                return -1;
            } else if (text2Num(text1) > text2Num(text2)) {
                return 1;
            }
        }

        return 0;
    }

    int text2Num(String text) {
        if (text == null) {
            return 0;
        }

        int value = 0;
        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);
            value = value * 10 + ch - '0';
        }

        return value;
    }

    /**
     * leetcode 209. 长度最小的子数组
     * @param s
     * @param nums
     * @return
     */
    public int minSubArrayLen(int s, int[] nums) {
        int left = 0;
        int right = 0;
        int sum = 0;
        int min = Integer.MAX_VALUE;
        while (right < nums.length) {
            if (sum < s) {
                sum += nums[right];
                right++;
            } else {
                if (left < right) {
                    sum -= nums[left];
                    left++;
                } else {
                    sum += nums[right];
                    right++;
                }
            }

            if (sum >= s && right - left < min) {
                min = right - left;
            }
        }

        while (sum >= s && left < right) {
            sum -= nums[left];
            left++;
            if (sum >= s) {
                if (right - left < min) {
                    min = right - left;
                }
            }
        }

        return min == Integer.MAX_VALUE ? 0 : min;
    }


    /**
     * leetcode 209. 长度最小的子数组
     * @param s
     * @param nums
     * @return
     */
    public int minSubArrayLen1(int s, int[] nums) {
        int n = nums.length;
        if (n == 0) {
            return 0;
        }
        int ans = Integer.MAX_VALUE;
        int start = 0, end = 0;
        int sum = 0;
        while (end < n) {
            sum += nums[end];
            while (sum >= s) {
                ans = Math.min(ans, end - start + 1);
                sum -= nums[start];
                start++;
            }
            end++;
        }
        return ans == Integer.MAX_VALUE ? 0 : ans;
    }

    /**
     * leetcode 209. 长度最小的子数组
     * @param s
     * @param nums
     * @return
     */
    public int minSubArrayLen2(int s, int[] nums) {
        int n = nums.length;
        if (n == 0) {
            return 0;
        }
        int ans = Integer.MAX_VALUE;
        int[] sums = new int[n + 1];
        // 为了方便计算，令 size = n + 1
        // sums[0] = 0 意味着前 0 个元素的前缀和为 0
        // sums[1] = A[0] 前 1 个元素的前缀和为 A[0]
        // 以此类推
        for (int i = 1; i <= n; i++) {
            sums[i] = sums[i - 1] + nums[i - 1];
        }
        for (int i = 1; i <= n; i++) {
            int target = s + sums[i - 1];
            int bound = Arrays.binarySearch(sums, target);
            if (bound < 0) {
                bound = -bound - 1;
            }
            if (bound <= n) {
                ans = Math.min(ans, bound - (i - 1));
            }
        }
        return ans == Integer.MAX_VALUE ? 0 : ans;
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


        int[] nums = new int[] {-1, 2, 1, -4};
        int target = 1;
        System.out.println("closet threesum is " + solution202006.threeSumClosest(nums, target));

        nums = new int[] {-1, 0, 1, 1, 55};
        target = 3;
        System.out.println("closet threesum is " + solution202006.threeSumClosest(nums, target));

        nums = new int[] {3,0,-2,-1,1,2};
        System.out.println("threesum equals zero ---> " + solution202006.threeSum(nums));
        int[] heights = new int[] {1,8,6,2,5,4,8,3,7};
        System.out.println("maxArea is ---> " + solution202006.maxArea(heights));


        String version1 = "1.0";
        String version2 = "1.0.0";
        System.out.println("\"" + version1 + "\" compare to \"" + version2 + "\" is " + solution202006.compareVersion(version1, version2));

        target = 15;
        nums = new int[]{1, 2, 3, 4, 5};
        System.out.println("num =  " + solution202006.minSubArrayLen2(target, nums));

        target = 7;
        nums = new int[]{2,3,1,2,4,3};
        System.out.println("num =  " + solution202006.minSubArrayLen2(target, nums));
    }

}
