package com.lihuanzhi.leetcode.array;
/**
 编写一个函数来查找字符串数组中的最长公共前缀。
 如果不存在公共前缀，返回空字符串 ""。

 示例 1：
 输入：strs = ["flower","flow","flight"]
 输出："fl"

 示例 2：
 输入：strs = ["dog","racecar","car"]
 输出：""
 解释：输入不存在公共前缀。

 提示：
 1 <= strs.length <= 200
 0 <= strs[i].length <= 200
 strs[i] 仅由小写英文字母组成

 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/longest-common-prefix
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

/**
 * 最长前缀匹配（20230519）
 */
public class MaxPrefix {
    public static void main(String[] args) {

    }
    public String longestCommonPrefix(String[] strs) {
        //最长长度匹配
        int maxPrefixLen = 0;
        // 字符串最短长度
        int minLen = 255;
        for (int i = 0; i < strs.length; i++) {
            minLen = strs[i].length() < minLen ? strs[i].length() : minLen;
        }

        // 找出所有字符串的最小前缀
        for (int j = 0; j < minLen; j++) {
            // 字符匹配
            String s = "";
            if (strs.length > 0) {
                s = strs[0].substring(j, j+1);
            }
            boolean flag = false;
            for (int m = 1; m < strs.length; m++) {
                if (!strs[m].substring(j, j+1).equals(s)) {
                    flag = true;
                    break;
                }
            }
            if (flag) {
                break;
            }
            maxPrefixLen++;
        }

        if (maxPrefixLen == 0) {
            return "";
        } else {
            return strs[0].substring(0, maxPrefixLen);
        }
    }
}
