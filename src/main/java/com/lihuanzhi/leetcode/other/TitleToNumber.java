package com.lihuanzhi.leetcode.other;

/**
 * Excel表列序号（2023/05/28 19:30:12）
 *
 * 给你一个字符串 columnTitle ，表示 Excel 表格中的列名称。
 * 返回 该列名称对应的列序号 。
 */
public class TitleToNumber {
    public static void main(String[] args) {

    }

    /**
     * 每次转换时先整体右移（就是+1），之前的那题Excel是左移
     */
    public int titleToNumber(String columnTitle) {
        char[] chars = columnTitle.toCharArray();

        int count = 0;
        // 进制
        int jinzhi = 1;

        for (int i = chars.length - 1; i >= 0; i--) {
            count += jinzhi * (chars[i] - 64);
            jinzhi *= 26;
        }
        return count;
    }

    /**
     * 获取26次方值
     */
//    public int get26Cicle(int cicle) {
//        int count = 1;
//        for (int i = 0; i < cicle; i++) {
//            count *= 26;
//        }
//        return count;
//    }
}
