package com.lihuanzhi.leetcode.linked;

/**
 * 反转链表（2023/05/30 22:56:44）
 */
public class ReverseList {
    public static void main(String[] args) {
        ReverseList r = new ReverseList();
    }

    public ListNode reverseList(ListNode head) {
        // 遍历到尾节点是直接返回用该节点作为头结点
        if (head == null || head.next == null) {
            return head;
        }
        ListNode newHead = reverseList(head.next);
        head.next.next = head;
        head.next = null;
        return newHead;
    }
}
