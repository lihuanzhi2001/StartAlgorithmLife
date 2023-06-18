package com.lihuanzhi.leetcode.linked;

import org.omg.PortableServer.LIFESPAN_POLICY_ID;

/**
 * 回文链表（2023/06/05）
 *
 * 给你一个单链表的头节点 head ，请你判断该链表是否为回文链表。如果是，返回 true ；否则，返回 false 。
 */
public class IsPalindrome {
    int len = 0;    // 链表长度
    int cur = 0;    // 反转链表长度
    public boolean isPalindrome(ListNode head) {
        ListNode nh = fanzhuan(head);
        while (head != null && nh != null) {
            if (head.val != nh.val) {
                return false;
            }
            head = head.next;
            nh = nh.next;
        }
        return true;
    }
    /**
     * 思路：
     * 翻转后半部分的链表，使用递归翻转（不过递归太慢了，所以很难受）
     *
     * 一开始遍历链表，直到最后一个节点，然后开始翻转，翻转到中间时结束翻转
     */
    public ListNode fanzhuan(ListNode head) {
        len++;
        // 最后一个节点
        if (head.next == null) {
            cur++;
            return head;
        }
        ListNode nh = fanzhuan(head.next);
        if (len / 2 == cur) {
            return nh;
        }
        head.next.next = head;
        head.next = null;
        cur++;

        return nh;
    }

    /**
     * （官方解答）
     * 思路：
     * 保存一个当前节点，然后遍历到链表的最后，然后比较这两个节点
     * 如果相等的话，让其头节点下移，然后比较最后节点的上一个节点，一直比较
     */
    ListNode frontPonint = null;
    public boolean isPalindrome2(ListNode head) {
        frontPonint = head;
        return bijiao(head);
    }

    public boolean bijiao(ListNode head) {
        // 不是最后一个节点的话就比较
        if (head != null) {
            // 遍历到最后一个节点了，所以返回true了，所以这里是进不去了
            if (!bijiao(head.next)) {
                return false;
            }
            // 两个对称节点进行比较，不相等的话就返回false
            if (head.val != frontPonint.val) {
                return false;
            }
            // 相等就返回节点下移，这里不用返回true了，因为下面有了
            frontPonint = frontPonint.next;
        }
        // 是最后一个节点了，返回true
        return true;
    }

    /**
     * （官方更牛逼的解答：快慢指针）
     * 快慢指针的思想就是：快指针一次移动两个节点，慢指针一次移动1个节点
     * 等快指针移动到末尾时，慢指针移动到的节点位置就是中间节点的位置
     * 此时只需要翻转当前节点后续的链表就好了
     * 翻转之后就比较两个链表，如果有不相等的节点就直接返回false
     * 最后把返回后的后半部分链表翻转回来就好了（官方解答要翻转，很奇怪，感觉不用翻转）
     *
     */
    public boolean isPalindrome3(ListNode head) {
        // 找到链表的后半部的头节点
        ListNode houHalfHeadNode = getMidNode3(head);
        // 翻转后半部的链表
        ListNode fanzhuanHouHalf = fanzhuan3(houHalfHeadNode);

        // 链表也翻转好了，接下来就是比较两个链表了
        ListNode oneh = head;
        ListNode twoh = fanzhuanHouHalf;
        boolean flag = true;
        while (flag && twoh != null) {
            if (oneh.val != twoh.val) {
                flag = false;
            } else {
                oneh = oneh.next;
                twoh = twoh.next;
            }
        }
        // 翻转后面的链表
        fanzhuan3(fanzhuanHouHalf);
        return flag;
    }

    /**
     * 翻转链表
     */
    public ListNode fanzhuan3(ListNode head) {
        ListNode prev = null;    // 上一个节点
        ListNode curr = head;   // 当前节点
        while (curr != null) {
            ListNode nextNode = curr.next;
            curr.next = prev;
            prev = curr;
            curr = nextNode;
        }
        return prev;
    }

    /**
     * 返回链表的后半部的头节点
     */
    public ListNode getMidNode3(ListNode node) {
        ListNode slow = node;   // 慢指针
        ListNode fast = node;   // 快指针
        while (fast != null) {
            // 如果快指针的下一个节点为空
            if (fast.next == null) {
                fast = fast.next;
            } else {
                fast = fast.next.next;
            }
            slow = slow.next;
        }
        return slow;
    }
}
