package com.lihuanzhi.leetcode.linked;

/**
 * 判断链表中是否右环
 * （2023/05/27 15:41:59）
 */
public class HasCycle {
    public static void main(String[] args) {

    }

    /**
     * 思路：
     * 双指针延迟移动，前指针qp先移动，后指针hp延迟后移动，如果前指针qp能追上后指针hp，则表示这是一个环
     *
     * 拓展问题：--> 最后一个问题，如果存在环，如何判断环的长度呢？
     * 答案：--> 方法是，快慢指针相遇后继续移动，直到第二次相遇。两次相遇间的移动次数即为环的长度。
     */
    public boolean hasCycle(ListNode head) {
        if (head == null) return false;
        ListNode qp = head.next;
        ListNode hp = head;

        // 后指针移动标识
        int flag = 0;
        while (qp != null) {
            // qp 追上了 hp
            if (qp == hp) {
                return true;
            }

            // 判断hp是否可以移动，没三轮移动一次
            // 其实让前指针每次比后指针移动的快一步的时间复杂度是一样的
            if (flag % 3 == 0) {
                hp = hp.next;
            }

            // 标识相加
            flag++;

            // qp前指针后移
            qp = qp.next;
        }
        // 遍历到空了直接返回false,不是一个环
        return false;
    }

    /**
     * 官方答案：
     *      快慢指针
     *
     * 解释一下：
     * 这个官方的写法明明想法跟我的一样，但是他的时间消耗却能击败100%的人？
     * 而我的每3次移动一次却只能击败22%的人？为什么，很怪.
     *
     * 拓展问题：--> 最后一个问题，如果存在环，如何判断环的长度呢？
     * 答案：--> 方法是，快慢指针相遇后继续移动，直到第二次相遇。两次相遇间的移动次数即为环的长度。
     */
    public boolean hasCycle1(ListNode head) {
        if (head == null) return false;
        ListNode qp = head.next;
        ListNode hp = head;

        while (qp != null) {
            qp = qp.next;
            if (qp == null) {
                return false;
            }
            // qp 追上了 hp
            if (qp == hp) {
                return true;
            }
            // 后指针移动
            hp = hp.next;
            // qp前指针移动
            qp = qp.next;
        }
        // 遍历到空了直接返回false,不是一个环
        return false;
    }
}
