package com.example.homlee.leetcode;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * leetcode 20,155,232,844,224,682,496
 */
public class StackTest {

    Map<Character, Character> mMap = new HashMap<>();
    public StackTest() {
        mMap.put(')', '(');
        mMap.put('}', '{');
        mMap.put(']', '[');
    }

    /**
     * leetcode 20 有效的括号
     * @param s
     * @return
     */
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        int i;
        for (i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch == '('
                    || ch == '['
                    || ch == '{') {
                stack.push(ch);
            } else {
                char pCh = stack.isEmpty() ? '#' : stack.pop();
                if (pCh != '(' && ch == ')'
                        || pCh != '[' && ch == ']'
                        || pCh != '{' && ch == '}') {
                    break;
                }
            }
        }


        return i == s.length();
    }


    public boolean isValid1(String s) {
        if (s.length() % 2 == 1) {
            return false;
        }
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (mMap.containsKey(ch)) {
                char pCh = stack.isEmpty() ? '#' : stack.pop();
                if (pCh != mMap.get(ch)) {
                    return false;
                }
            } else {
                stack.push(ch);
            }
        }


        return stack.isEmpty();
    }


    public static void main(String[] args) {
        StackTest test = new StackTest();
        //String s = "(}";
        String s = "{[]}";
        System.out.println("\"" + s + "\" is valid string? " + test.isValid1(s));
    }
}
