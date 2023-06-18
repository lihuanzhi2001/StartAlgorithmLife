package com.lihuanzhi.leetcode.other;

/**
 * 爬楼梯问题
 * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
 * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
 */
public class ClimbStairs {
    public static void main(String[] args) {
        int i = climbStairs(35);
        System.out.println(i);
    }

    /**
     * 思路：
     * 假如需要 x 个 1阶, 那么 2阶 的个数为 y = (n - x) / 2
     * 所用只需要 x 的整数取值，但是 y 的返回值也是整数即可
     * 即：(n - x) / 2) % 2 == 0 即可
     * x 个 1，y 个 2
     * 当x = 0时，只有一种；当y = 0时，也只有一种
     * 当x != 0 或 y != 0 时都是排列组合
     * 例如：
     *  n = 10 时  -->
     *  1 1 2 2 2 2
     *  1 2 1 2 2 2
     *  . . . . . .
     *
     *  n = 5 时  -->
     *  1 2 2
     *  2 1 2
     *  2 2 1
     *  1 1 1 1 1
     *  . . . . . .
     *
     */
    public static int climbStairs(int n) {
        // 1阶 的个数
//        int x = 0;
        // 2阶 的个数
//        int y = 0;

        // 符合的次数
        int count = 0;
        for (int x = 0; x <= n; x++) {
            // 如果整数
            if ((n - x) % 2 == 0) {
                // 这是所有都为1 或者 所有都为2 的情况
                if (x == 0 || x == n) {
                    count++;
                    continue;
                }
                // 2阶 的个数
                int y = (n - x) / 2;
                // 1阶 和 2阶 中的最小数
                int min = x > y ? y : x;

                // 计算排列组合 （C_下zong_上min)
                int zong = x + y;

                // 总num
                long zongNum = zong;
                // 分子num
                long fenziNum = min;
                while (min > 1) {
                    zongNum *= (--zong);
                    fenziNum *= (--min);
                    while ((zongNum % 2 == 0) && (fenziNum % 2 == 0)) {
                        zongNum /= 2;
                        fenziNum /= 2;
                    }
                }

                // 计算排列组合
                count += (zongNum / fenziNum);
            }
        }
        return count;
    }

    /**
     * 官方解法：
     * 动态规划思想（就是斐波那契数列）
     *
     * 因为要爬到n阶就只能从n-1阶到达或者n-2阶到达，所有爬到n阶的方案数为爬到n-1和n-2的方案和。
     * 即 f(n) = f(n-1) + f(n-2)
     *
     * 这就是动态规划的思想，即后一项等于之前的项之和（不一定是挨在一起的）
     */
    public int climbStairs1(int n) {
        /**
         * p : n -2
         * q : n - 1
         * r : p + q
         */
        int p = 0, q = 0, r = 1;
        for (int i = 1; i <= n; i++) {
            p = q;
            q = r;
            r = p + q;
        }
        return r;
    }

}
