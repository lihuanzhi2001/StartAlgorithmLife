package com.lihuanzhi.leetcode.other;

/**
 * 返回字符串最后一个单词的长度，字符串中的单词使用“ ”切分
 * （2023/05/21 12:13:51）
 */
public class LengthOfLastWord {
    public static void main(String[] args) {

    }

    /**
     * 开始时：
     * 思路：双指针
     * 1个指针(pre) 指向空格之后的第一个非空格字
     * 1个指针(next) 指向空格之前的最后一个非空格字符
     *
     *  思考后：
     *  不需要使用双指针，只需要从字符串后续开始遍历
     *  从第一个非空格的字符开始计算，计算到空格为止
     */
    public int lengthOfLastWord(String s) {
        // 第一个非空字符开始的截止空字符的长度
        int count = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            String str = s.substring(i, i+1);
            // 标记第一个非空
            // 注意：这里我一开始是使用 == 比较的，这是非常不对的
            if (!str.equals(" ")){
                count++;
            }
            // 遇到非空字符之后的第一个空格字符
            if (str.equals(" ") && count > 0) {
                break;
            }
        }
        return count;
    }
}
