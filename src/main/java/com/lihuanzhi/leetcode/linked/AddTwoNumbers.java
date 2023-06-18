package com.lihuanzhi.leetcode.linked;

/**
 * 给你两个 非空 的链表，表示两个非负的整数。它们每位数字都是按照 逆序 的方式存储的，并且每个节点只能存储 一位 数字。
 * 请你将两个数相加，并以相同形式返回一个表示和的链表。
 * 你可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 *
 * 示例 1：
 * 输入：l1 = [2,4,3], l2 = [5,6,4]
 * 输出：[7,0,8]
 * 解释：342 + 465 = 807.
 *
 * 示例 2：
 * 输入：l1 = [0], l2 = [0]
 * 输出：[0]
 *
 * 示例 3：
 * 输入：l1 = [9,9,9,9,9,9,9], l2 = [9,9,9,9]
 * 输出：[8,9,9,9,0,0,0,1]
 *
 * 提示：
 *
 *     每个链表中的节点数在范围 [1, 100] 内
 *     0 <= Node.val <= 9
 *     题目数据保证列表表示的数字不含前导零
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/add-two-numbers
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

/**
 * 郑桂先思路（20230519）
 * 但是因为这个链表数组无限长，长度超过int类型和long类型
 *
 */
public class AddTwoNumbers {
    public static void main(String[] args) {

    }
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        /**
         * 把l1链表转成整数数字
         * 每进一位就 x (10^n)
         */
        int i=1;
        int a=0;
        while (l1.next!=null){
            a+=(l1.val*i);
            i=i*10;
            l1=l1.next;
        }

        /**
         * 把l2链表转成整数数字
         * 每进一位就 x (10^n)
         */
        i=1;
        int b=0;
        while (l2.next!=null){
            b+=(l2.val*i);
            i=i*10;
            l2=l2.next;
        }
        /**
         * 把整数数字相加之后，位数如果相加能进位就可以进位 + 1
         */
        String c=(a+b)+"";
        // 把相加后的数字转成字符串数组
        char[] chars=c.toCharArray();
        ListNode listNode1=null;

        // 最后把这些字符串一次用ListNode连接
        for(char n : chars){
            if(listNode1==null){
                listNode1=new ListNode((int)n);

            }else {
                ListNode tlistNode= new ListNode((int)n,listNode1);
                listNode1=tlistNode;
            }

        }
        return listNode1;
    }
}
class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}
