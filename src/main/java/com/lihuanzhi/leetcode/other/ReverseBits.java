package com.lihuanzhi.leetcode.other;

/**
 * 二进制颠倒（2023/05/28 21:56:22）
 */
public class ReverseBits {
    public static void main(String[] args) {

    }

    public int reversBits(int n) {
        char[] carr = new char[32];
        int index = 0;
        int reverNum = 0;

        // 是否为负数
        boolean flag = false;
        if (n < 0) {
            flag = true;
        }

        while (n != 0) {
            char yushu = (char)(n % 2 + 48);
            if (flag) yushu ^= 1;

            carr[index++] = yushu;
            n /= 2;
        }
        // 进制
        int jinzhi = 1;

        for (int i = carr.length - 1; i >= 0; i--) {
            /**
             * 注意：
             * 这里很坑，卡了我好久，脑子短路了，没考虑到未填充的字符就是“空格字符”，对应的ascii码为0
             */
            if (carr[i] != '0' && carr[i] != 0) {
                reverNum += jinzhi * (carr[i] - 48);
            }
            // 进位
            jinzhi *= 2;
        }
        return reverNum;
    }
}
