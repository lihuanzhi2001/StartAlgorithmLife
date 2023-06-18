package com.lihuanzhi.leetcode.other;

/**
 * 是否是丑数
 *
 * 就是只包含质因数 2、3 和 5 的正整数。
 * 给你一个整数 n ，请你判断 n 是否为 丑数 。
 * 如果是，返回 true ；否则，返回 false
 *
 * @Author: lihuanzhi
 * @Date: 2023/6/17 23:58:08
 */
public class IsUgly {
    public boolean isUgly(int n) {
        if (n == 1) {
            return true;
        }
        // 如果除以2能除得尽
        if (n % 2 == 0) {
            return isUgly(n / 2);
        }
        // 能除得尽5
        else if (n % 5 == 0) {
            return isUgly(n / 5);
        }
        // 能除得尽3
        else if (n % 3 == 0) {
            return isUgly(n / 3);
        }
        return isUgly(n);
    }

}
