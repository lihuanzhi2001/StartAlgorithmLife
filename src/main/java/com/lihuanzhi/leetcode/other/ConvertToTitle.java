package com.lihuanzhi.leetcode.other;

/**
 * Excel表列名称（2023/05/27 22:04:23）
 *
 * 给你一个整数 columnNumber ，返回它在 Excel 表中相对应的列名称。
 *
 * 例如：
 * A -> 1
 * B -> 2
 * C -> 3
 * ...
 * Z -> 26
 * AA -> 27
 * AB -> 28
 * ...
 *
 * 示例 1：
 * 输入：columnNumber = 1
 * 输出："A"
 *
 * 示例 2：
 * 输入：columnNumber = 28
 * 输出："AB"
 *
 * 示例 3：
 * 输入：columnNumber = 701
 * 输出："ZY"
 *
 * 示例 4：
 * 输入：columnNumber = 2147483647
 * 输出："FXSHRXW"
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/excel-sheet-column-title
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class ConvertToTitle {
    public static void main(String[] args) {

    }

    /**
     * 思路：
     * 就是26进制，因为是 1 - 26 从27开始进位
     * 但是，因为他是没有0的，直接是从1开始，那么如果遇到了，就得高位借1
     * 如果高位没有则抹除高位，高位借1之后就是26，而26就是'Z'
     *
     */
    public String convertToTitle(int columnNumber) {
        // 保存字符的char数组
        char[] carr = new char[10];
        // 字符位置索引
        int index = 9;

        while (columnNumber != 0) {
            // 如果index == 0 时，需要扩容
            if (index == 0) {
                // 比原数组长度 + 10
                char[] ncarr = new char[carr.length + 10];
                for(int i = ncarr.length - 1; i >= carr.length; i--) {
                    ncarr[i] = carr[i - 10];
                }
                // 前面十个字符索引都为空位置
                index = 9;
                carr = ncarr;
            }
            // 余数
            int n = columnNumber % 26;
            // 商
            columnNumber /= 26;

            // 等于0时需要高位借1
            if (n == 0) {
                carr[index--] = 'Z';
                // 高位借1
                columnNumber -= 1;
            } else {
                carr[index--] = (char)(n + 64);
            }
        }
        return String.valueOf(carr, index + 1, carr.length - index - 1);
    }

    /**
     * 更巧妙的解法，但是思路还是按照我的思路
     */
    public String convertToTitle1(int columnNumber) {
        StringBuilder sb = new StringBuilder();

        while (columnNumber > 0) {

            // 余数
            int n = columnNumber % 26;
            // 商
            columnNumber /= 26;

            /**
             * 注意：
             * 我这里因为是从1开始算的，所用这里我是等于0时高位借1
             * 但是，如果我这里是从2开始算的话，小于等于 1 的时候就需要高位借1了
             */

            // 等于0时需要高位借1（因为没有0位）
            if (n == 0) {
                sb.append('Z');
                // 高位借1
                columnNumber -= 1;
            } else {
                sb.append((char)(n + 64));
            }
        }
        return sb.reverse().toString();
    }

    /**
     *
     *
     * 这是一道从 11 开始的的 2626 进制转换题。
     *      对于一般性的进制转换题目，只需要不断地对 columnNumber 进行 % 运算取得最后一位，
     *      然后对 columnNumber 进行 / 运算，
     *      将已经取得的位数去掉，直到 columnNumber 为 0 即可。
     *
     * 一般性的进制转换题目无须进行额外操作，是因为我们是在「每一位数值范围在 [0,x)」的前提下进行「逢 x 进一」。
     * 但本题需要我们将从 1 开始，因此在执行「进制转换」操作前，我们需要先对 columnNumber 执行减一操作，从而实现整体偏移。
     *
     *
     * 作者：AC_OIer
     * 链接：https://leetcode.cn/problems/excel-sheet-column-title/solution/gong-shui-san-xie-cong-1-kai-shi-de-26-j-g2ur/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
    public String convertToTitle2(int columnNumber) {
        StringBuilder sb = new StringBuilder();
        while (columnNumber > 0) {
            columnNumber--;
            sb.append((char)(columnNumber % 26 + 'A'));
            columnNumber /= 26;
        }
        sb.reverse();
        return sb.toString();

    }
}
