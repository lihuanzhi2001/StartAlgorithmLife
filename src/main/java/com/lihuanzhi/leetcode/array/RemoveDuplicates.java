package com.lihuanzhi.leetcode.array;

/**
 * 删除数组中的重复项（2023/05/21 09:05:44）
 */
public class RemoveDuplicates {
    public static void main(String[] args) {

    }

    /**
     *思路：双指针，一个指向当前需要修改替换的位置，一个指向后续不等于前一个元素的位置
     */
    public int removeDuplicates(int[] nums) {
        // 数组元素个数小于1的话直接返回0
        if (nums.length <= 0) {
            return 0;
        }
        // 当前需要修改替换元素的指针位置
        int current = 0;
        // 元素个数，元素遍历从第一个元素开始，所有这里直接赋值为1
        int count = 1;
        for (int i = 1; i < nums.length; i++) {
            // 如果nums[i] != nums[current] 则current指针下移且等于当前i元素，
            if (nums[i] != nums[current]) {
                nums[++current] = nums[i];
                count++;
            }
            // 如果nums[i] == nums[current] 则i指针后移
        }
        return count;
    }
}
