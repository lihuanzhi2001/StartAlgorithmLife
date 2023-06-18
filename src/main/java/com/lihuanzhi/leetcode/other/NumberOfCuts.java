package com.lihuanzhi.leetcode.other;

/**
 * 分割圆的最小切割次数
 * 难度：简单
 *
 *
 * @Author: lihuanzhi
 * @Date: 2023/6/17 22:21:58
 */
public class NumberOfCuts {
    public int numberOfCuts(int n) {
        // 看他是奇数还是偶数
        if (n % 2 == 0) {
            return n / 2;
        } else {
            return n;
        }
    }
}
