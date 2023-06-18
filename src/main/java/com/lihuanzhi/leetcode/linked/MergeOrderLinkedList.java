package com.lihuanzhi.leetcode.linked;

/*
将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。

示例 1：
输入：l1 = [1,2,4], l2 = [1,3,4]
输出：[1,1,2,3,4,4]

示例 2：
输入：l1 = [], l2 = []
输出：[]

示例 3：
输入：l1 = [], l2 = [0]
输出：[0]

提示：
    两个链表的节点数目范围是 [0, 50]
    -100 <= Node.val <= 100
    l1 和 l2 均按 非递减顺序 排列

来源：力扣（LeetCode）
链接：https://leetcode.cn/problems/merge-two-sorted-lists
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

/**
 * 合并两个有序序列（2023/05/20 23:10:19）
 * 思路：
 * 1个第一个序列的指针，1个第二个序列的指针
 */
public class MergeOrderLinkedList {
    public static void main(String[] args) {

    }

    /**
     *这里虽然我开始写的时候是让nextNode = resultListNode的
     * 但是后续我让nextNode重新指向了一个新的ListNode对象，这似乎是不对的（可能这样会截断resultListNode和nextNode的指向吧）
     * 应该修改他们的val和next值，而不是让他重新指向新的对象
     */
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        // 返回链表头结点
        ListNode resultListNode = new ListNode();
        // 下一节点
        ListNode nextNode = resultListNode;
        // 标记空链表
        boolean flag = false;
        while (true) {
            if (list1 == null && list2  == null) {
                flag = true;
                break;
            }
            // 如果list1已经为空，但list2不为空，则把list2的剩余节点保存接到后续后续节点中
            if (list1 == null && list2 != null) {
                nextNode.val  = list2.val;
                nextNode.next = list2.next;
                break;
            }
            // 如果list2已经为空，list1不为空，则把list1的后续节点接到后续剩余节点
            if (list2 == null && list1 != null) {
                nextNode.val = list1.val;
                nextNode.next = list1.next;
                break;
            }

            // list1的节点值小于list2时，list1后移一位，
            if (list1.val <= list2.val) {
                nextNode.val = list1.val;
                nextNode.next = new ListNode();
                list1 = list1.next;
            } else {
                nextNode.val = list2.val;
                nextNode.next = new ListNode();
                list2 = list2.next;
            }
            nextNode = nextNode.next;
        }
        if (flag) {
            return null;
        }
        return resultListNode;
    }
}
