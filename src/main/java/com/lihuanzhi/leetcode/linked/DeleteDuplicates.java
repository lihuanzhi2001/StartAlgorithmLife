package com.lihuanzhi.leetcode.linked;

/**
 * 删除排序链表中的重复元素（2023/05/22 22:18:07）
 */
public class DeleteDuplicates {
    public static void main(String[] args) {

    }

    /**
     * 思路： 双指针
     * 保存第一个非重复节点，找到后续非重复node
     */
    public ListNode deleteDuplicates(ListNode head) {
        // 上一个非重复节点指针
        ListNode pre = new ListNode(head.val, null), resultNode = pre;
        // 下一个非重复节点指针
//        ListNode next = head;

//        while (next != null) {
//            if (next.val != pre.val) {
//                pre.next = new ListNode(next.val,null);
//                pre = pre.next;
//            }
//            next = next.next;
//        }

        while (pre != null) {
            if (pre.val != pre.next.val) {
                pre = pre.next;
            } else {
                pre.next = pre.next.next;
            }
        }

        return resultNode;
    }
}
