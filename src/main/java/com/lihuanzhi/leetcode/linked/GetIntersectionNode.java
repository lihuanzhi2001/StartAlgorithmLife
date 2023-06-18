package com.lihuanzhi.leetcode.linked;

import java.util.HashSet;
import java.util.Set;

/**
 * 相交链表 (2023/05/27 16:54:43)
 *
 * 给你两个单链表的头节点 headA 和 headB ，
 * 请你找出并返回两个单链表相交的起始节点。
 * 如果两个链表不存在相交节点，返回 null
 *
 */
public class GetIntersectionNode {
    public static void main(String[] args) {

    }

    /**
     * 用一个set集合保存a、b链表的每个节点，当出现第一个无法正常插入的节点时
     * 该节点就是他们的相交节点
     *
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        Set<ListNode> set = new HashSet<>();
        // a链表头节点
        ListNode a = headA;
        // b链表头节点
        ListNode b = headB;

        // 第一轮不用考虑无法插入情况
        while (a != null) {
            set.add(a);
            a = a.next;
        }
        // 第二轮需要考虑
        while (b != null) {
            // 不成功就返回该节点
            if (!set.add(b)) {
                return b;
            }
            b = b.next;
        }
        // 没找到就返回null
        return null;
    }

    /**
     * 思路2：
     *      双层for循环
     *      （感觉这个方法还不如上面那个）
     */
    public ListNode getIntersectionNode1(ListNode headA, ListNode headB) {

        // a链表头节点
        ListNode a = headA;
        // b链表头节点
        ListNode b = headB;

        while (a != null) {
            ListNode tb = b;
            while (tb != null) {
                if (a == tb) return a;
                tb = tb.next;
            }
            a = a.next;
        }

        // 没找到就返回null
        return null;
    }

    /**
     * 思路3：
     *      同时遍历 a链表 和 b链表 ，有一个遍历到链表末尾时停止移动
     *      另外一个链表继续移动，如果 a b 链表的节点相等且他们已经到链表末尾了
     *      那么，他们直接的移动距离就是他们两个链表链表的长度差了，
     *      如果上面已经在末尾了，仍然不相等，那么他们就没有相交节点
     *
     *      计算出长度差之后，长链表就跳过前面长度差的节点，从两个链表长度相等的节点位置开始比较
     *      知道比较出相等的两个节点，直接返回
     *
     */
    public ListNode getIntersectionNode2(ListNode headA, ListNode headB) {
        // a链表头节点
        ListNode a = headA;
        // b链表头节点
        ListNode b = headB;

        // a节点移动距离
        int al = 1;
        // b节点移动距离
        int bl = 1;

        while (a.next != null || b.next != null) {
            // a节点 等于 b节点 且他们移动的距离相等
            if (a == b && al == bl) {
                return a;
            }
            if (a.next != null) {
                a = a.next;
                al++;
            }
            if (b.next != null) {
                b = b.next;
                bl++;
            }
        }

        // 如果末尾节点都不相等的话，那么就可以直接判断两个链表没有相相交节点
        if (a != b) return null;

        // 已经计算出a b 链表的长度差了，首先先判断哪个链表长
        ListNode maxLink = al > bl ? headA : headB;
        ListNode minLink = al > bl ? headB : headA;

        // 让长链表先移动
        for (int i = 0; i < Math.abs(al - bl); i++) {
            maxLink = maxLink.next;
        }

        // 现在 maxLink 和 minLink 长度相等了
        while (maxLink != null || minLink != null) {
            // a节点 等于 b节点 且他们移动的距离相等
            if (maxLink == minLink) {
                return maxLink;
            }
            maxLink = maxLink.next;
            minLink = minLink.next;
        }

        return null;
    }
}
