package com.lihuanzhi.leetcode.array;

/*
给你一个数组 nums 和一个值 val，你需要 原地 移除所有数值等于 val 的元素，并返回移除后数组的新长度。
不要使用额外的数组空间，你必须仅使用 O(1) 额外空间并 原地 修改输入数组。
元素的顺序可以改变。你不需要考虑数组中超出新长度后面的元素

来源：力扣（LeetCode）
链接：https://leetcode.cn/problems/remove-element
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

/**
 * 原地删除数组中的指定元素
 * 思路：双指针
 */
public class RemoveElement {
    public static void main(String[] args) {

    }
    public int removeElement(int[] nums, int val) {
        // 空数组直接返回0
        if (nums.length <= 0) {
            return 0;
        }
        // 当前指针
        int current = 0;
        // 总个数
        // （我测试过了，在LeetCode里面如果不用这个count统计个数，返回current的话内存占用好像更高了）
        // 这是错误的，可能是网络问题吧，这返回current指针而不用count统计肯定是内存占用更少的才对
//        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            // i指针元素与要删除的元素相等则直接下移
            if (nums[i] != val) {
                nums[current++] = nums[i];
            }
        }
        return current;
}
}
