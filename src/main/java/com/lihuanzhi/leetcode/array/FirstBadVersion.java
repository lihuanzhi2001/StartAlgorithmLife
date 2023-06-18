package com.lihuanzhi.leetcode.array;

/**
 * 第一个错误的版本
 *
 * @Author: lihuanzhi
 * @Date: 2023/6/18 08:50:39
 */
public class FirstBadVersion {
    /**
     * 看到这题我就感觉应该使用双指针 + 二分查找
     */
    public int firstBadVersion(int n) {
        // 中间数
        int mid = n / 2;

        // 首先得确保leftRight必须是正确的版本
        while (isBadVersion(mid)) {
            mid = mid / 2;
        }

        // 假设左边是正确的，右边是错误的
        int leftRight = mid;
        int rightError = n;

        // 相差1
        while (!(rightError - leftRight == 1)) {

            // 注意这里，很可能会超出int的最大范围，从而导致一直循环下去，会进入死循环
            mid = (rightError - leftRight) / 2 + leftRight;

            // 是false的话，就移动left
            if (!isBadVersion(mid)) {
                leftRight = mid;
            }
            // 是true的话，就移动right
            else {
                rightError = mid;
            }
        }
        return rightError;
    }

    /**
     * 这是LeetCode上自带的用于判断当前版本是否错误的方法
     * 我没有他的具体实现，所以这里就直接返回就好了
     * 主要是避免idea编译不通过...
     *
     */
    boolean isBadVersion(int version) {
        return false;
    }
}
