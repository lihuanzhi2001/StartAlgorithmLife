package com.lihuanzhi.leetcode.other;

import java.util.HashMap;
import java.util.Map;

/**
 * 判断一个数是否为快乐数（2023/05/28 23:08:10）
 */
public class IsHappy {
    public static void main(String[] args) {

    }

    /**
     * 思路：
     * 判断是否有环的存在，我之前不是会写吗，难道换成抽象的问题就不会写了吗？
     *
     * 判断是否有环，且只使用O(1)的空间复杂度，使用双指针延迟移动（快慢指针）就好了
     *
     */
    public boolean isHappy(int n) {
        Map<Integer, Integer> map = new HashMap<>();
        while (true) {
            if (n == 1) {
                return true;
            }

            // 进入环之后就直接返回false
            if (map.get(n) != null) return false;
            // 当前值进环
            map.put(n, 1);

            int count = 0;
            while (n != 0) {
                count += (n % 10) * (n % 10);
                n /= 10;
            }
            n = count;
        }
    }
}
