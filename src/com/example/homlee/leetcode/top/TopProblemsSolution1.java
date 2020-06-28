package com.example.homlee.leetcode.top;

import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

public class TopProblemsSolution1 {

    /**
     * leetcode 7. 整数反转
     * @param x
     * @return
     */
    public int reverse(int x) {
        Queue<Integer> integers = new ArrayBlockingQueue<>(16);
        while (x != 0) {
            integers.add(x % 10);
            x /= 10;
        }

        long value = 0;
        while (!integers.isEmpty()) {
            value = value * 10 + integers.remove();
        }

        if (value > Integer.MAX_VALUE || value < Integer.MIN_VALUE) {
            return 0;
        }

        return (int) value;
    }

    /**
     * leetcode 7. 整数反转
     * @param x
     * @return
     */
    public int reverse1(int x) {
        long value = 0;
        while (x != 0) {
            int a = x % 10;
            value = value * 10 + a;
            x /= 10;
        }

        if (value > Integer.MAX_VALUE
                || value < Integer.MIN_VALUE) {
            return 0;
        }

        return (int) value;
    }


    public static void main(String[] args) {
        TopProblemsSolution1 solution1 = new TopProblemsSolution1();
        int num = -120;
        System.out.println("reverse \"" + num + "\" ---> " + solution1.reverse1(num));
    }

}
