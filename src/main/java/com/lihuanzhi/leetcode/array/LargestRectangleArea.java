package com.lihuanzhi.leetcode.array;

import java.util.ArrayList;
import java.util.List;

/**
 * 柱状图中最大的矩形
 *
 * LeetCode 84题
 * 难度：困难
 *
 * @Author: lihuanzhi
 * @Date: 2023/6/9 11:58:21
 */
public class LargestRectangleArea {
    public static void main(String[] args) {
//        int[] heights = new int[]{0,0,0,0,0,0,0,0,0,0,1,1,1,1,1,1,1,1,1,1,2,2,2,2,2,2,2,2,2,2,3,3,3,3,3,3,3,3,3,3,4,4,4,4,4,4,4,4,4,4,5,5,5,5,5,5,5,5,5,5,6,6,6,6,6,6,6,6,6,6};
//        int[] heights = new int[]{2,1,5,6,2,3};
        int[] heights = new int[]{2,1,2,0,3,2,2,3};

        System.out.println(largestRectangleArea2(heights));
    }

    /**
     * 思路：（这个思路是没有问题的，可是提交时时间超时了，很难受）
     * 具体思路就是，每次找到最小沉降高度，然后找到每个小范围集合内的最小高度，计算他们范围内的面积
     * 例如：
     *      [1,2,3,0,3,4,0,2,5,6]
     *      每个小范围就是(1,3,4)、(3,4)、(2,5,6) 他们对应的面积为 3 * ( 1 + 当前沉降高度) 、2 * (3 + 当前沉降高度)、3 * (2 + 当前沉降高度)
     *
     * 就是这个一直沉降，指定全部沉降完全就结束了
     */
    public int largestRectangleArea(int[] heights) {
        // 当前沉降高度
        int chenHeight = 0;
        // 每次循环需要沉降的最小高度
        int minHeight = Integer.MAX_VALUE;

        // 最大矩形面积
        int maxArea = 0;

        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < heights.length; i++) {
            list.add(heights[i]);
        }

        while (list.size() > 0) {
            // 小范围内的最大宽度
            int x_max_k = 0;
            // 小范围内的最小高度
            int x_min_h = Integer.MAX_VALUE;

            for (int i = 0; i < list.size(); i++) {
                int num = list.get(i);
                // 遇到0
                if (num == 0) {
                    // 计算当前矩形面积是否比最大的大
                    maxArea = Math.max(x_max_k * (x_min_h + chenHeight), maxArea);

                    // 统计完一个小范围之后，重置
                    x_max_k = 0;
                    x_min_h = Integer.MAX_VALUE;
                    continue;
                }
                // 遍历到末尾
                if (i == list.size() - 1) {
                    x_max_k++;

                    x_min_h = Math.min(num, x_min_h);
                    minHeight = Math.min(num, minHeight);

                    // 计算当前矩形面积是否比最大的大
                    maxArea = Math.max(x_max_k * (x_min_h + chenHeight), maxArea);
                    continue;
                }

                // 没遇到 0 就继续找
                x_max_k++;
                if (num < x_min_h) {
                    x_min_h = num;
                }

                // 计算一下需要沉降的最小高度
                if (num < minHeight) {
                    minHeight = num;
                }
            }

            List<Integer> nlist = new ArrayList<>();
            int nsize = -1;

            for (int i = 0; i < list.size(); i++) {
                int temp = list.get(i) - minHeight;

                // 集合为空时，所有小于0的数都过滤
                if (nlist.size() == 0 && temp <= 0) {
                    continue;
                }

                // 大于0直接添加
                if (temp > 0) {
                    nlist.add(temp);
                    nsize++;
                }
                // 小于等于0，看前一个是否为0
                else {
                    if (nlist.get(nsize) == 0) {
                        continue;
                    } else {
                        nlist.add(0);
                        nsize++;
                    }
                }
            }

            // 删除集合最后一个0
            if (nsize >= 0 && nlist.get(nsize) == 0) {
                nlist.remove(nsize);
            }

            // 高度沉降
            chenHeight += minHeight;
            minHeight = Integer.MAX_VALUE;

            list = nlist;
        }

        return maxArea;
    }

    /**
     * 思路：
     * 上面那个思路太慢了，换个方法
     * 改成不是沉降最小高度了，而是沉降每个小范围内的最小高度
     * 这个就能提高速度了，感觉是这样的
     *
     */
    public static int largestRectangleArea2(int[] heights) {
        // 最大面积
        int maxArea = 0;

        // 小范围list
        List<List<Integer>> xfwList = new ArrayList<>();
        // 每个小范围集合的沉降高度
        List<Integer> xfwHeightList = new ArrayList<>();

        // 装填 ，把相邻的相同的累加起来
        List<Integer> tempList = new ArrayList<>();

        // 数组非常长时，可以使用这个方法
        if (heights.length >= 1000) {
            int geshu = 1;
            for (int i = 0; i < heights.length; i++) {
                // 第一个元素时
                if (i == 0) {
                    // 只有一个元素时
                    if (i == heights.length - 1) {
                        tempList.add(heights[i]);
                    }
                    continue;
                }
                if (heights[i] == heights[i - 1]) {
                    geshu++;
                    // 相等，但最后一个
                    if (i == heights.length - 1) {
                        tempList.add(heights[i] * geshu);
                        continue;
                    }
                }
                // 不相等就直接添加到集合
                else {
                    // 不相等，但最后一个
                    tempList.add(heights[i - 1] * geshu);
                    geshu = 1;
                    if (i == heights.length - 1) {
                        tempList.add(heights[i]);
                        continue;
                    }
                }
//            tempList.add(heights[i]);

            }
        } else {
            for (int i = 0; i < heights.length; i++) {
                tempList.add(heights[i]);
            }
        }

        xfwList.add(tempList);
        xfwHeightList.add(0);

        while (xfwList.size() > 0) {
            // 新小范围的最小沉降高度
            List<Integer> n_xfw_height_list = new ArrayList<>();
            // 每个小范围的已沉降高度

            for (int i = 0; i < xfwList.size(); i++) {
                List<Integer> list = xfwList.get(i);
                // 每个范围最小沉降高度
                int minHeight = Integer.MAX_VALUE;

                for (Integer num : list) {
                    minHeight = Math.min(num, minHeight);
                }
                // 更新最大面积
                maxArea = Math.max(list.size() * (xfwHeightList.get(i) + minHeight), maxArea);

                n_xfw_height_list.add(minHeight);
            }
            // 沉降高度临时集合
            List<Integer> temp_height_list = new ArrayList<>();
            // 新范围集合
            List<List<Integer>> temp_list = new ArrayList<>();

            // 遍历完之后开始沉降
            for (int i = 0; i < xfwList.size(); i++) {
                List<Integer> list = xfwList.get(i);
                // 当前小范围的沉降高度
                int chen = n_xfw_height_list.get(i);

                List<Integer> tempList1 = new ArrayList<>();
                for(int j = 0; j < list.size(); j++) {
                    // 找到小于等于0时
                    if (list.get(j) - chen <= 0) {
                        if (tempList1.size() > 0) {
                            temp_list.add(tempList1);
                            // 添加成功之后，重置集合
                            tempList1 = new ArrayList<>();
                            // 重置集合之后，记录一下沉降高度
                            temp_height_list.add(chen +  xfwHeightList.get(i));
                            continue;
                        } else {
                            continue;
                        }
                    } else {
                        // 大于0的话就继续添加
                        tempList1.add(list.get(j) - chen);

                        // 如果遍历到了末尾
                        if (j == list.size() - 1) {
                            if (tempList1.size() > 0) {
                                temp_list.add(tempList1);
                                // 记录一下沉降高度
                                temp_height_list.add(chen +  xfwHeightList.get(i));
                                continue;
                            }
                        }
                    }
                }
            }
            // 更新小范围list
            xfwList = temp_list;
            // 更新每个小范围集合的沉降高度
            xfwHeightList = temp_height_list;
        }
        return maxArea;
    }
}
