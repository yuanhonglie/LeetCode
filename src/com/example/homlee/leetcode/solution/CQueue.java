package com.example.homlee.leetcode.solution;

import java.util.Stack;

/**
 * 2020.6.30
 * 剑指 Offer 09. 用两个栈实现队列
 */
public class CQueue {

    private Stack<Integer> input = new Stack<>();
    private Stack<Integer> output = new Stack<>();
    public CQueue() { }

    public void appendTail(int value) {
        input.push(value);
    }

    public int deleteHead() {
        if (output.isEmpty()) {
            if (input.isEmpty()) {
                return -1;
            } else {
                while (!input.isEmpty()) {
                    output.push(input.pop());
                }
            }
        }

        return output.pop();
    }

    public static void main(String[] args) {
        CQueue queue = new CQueue();
        System.out.println("" + queue.deleteHead());
        queue.appendTail(5);
        queue.appendTail(2);
        System.out.println("" + queue.deleteHead());
        System.out.println("" + queue.deleteHead());
        System.out.println("=======");
        queue = new CQueue();
        queue.appendTail(3);
        System.out.println("" + queue.deleteHead());
        System.out.println("" + queue.deleteHead());
    }

}
