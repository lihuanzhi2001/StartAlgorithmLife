package com.lihuanzhi.leetcode.other;



/**
 * 二进制求和
 */
public class AddBinary {
    public static void main(String[] args) {
        String a = "11";
        String b = "1";
        String str = addBinary(a, b);
        System.out.println(str);
    }

    /**
     * 思路:
     * 每个字符串采用逆序的方式两两相加
     * 采用与运算
     *  使用新数组接收
     */
    public static String addBinary(String a, String b) {
        // 找出两个字符串中最长的那一个字符串
        int len = a.length() > b.length() ? a.length() : b.length();
        // 保存二进制的字符数组的最长长度
        char barry[] = new char[len + 1];

        // a字符串指针
        int ap = a.length() - 1;
        // b字符串指针
        int bp = b.length() - 1;

        // a指针值
        int apn = 0;
        // b指针值
        int bpn = 0;

        // 二进制进位
        boolean flag = false;

        while ((ap >= 0) || (bp >= 0)) {
            apn = 0;
            bpn = 0;

            if (ap >= 0) {
                apn = Integer.valueOf(a.substring(ap, ap+1));
            }
            if (bp >= 0) {
                bpn = Integer.valueOf(b.substring(bp, bp+1));
            }
            // 如果 apn和bpn 都为1则进位补0
            if ((apn & bpn) == 1) {
                // 有进位则为1
                barry[len--] = flag ? '1' : '0';
                flag = true;
            }
            // 如果 apn和bpn都为0且进位为false 则直接补0 进位为true则补1
            else if((apn | bpn) == 0) {
                barry[len--] = flag ? '1' : '0';
                flag = false;
            }
            // 如果都不是则补1
            else {
                barry[len--] = flag ? '0' : '1';
                flag = flag ? true : false;
            }
            ap--;
            bp--;
        }
        // 如果循环结束，但是还有进位则头补1
        if (flag) barry[len] = '1';

        // 把barry数组前为0的都剔除
        int index = 0;
        for (int i = 0; i < barry.length; i++) {
            if (barry[i] != 0) {
                index = i;
                break;
            }
        }
        return String.valueOf(barry).substring(index, barry.length);
//        return null;
    }

    /**
     * 直接不用新数组接收
     * 使用最长的字符串作为修改
     */
    public static String addBinary1(String a, String b) {
        char[] maxChars;
        char[] minChars;
        // 找出 a b 字符串中最长的字符串
        if (a.length() > b.length()) {
            maxChars = a.toCharArray();
            minChars = b.toCharArray();
        } else {
            maxChars = b.toCharArray();
            minChars = a.toCharArray();
        }

        // 短数组指针
        int minp = minChars.length - 1;

        // 进位
        int jinwei = 0;
        for (int i = maxChars.length - 1; i >= 0; i--) {
            // 进位为0 且 短数组已经遍历完则直接返回
            if ((jinwei == 0) && (minp < 0)) {
                break;
            }
            // 长数组值
            int maxNum = maxChars[i] - 48;
            // 短数组值
            int minNum = 0;
            if (minp >= 0) {
                minNum = minChars[minp--] - 48;
            }
            int count = maxNum + minNum + jinwei;
            // 当前值
            maxChars[i] = (char)(count % 2 + 48);
            // 进位
            jinwei = count / 2;
        }
        // 如果有进位则长数组前 + 1;
        if (jinwei > 0) {
            return "1" + String.valueOf(maxChars);
        }
        return String.valueOf(maxChars);
    }
}
