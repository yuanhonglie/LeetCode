package com.example.homlee.leetcode;

import java.util.List;

public class ListNodeTest {

    public static class ListNode<T> {
        T value;
        ListNode next;
        public ListNode() { }

        public ListNode(T value) {
            this.value = value;
        }
    }

    //判断回文
    public boolean isPalindrome(ListNode head) {
        ListNode prev = null;
        ListNode slow = head;
        ListNode fast = head;
        ListNode next;
        //先用快慢指针法，找到链表的中间节点
        //在遍历的过程中，反转前半部分链表
        while(fast != null && fast.next != null) {
            fast = fast.next.next;
            next = slow.next;
            slow.next = prev;
            prev = slow;
            slow = next;
        }

        //说明链表的长度为单数，做回文判断时，不需要对比最中间的那个节点，所以这里跳到下一个节点；
        if (fast != null) {
            slow = slow.next;
        }

        //从中间往两边逐个对比，当出现不相等的情况，说明不是回文
        while (prev != null) {
            if (prev.value != slow.value) {
                return false;
            }
            prev = prev.next;
            slow = slow.next;
        }

        return true;
    }




    //leetcode 206反转链表 迭代
    private ListNode reverseList(ListNode node) {
        if (node == null) {
            return null;
        }

        ListNode prev = null;
        ListNode next = null;
        while (node != null) {
            //暂存当前结点的下一结点；
            next = node.next;
            //将当前结点指向前一个结点；
            node.next = prev;
            //将当前结点赋值给prev暂存起来；
            prev = node;
            //将下一结点指定给node；
            node = next;
        }

        //最后一个不为null的结点就是反转链表的头
        return prev;
    }

    //leetcode 206反转链表 递归
    private ListNode reverseList1(ListNode head) {
        //节点或节点下一个节点为null，则返回此节点，此节点将作为链表的头节点
        if (head == null || head.next == null) {
            return head;
        }
        //运行到这里，head.next一定是不为null
        ListNode p = reverseList1(head.next);
        //只要当前节点的下一节点(head.next)不为空，则将下一节点(head.next)的下一节点（head.next.next）指向当前节点head，反转链表；
        head.next.next = head;
        //当前节点的下一节点指向null
        head.next = null;
        //p作为链表的头节点，返回
        return p;
    }

    //leetcode 141链表中环的检测
    public boolean hasCycle(ListNode head) {
        //空链表，没有环，返回false
        if (head == null) {
            return false;
        }

        ListNode fast = head;
        ListNode slow = head;

        //只需检测快指针是否为null
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            //如果快慢指针相遇，说明有环
            if (slow == fast) {
                return true;
            }
        }

        //循环因为节点为null退出，说明没有环
        return false;
    }

    //leetcode 21两个有序的链表合并
    ListNode<Integer> mergeList(ListNode<Integer> node1, ListNode<Integer> node2) {
        //任意一个链表为空，返回另一个链表
        if (node1 == null) {
            return node2;
        }

        //任意一个链表为空，返回另一个链表
        if (node2 == null) {
            return node1;
        }

        //哨兵节点
        ListNode<Integer> head = new ListNode<>(0);
        //当前节点
        ListNode<Integer> node = head;
        //遇到任意一个链表结束，就停止
        while (node1 != null && node2 != null) {
            //将下一个元素指向两个值更小的那一个，移动对应的链表指针
            if (node1.value < node2.value) {
                node.next = node1;
                node1 = node1.next;
            } else {
                node.next = node2;
                node2 = node2.next;
            }
            node = node.next;
        }

        //最后最多只有一个还未被完全合并，直接将链表指定到未合并的链表即可
        node.next = node1 == null ? node2 : node1;

        return head.next;
    }

    //leetcode 19删除链表倒数第n个结点
    public ListNode<Integer> removeNthFromEnd(ListNode<Integer> head, int n) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode first = dummy;
        ListNode second = dummy;
        // Advances first pointer so that the gap between first and second is n nodes apart
        //这里为什么是n+1呢，因为要删除第n个节点，所以必须定位到倒数第n个节点的前一个节点；
        //执行完毕之后，first和second之间间隔n个节点；
        for (int i = 0; i < n + 1; i++) {
            first = first.next;
        }
        // Move first to the end, maintaining the gap
        //当first指到结尾（null）时，second刚好指向倒数第n+1个节点；
        while (first != null) {
            first = first.next;
            second = second.next;
        }
        //删除second的下一个节点
        second.next = second.next.next;

        return dummy.next;
    }

    //leetcode 876求链表的中间结点 快慢指针
    public ListNode middleNode(ListNode head) {
        ListNode slow = head, fast = head;
        //这里只需要判断快指针是否为null
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    //leetcode 876求链表的中间结点 数组法
    public ListNode middleNode1(ListNode head) {

        return null;
    }

    //leetcode 876求链表的中间结点 两次遍历
    public ListNode middleNode2(ListNode head) {

        return null;
    }

    private static void print(ListNode node) {
        while (node != null) {
            System.out.print(node.value);
            node = node.next;
        }
        System.out.println();
    }

    private static ListNode text2ListNode(String text) {
        ListNode head = new ListNode();
        ListNode node = head;
        for (int i = 0; i < text.length(); i++) {
            node.next = new ListNode(text.charAt(i));
            node = node.next;
        }

        return head.next;
    }

    private static ListNode createCycleListNode() {
        ListNode head = new ListNode('a');
        head.next = new ListNode('b');
        head.next.next = head;
        return head;
    }

    private static ListNode createListNode(int[] list) {
        ListNode head = new ListNode();
        ListNode node = head;
        for (int i = 0; i < list.length; i++) {
            node.next = new ListNode(list[i]);
            node = node.next;
        }

        return head.next;
    }

    public static void main(String[] args) {
        ListNodeTest test = new ListNodeTest();

        String text0 = "asdffdsa";
        String text = "asdfafdsa";

        ListNode node1 = text2ListNode(text);
        print(node1);

        boolean isPalindrome = test.isPalindrome(node1);
        System.out.println(text + " isPalindrome = " + isPalindrome);

        ListNode node0 = text2ListNode(text0);
        print(node0);

        boolean isPalindrome0 = test.isPalindrome(node0);
        System.out.println(text + " isPalindrome0 = " + isPalindrome0);

        String text1 = "abcdefghi";
        ListNode node2 = text2ListNode(text1);
        print(node2);
        ListNode reverseNode2 = test.reverseList1(node2);
        print(reverseNode2);
        System.out.println("isCycleList " + test.hasCycle(reverseNode2));
        System.out.println("isCycleList " + test.hasCycle(createCycleListNode()));
        int[] array1 = {1,2,4,5,8,9};
        int[] array2 = {1,3,4,6,7};
        ListNode<Integer> list1 = createListNode(array1);
        ListNode<Integer> list2 = createListNode(array2);
        print(list1);
        print(list2);
        ListNode<Integer> mergedList = test.mergeList(list1, list2);
        print(mergedList);

        int[] array = {1,2,3,4};
        ListNode<Integer> removedList = test.removeNthFromEnd(createListNode(array), 1);
        print(removedList);

        ListNode<Integer> middleNode = test.middleNode(createListNode(array));
        print(middleNode);

    }
}
