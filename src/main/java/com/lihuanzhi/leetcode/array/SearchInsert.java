package com.lihuanzhi.leetcode.array;

/**
 * 搜索插入位置（2023/05/21 10:02:34）
 * 题目要求必须使用时间复杂度为O(logn)的算法
 */
public class SearchInsert {
    public static void main(String[] args) {
        int arr[] = new int[]{1,3,5,6};
        System.out.println(searchInsert(arr, 7));
    }

    /**
     * 思路：二分查找法
     * 双指针，left左边界指针，right右边界指针
     */
    public static int searchInsert(int[] nums, int target) {
        if (nums.length <= 0) {
            return 0;
        }
        // 左边界指针
        int left = 0;
        // 右边界指针
        int right = nums.length - 1;
        // 中间指针
        int mid = (left + right) / 2;
        // 这里当left <= right时也是成立的，这里很容易犯错
        while (left <= right) {
            mid = (left + right)/2;
            if (nums[mid] == target) {
                return mid;
            }
            if (nums[mid] < target) {
                left = mid + 1;
                continue;
            }
            if (nums[mid] > target) {
                right = mid - 1;
                continue;
            }
        }
        return target < nums[mid] ? mid : mid + 1;
    }
}
