package com.lihuanzhi.leetcode.other;

import java.text.BreakIterator;

/**
 * 求x的算术平方根（其实就是开根号），但这个只保留整数部分
 * 2023/05/21 22:32:40
 */
public class NumSqrt {
    public static void main(String[] args) {

    }

    /**
     * 思路：
     * 求 int a = x/2; a*a > x
     */
    public int mySqrt(int x) {
        // 只考虑 [0 - x/2] 部分的数

        // 左指针
        int left = 0;
        // 右指针
        int right = x / 2 <= 0 ? 1 : x / 2;
        // 中间指针
        int mid;
        // 最后一个符合的整数
        int lastNum = -1;

        while (left <= right) {
            mid = (left + right) / 2;
            // 如果mid*mid == x 则直接返回mid的值
            // 之前的写法，这里是有问题的
            // 如果mid * mid 的值大于了int的最大范围，则会出现问题...
            if (mid * mid == x) {
                return mid;
            }

            // 如果mid * mid < x 则记录当前mid的值为lastNum
            // TODO 注意:
            // 如果mid * mid 的值大于了int的最大范围，则会出现问题...
            // 这里我好几次都没注意到
            if ((mid * mid) < x) {
                lastNum = mid;
                left = mid + 1;
                continue;
            }
            if ((mid * mid) > x) {
                right = mid - 1;
                continue;
            }
        }
        return lastNum;
    }

    public int mySqrt1(int x) {
        // 如果x为0则直接返回0；
        if (x == 0) {
            return 0;
        }
        // 只考虑 [0 - x/2] 部分的数

        // 左指针 跳过0，直接从1开始
        int left = 1;
        // 右指针
        int right = x / 2 <= 0 ? 1 : x / 2;
        // 中间指针
        int mid;
        // 最后一个符合的整数
        int lastNum = -1;

        while (left <= right) {
            mid = (left + right) / 2;
            // 如果 (x / mid == mid) && (x % mid == 0) 则直接返回mid的值
            if ((x / mid == mid) && (x % mid == 0)) {
                return mid;
            }
            // 如果 (x / mid >= mid) 则表示 (mid * mid < x) 记录当前mid的值为lastNum
            if ((x / mid) >= mid) {
                lastNum = mid;
                left = mid + 1;
//                continue;
            } else {
                right = mid - 1;
            }
//            // 如果 (x / mid < mid) 表示 (mid * mid > x)
//            if ((x / mid) < mid) {
//                right = mid - 1;
//                continue;
//            }
        }
        return lastNum;
    }
}
