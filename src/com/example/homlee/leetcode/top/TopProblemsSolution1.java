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

    int count = 0;

    /**
     * leetcode 215. 数组中的第K个最大元素
     * @param nums
     * @param k
     * @return
     */
    public int findKthLargest(int[] nums, int k) {
        int[] heap = new int[k + 1];
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            if (!insert(heap, num)) {
                if (num > heap[1]) {
                    replaceTop(heap, num);
                }
            }
        }

        return heap[1];
    }

    private boolean insert(int[] heap, int data) {
        if (count == heap.length - 1) {
            return false;
        }
        count++;
        heap[count] = data;
        int i = count;
        //自下而上堆化
        while (i/2 > 0 && heap[i] < heap[i/2]) {
            swap(heap, i, i/2);
            i = i/2;
        }
        return true;
    }

    private boolean replaceTop(int[] heap, int data) {
        heap[1] = data;
        heapify(heap, 1, count);
        return true;
    }

    /**
     * 从上往下堆化
     * @param heap
     * @param i
     * @param n
     */
    private void heapify(int[] heap, int i, int n) {
        while (true) {
            int pos = i;

            if (i*2<=n && heap[i] >= heap[i*2]) {
                pos = i*2;
            }

            if (i*2+1<=n && heap[pos] >= heap[i*2+1]) {
                pos = i*2+1;
            }

            if (pos == i) {
                return;
            }

            swap(heap, pos, i);
            i = pos;
        }
    }

    private void swap(int[] heap, int i, int j) {
        int tmp = heap[i];
        heap[i] = heap[j];
        heap[j] = tmp;
    }


    public static void main(String[] args) {
        TopProblemsSolution1 solution1 = new TopProblemsSolution1();
        int num = -120;
        System.out.println("reverse \"" + num + "\" ---> " + solution1.reverse1(num));

        int[] nums = new int[]{3,2,1,5,6,4};
        System.out.println("findKthLargest: " + solution1.findKthLargest(nums, 2));
    }

}
