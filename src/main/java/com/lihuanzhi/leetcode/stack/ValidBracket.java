package com.lihuanzhi.leetcode.stack;

/*
给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s ，判断字符串是否有效。
有效字符串需满足：

    左括号必须用相同类型的右括号闭合。
    左括号必须以正确的顺序闭合。
    每个右括号都有一个对应的相同类型的左括号。

示例 1：
输入：s = "()"
输出：true

示例 2：
输入：s = "()[]{}"
输出：true

示例 3：
输入：s = "(]"
输出：false

提示：
    1 <= s.length <= 104
    s 仅由括号 '()[]{}' 组成

来源：力扣（LeetCode）
链接：https://leetcode.cn/problems/valid-parentheses
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

import java.util.HashMap;
import java.util.Stack;

/**
 * 有效的括号（2023/05/20 19:48:47）
 * 思路：
 * 使用栈来实现括号匹配
 * 当栈顶的括号和准备插入的括号可以配对时则删除栈顶括号，并取消入栈操作
 * 如果不匹配则执行入栈操作
 */
public class ValidBracket {
    public static void main(String[] args) {
        System.out.println(isValid("()[]{}"));
    }

    public static boolean isValid(String s) {
        // 栈
        Stack stack = new Stack();
        // 字符对
        HashMap<Character, Character> hashMap = new HashMap();
        hashMap.put('(', ')');
        hashMap.put('[', ']');
        hashMap.put('{', '}');

        for (char c : s.toCharArray()) {
            // 空栈则直接入栈
            // 注意：这里我起初没想到空栈时无法拿到栈顶元素的问题
            if (stack.size() == 0) {
                stack.push(c);
                continue;
            }
            // 如果栈顶元素和在没有配对的括号直接返回false
            if (hashMap.get(stack.peek()) == null) {
                return false;
            }
            // 如果匹配则栈顶元素出栈
            // 注意：这里我起初写的时候没有注意到可能空指针
            if (c == hashMap.get(stack.peek())){
                stack.pop();
            } else {
                // 不匹配则入栈
                stack.push(c);
            }
        }

        return stack.size() > 0 ? true : false;
    }
}
