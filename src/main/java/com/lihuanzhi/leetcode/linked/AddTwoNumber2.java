package com.lihuanzhi.leetcode.linked;

/**
 * 20230519
 * 思路：双指针循环 add
 */
public class AddTwoNumber2 {
    public static void main(String[] args) {

    }
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
       // 进位
        int jinwei = 0;
        // 返回ListNode;
        ListNode returnListNode = new ListNode();
        // 指针ListNode
        ListNode nextNode = returnListNode;

        int l1Val = 0;
        int l2Val = 0;
        while (true) {
            l1Val = 0;
            l2Val = 0;

            if (l1 != null) {
                l1Val = l1.val;
            }
            if (l2 != null) {
                l2Val = l2.val;
            }

            // l1.val + l2.val + 进位
            int count = l1Val + l2Val + jinwei;
            // 个位 + 进位
            nextNode.val = count % 10;
            // 求进位
            jinwei = count / 10;

            // l1有下一个
            if (l1 != null) {
                l1 = l1.next;
            }
            // l2有下一个
            if (l2 != null) {
                l2 = l2.next;
            }

            /**
             * 注意：进位如果加1时如果l1和l2的next都为空了
             * 此时也不能直接退出循环，应该继续循环，让nextNode直接赋值为该进位
             */
            if (l1 == null && l2 == null && jinwei == 0) {
                break;
            }
            nextNode.next = new ListNode();
            nextNode = nextNode.next;
        }
        return returnListNode;
    }
}
