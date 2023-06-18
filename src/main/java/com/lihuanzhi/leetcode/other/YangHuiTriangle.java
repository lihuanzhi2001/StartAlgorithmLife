package com.lihuanzhi.leetcode.other;

import org.omg.PortableInterceptor.INACTIVE;

import java.util.ArrayList;
import java.util.List;

/**
 * 杨辉三角 （2023/05/26 12:17:08）
 */
public class YangHuiTriangle {
    public static void main(String[] args) {

    }
    // 杨辉三角
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> list = new ArrayList<>();
        // 层数
        for (int i = 0; i < numRows; i++) {
            List<Integer> tempList = new ArrayList<>();
            for (int j = 0; j <= i; j++) {
                if (j == 0 || j == i) {
                    tempList.add(1);
                    continue;
                }
                List<Integer> preList = list.get(i - 1);
                // 等于上一个集合的 当前索引前一个以及当前索引
                tempList.add(preList.get(j - 1) + preList.get(j));
            }
            list.add(tempList);
        }
        return list;
    }
}
