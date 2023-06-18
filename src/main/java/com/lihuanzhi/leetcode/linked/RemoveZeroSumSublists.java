package com.lihuanzhi.leetcode.linked;

/**
 * 从链表中删去总和值为零的连续节点
 *
 * @Author: lihuanzhi
 * @Date: 2023/6/11 20:57:01
 */
public class RemoveZeroSumSublists {

    public ListNode removeZeroSumSublists(ListNode head) {
        // 临时头节点
        ListNode tempHead = new ListNode(0, head);

        // 左指针
        ListNode leftNode = tempHead;
        // 右指针
        ListNode rightNode = head;

        // 和
        int num = 0;

        while (leftNode.next != null) {
            // 是否遇到和为0的标记
            boolean flag = false;

            while (rightNode != null) {
                num += rightNode.val;
                // 总和为0，结束循环
                if (num == 0) {
                    flag = true;
                    break;
                }
                // 总和不为0，继续
                rightNode = rightNode.next;
            }

            // 如果找到了的话
            if (flag) {
                leftNode.next = rightNode.next;
                rightNode = rightNode.next;
            }
            // 没找到，
            else {
                leftNode = leftNode.next;
                rightNode = leftNode.next;
            }
            // 找没找到都得归0
            num = 0;
        }
        return tempHead.next;
    }
}
