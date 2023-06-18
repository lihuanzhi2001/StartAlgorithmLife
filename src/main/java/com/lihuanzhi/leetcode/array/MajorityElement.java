package com.lihuanzhi.leetcode.array;

/**
 * 多数元素（众数）
 * （2023/05/28 18:08:59）
 *
 * 给定一个大小为 n 的数组 nums ，返回其中的多数元素。多数元素是指在数组中出现次数 大于 ⌊ n/2 ⌋ 的元素。
 * 你可以假设数组是非空的，并且给定的数组总是存在多数元素。
 *
 * 进阶：尝试设计时间复杂度为 O(n)、空间复杂度为 O(1) 的算法解决此问题。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/majority-element
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class MajorityElement {
    public static void main(String[] args) {

    }

    public int majorityElement(int[] nums) {
        return majorityElementRec(nums, 0, nums.length - 1);
    }

    private int majorityElementRec(int[] nums, int lo, int hi) {
        // 基本情况；大小为1的数组中唯一的元素是多数
        if (lo == hi) {
            return nums[lo];
        }

        // 在这个切片的左半部分和右半部分上下弯
        int mid = (hi - lo) / 2 + lo;
        int left = majorityElementRec(nums, lo, mid);
        int right = majorityElementRec(nums, mid + 1, hi);

        // 如果两半在多数元素上一致，则返回
        if (left == right) {
            return left;
        }

        // 否则，计算每个元素并返回“获胜者”
        int leftCount = countInRange(nums, left, lo, hi);
        int rightCount = countInRange(nums, right, lo, hi);

        return leftCount > rightCount ? left : right;
    }

    private int countInRange(int[] nums, int num, int lo, int hi) {
        int count = 0;
        for (int i = lo; i <= hi; i++) {
            if (nums[i] == num) {
                count++;
            }
        }
        return count;
    }

    /**
     * 根据官方的思路来写，不是我想到的，只能说很牛比
     */
    public int majorityElement1(int[] nums) {
        return bianli(nums, 0, nums.length - 1);
    }

    /**
     * 递归查找众数，
     * @param nums
     * @param left
     * @param right
     * @return
     */
    public int bianli(int[] nums, int left, int right) {
        // 数组只剩一个元素时，这个元素就是该数组的众数
        if (left == right) return nums[left];

        // 数组中间位置
        int mid = left + ((right - left) / 2);

        // 递归查找左半部分的众数
        int lzhong = bianli(nums, left, mid);
        // 递归查找右半部分的众数
        int rzhong = bianli(nums, mid + 1, right);

        // 如果两边的众数相等，那么直接返回
        if (lzhong == rzhong) return lzhong;

        // 如果两边的众数不相等，那么就计算这两个疑似众数的家伙在数组范围中出现的次数
        int lcount = count(nums, lzhong, left, right);
        int rcount = count(nums, rzhong, left, right);

        // 那么众数就是他们中间较大那个，如果两个相等也不用管，随便返回一个就好了
        return lcount > rcount ? lcount : rcount;
    }

    public int count(int[] nums, int num, int left, int right) {
        int count = 0;
        for (int i = left; i <= right; i++) {
            if (nums[i] == num) {
                count++;
            }
        }
        return count;
    }

    /**
     * 更牛逼的写法：投票算法
     *
     * 评论区中的大神解释到 -------------------------------------
     * “同归于尽消杀法” ：
     * 由于多数超过50%, 比如100个数，那么多数至少51个，剩下少数是49个。
     *
     *     第一个到来的士兵，直接插上自己阵营的旗帜占领这块高地，此时领主 winner 就是这个阵营的人，现存兵力 count = 1。
     *     如果新来的士兵和前一个士兵是同一阵营，则集合起来占领高地，领主不变，winner 依然是当前这个士兵所属阵营，现存兵力 count++；
     *     如果新来到的士兵不是同一阵营，则前方阵营派一个士兵和它同归于尽。 此时前方阵营兵力count --。（即使双方都死光，这块高地的旗帜 winner 依然不变，因为已经没有活着的士兵可以去换上自己的新旗帜）
     *     当下一个士兵到来，发现前方阵营已经没有兵力，新士兵就成了领主，winner 变成这个士兵所属阵营的旗帜，现存兵力 count ++。
     *
     * 就这样各路军阀一直以这种以一敌一同归于尽的方式厮杀下去，直到少数阵营都死光，那么最后剩下的几个必然属于多数阵营，winner 就是多数阵营。
     * （多数阵营 51个，少数阵营只有49个，死剩下的2个就是多数阵营的人）
     *
     * ----------------------------------------------------------
     */
    public int majorityElement2(int[] nums) {
        int winner = nums[0];
        int count = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == winner) {
                count++;
            } else if (count == 0) {
                winner = nums[i];
                count = 1;
            } else {
                count--;
            }
        }
        return winner;
    }
}
