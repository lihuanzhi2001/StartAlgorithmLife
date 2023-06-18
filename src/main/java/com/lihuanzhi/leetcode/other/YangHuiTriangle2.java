package com.lihuanzhi.leetcode.other;

import org.omg.PortableInterceptor.INACTIVE;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 给定一个非负索引 rowIndex，返回「杨辉三角」的第 rowIndex 行。
 * (2023/05/26 12:30:24)
 */
public class YangHuiTriangle2 {
    public static void main(String[] args) {
        YangHuiTriangle2 yht2 = new YangHuiTriangle2();
        yht2.getRow(6);

    }
    // 杨辉三角2

    /**
     * 思路：
     * 第 i 行的 第 j 项 等于 上一行（i-1）的 j - 1 项和 j 项 之和
     * @param rowIndex
     * @return
     */
    public List<Integer> getRow(int rowIndex) {
        List<Integer> result = new ArrayList<>();
        result.add(1);

        // 遍历到第n行
        for (int i = 1; i <= rowIndex; i++) {
            // 因为杨辉三角是对称的，所以只需要求一半就好了，剩下的一半就对称填充
            int mid = i / 2;
            // 第i行 第j项
            for (int j = 1; j <= mid; j++) {
                // 第i行第j项就是上一行（i-1）的第j-1项和第j项
                result.set(j, result.get(j) + getPre(result, j - 1));
            }
            // 剩下的就填充就好了
            for (int j = mid + 1; j <= i; j++) {
                if (j == i) {
                    result.add(1);
                    continue;
                }
                if (i % 2 == 0) result.set(j, result.get(--mid));
                else result.set(j, result.get(mid--));
            }
        }
        return result;
    }

    public Integer getPre(List<Integer> list, int index) {
        if (index <= 0) return 1;

        return list.get(index) - getPre(list, index - 1);
    }

    // 官方解答

    /**
     * 从后往前相加，而我的思路是从前往后添加，显然官方解答更牛逼
     * 例如：
     * 1            ---0
     * 1 1          ---1
     * 1 2 1        ---2
     * 1 3 3 1      ---3
     *
     * 所以第四行时，从前往后添加是
     * 1 3 3 1 0        --- 末尾补充0， 这里很关键，很牛逼
     *
     * j项 和 j+1 项添加之后（从后往前相加）。。。
     *
     * 1 3 3 1 [1]        ---第3和第4相加
     * 1 3 3 [4] 1        ---第2和第3相加
     * 1 3 [6] 4 1        ---第1和第2相加
     * 1 [4] 6 4 1        ---第0和第1相加
     *
     *
     */
    public List<Integer> getRow1(int rowIndex) {
        List<Integer> row = new ArrayList<Integer>();
        row.add(1);
        for (int i = 1; i <= rowIndex; ++i) {
            // 末尾补充0
            row.add(0);
            // 从后往前求值，这样就不会出现 g(n) = f(n-1) + f(n) 中f(n-1)被覆盖的情况了
            for (int j = i; j > 0; --j) {
                row.set(j, row.get(j) + row.get(j - 1));
            }
        }
        return row;
    }
}
