package com.lihuanzhi.leetcode.linked;

/**
 * 移除链表元素（2023/05/30 19:08:48）
 *
 */
public class RemoveElements {
    public static void main(String[] args) {

    }

    /**
     * 思路：
     * 一、先找到第一个非val节点，找到该节点后，把头节点赋值给他
     * 二、循环跳过val节点
     *
     */
    public ListNode removeElements(ListNode head, int val) {
        // 移动指针
        ListNode h = head;
        // 找到第一个非val节点
        while (h != null) {
            if (h.val != val) {
                break;
            }
            h = h.next;
            head = h;
        }

        if (h == null) {
            return head;
        }

        // 当前h节点已经不是val节点了
        while (h.next != null) {
            // 下一节点是val节点
            if (h.next.val == val) {
                h.next = h.next.next;
            }
            // 不是val节点时下移
            else {
                h = h.next;
            }
        }
        return head;
    }

    /**
     * 我看到题解里面有使用递归的，所以这里我尝试一下
     */
    public ListNode removeElements1(ListNode head, int val) {
        // 遍历到末尾了，直接跳过
        if (head == null) {
            return null;
        }
        head.next = removeElements1(head.next, val);
        if (head.val == val) {
            return head.next;
        } else {
            return head;
        }
    }
}
