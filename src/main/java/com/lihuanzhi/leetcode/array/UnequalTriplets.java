package com.lihuanzhi.leetcode.array;

import java.util.HashMap;
import java.util.Map;

/**
 * 双指针，一个前移作为中间值，先找和中间值不同的头，再找和头和中间值不同的尾
 * 每次循环都把当前前头的值保存起来，遍历到的时候可以直接拿到值，避免多次遍历
 *
 * 一个后移作为中间值，也是现在和中间值不同的头，再找和头和中间值不同的尾，一样的道理
 *
 * @Author: lihuanzhi
 * @Date: 2023/6/13 22:53:10
 */
public class UnequalTriplets {

    public int unequalTriplets(int[] nums) {
        // 找中间数
        int mid = nums.length / 2;

        // 前半部分中间值
        int lmid = mid;
        // 后半部分中间值
        int rmid = mid + 1;

        // 总的三元组个数
        int zongSanCount = 0;

        // 以中间的数作为中间点，往两边扩散
        while (lmid >= 1 || rmid < nums.length - 1) {
            if (lmid >= 1) {
                zongSanCount += getSanGroupCount(nums, lmid);
                lmid--;
            }
            if (rmid < nums.length - 1) {
                zongSanCount += getSanGroupCount(nums, rmid);
                rmid++;
            }
        }
        return zongSanCount;

    }

    /**
     * @param mid 中间索引位置
     * @return
     */
    public int getSanGroupCount(int[] nums, int mid) {
        // 左指针
        int l = mid - 1;
        // 右指针
        int r = mid + 1;

        // 中间值
        int midNum = nums[mid];

        // 三元组个数
        int sanCount = 0;

        // 因为中间节点是固定的，每次循环只需要找跟中间节点不同的头节点就行了
        // 所以这个保存当前遍历过的头节点的值以及他们对应的三元组个数，避免重复遍历
        Map<Integer, Integer> map = new HashMap<>();

        while (l >= 0) {
            if (nums[l] == midNum) {
                l--;
                continue;
            }
            // 之前遍历过
            if (map.get(nums[l]) != null) {
                sanCount += map.get(nums[l]);
            }
            // 没遍历过
            else {
                // 记录当前轮的头节点值数组
                int touCount = 0;
                for (int j = r; j < nums.length; j++) {
                    if (nums[j] != nums[l] && nums[j] != midNum) {
                        touCount += 1;
                    }
                }
                map.put(nums[l], touCount);
                sanCount += touCount;
            }
            l--;
        }

        // 最后返回三元组个数
        return sanCount;
    }
}
