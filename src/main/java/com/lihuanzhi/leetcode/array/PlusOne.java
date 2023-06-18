package com.lihuanzhi.leetcode.array;

/*
给定一个由 整数 组成的 非空 数组所表示的非负整数，在该数的基础上加一。
最高位数字存放在数组的首位， 数组中每个元素只存储单个数字。
你可以假设除了整数 0 之外，这个整数不会以零开头。

示例 1：
输入：digits = [1,2,3]
输出：[1,2,4]
解释：输入数组表示数字 123。

示例 2：
输入：digits = [4,3,2,1]
输出：[4,3,2,2]
解释：输入数组表示数字 4321。

示例 3：
输入：digits = [0]
输出：[1]

提示：
    1 <= digits.length <= 100
    0 <= digits[i] <= 9

来源：力扣（LeetCode）
链接：https://leetcode.cn/problems/plus-one
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

/**
 * 一个整数数组 + 1
 */
public class PlusOne {
    public static void main(String[] args) {

    }

    /**
     * 思路：
     * 从数组的末尾开始 + 1
     * 当前位为 (进位 + 当前值) % 10
     * 进位为 (进位 + 当前值) / 10
     * 如果非个位数的进位为0时则退出循环
     */
    public int[] plusOne(int[] digits) {
        // 进位
        int jinwei = 0;
        for (int i = digits.length - 1; i >= 0; i--) {
            // 个位数时 进位+1
            if (i == digits.length - 1) {
                jinwei++;
            }
            // 没有进位时 直接推荐循环
            if (jinwei == 0) {
                break;
            }
            int count = digits[i] + jinwei;
            // 当前位值为
            digits[i] = count % 10;
            // 进位值
            jinwei = count / 10;
        }
        // 注意：这里如果进位不为空，但是数组已经遍历完了，此时需要新建一个数据把这个新数组返回
        if (jinwei > 0) {
            int nums[] = new int[digits.length + 1];
            nums[0] = jinwei;
            for (int i = 1; i < nums.length; i++) {
                nums[i] = digits[i-1];
            }
            return nums;
        }
        return digits;
    }
}
