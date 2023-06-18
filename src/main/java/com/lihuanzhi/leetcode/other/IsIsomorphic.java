package com.lihuanzhi.leetcode.other;

/**
 * 同构字符串（2023/05/30 21:31:09）
 */
public class IsIsomorphic {
    public static void main(String[] args) {
        IsIsomorphic ii = new IsIsomorphic();
        String s = "egg";
        String t = "add";
        System.out.println(ii.isIsomorphic(s, t));
    }

    /**
     * 思路：
     * 遍历两个字符串，计算他们每个对应字符的差值
     * 然后用一个byte[128](asscii字符总共128个)字符数组，
     * 数组的下标所以即为两个字符串对应字符的asscii码值，保存的值为两个字符串对应的字符差值
     *
     * 但是我咋感觉有问题呢？
     *
     */
    public boolean isIsomorphic(String s, String t) {
        // 长度不同直接返回false
        if (s.length() != t.length()) {
            return false;
        }
        // 字符串s的映射关系
        byte[] sarr = new byte[128];
        // 字符串t的映射关系
        byte[] tarr = new byte[128];

        char[] schars = s.toCharArray();
        char[] tchars = t.toCharArray();

        for (int i = 0; i < schars.length; i++) {
            // 两个字符差值
            if (sarr[schars[i]] != 0 && sarr[schars[i]] != tchars[i]) {
                return false;
            }
            if (tarr[tchars[i]] != 0 && tarr[tchars[i]] != schars[i]) {
                return false;
            }
            sarr[schars[i]] = (byte)tchars[i];
            tarr[tchars[i]] = (byte)schars[i];
        }
        return true;
    }
}
