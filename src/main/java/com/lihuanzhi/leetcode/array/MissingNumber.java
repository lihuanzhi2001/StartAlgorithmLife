package com.lihuanzhi.leetcode.array;

/**
 * 丢失的数字
 *
 * @Author: lihuanzhi
 * @Date: 2023/6/18 08:39:14
 */
public class MissingNumber {
    /**
     * 思路： 找到 正确的数组和 - 现在的数组和，就能得到丢失的数字了
     */
    public int missingNumber(int[] nums) {
        // 正确的数组和
        int zCount = nums.length * (nums.length + 1) / 2;

        // 现在的数组和
        int cCount = 0;
        for (int i = 0; i < nums.length; i++) {
            cCount += nums[i];
        }

        return zCount - cCount;
    }
}
