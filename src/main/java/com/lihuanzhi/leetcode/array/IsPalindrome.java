package com.lihuanzhi.leetcode.array;

/**
 * 验证字符串是否是回文字符串（2023/05/27 09:03:13）
 *
 * 如果在将所有大写字符转换为小写字符、并移除所有非字母数字字符之后，短语正着读和反着读都一样。则可以认为该短语是一个 回文串 。
 * 字母和数字都属于字母数字字符。
 * 给你一个字符串 s，如果它是 回文串 ，返回 true ；否则，返回 false 。
 *
 * 示例 1：
 * 输入: s = "A man, a plan, a canal: Panama"
 * 输出：true
 * 解释："amanaplanacanalpanama" 是回文串。
 *
 * 示例 2：
 * 输入：s = "race a car"
 * 输出：false
 * 解释："raceacar" 不是回文串。
 *
 * 示例 3：
 * 输入：s = " "
 * 输出：true
 * 解释：在移除非字母数字字符之后，s 是一个空字符串 "" 。
 * 由于空字符串正着反着读都一样，所以是回文串。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/valid-palindrome
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class IsPalindrome {
    public static void main(String[] args) {

    }

    public boolean isPalindrome(String s) {
        char strArr[] = s.toCharArray();
        // 新字符串数组指针
        int index = 0;

        for (char c : strArr) {
            // 0 - 9
            if (48 <= c && c <= 57) {
                strArr[index++] = c;
            }
            // A - Z (同时转成小写)
            else if (65 <= c && c <= 90) {
                strArr[index++] = (char)(c + 32);
            }
            // a - z
            else if (97 <= c && c <= 122) {
                strArr[index++] = c;
            }
        }

        // 双指针回旋
        int left = 0;    // 头指针
        /**
         * 注意：
         * 我一开始是比较left <= index，这是不用也不对的，
         * 因为left == index时只剩一个字符，这是无论如何也是相等的
         */
        while (left < index) {
            index--;
            if (strArr[left] != strArr[index]){
                return false;
            }
            left++;
        }
        return true;
    }
}
