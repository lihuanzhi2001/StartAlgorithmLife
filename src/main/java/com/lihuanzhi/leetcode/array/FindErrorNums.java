package com.lihuanzhi.leetcode.array;

import com.sun.xml.internal.bind.v2.TODO;

import java.util.HashMap;
import java.util.Map;

/**
 * 找到缺少的数字和错误的数字 (2023/05/29 15:20:39)
 *
 * 集合 s 包含从 1 到 n 的整数。不幸的是，因为数据错误，导致集合里面某一个数字复制了成了集合里面的另外一个数字的值，导致集合 丢失了一个数字 并且 有一个数字重复 。
 * 给定一个数组 nums 代表了集合 S 发生错误后的结果。
 * 请你找出重复出现的整数，再找到丢失的整数，将它们以数组的形式返回。
 *
 * 示例 1：
 * 输入：nums = [1,2,2,4]
 * 输出：[2,3]
 *
 * 示例 2：
 * 输入：nums = [1,1]
 * 输出：[1,2]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/set-mismatch
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class FindErrorNums {
    public static void main(String[] args) {
        FindErrorNums f = new FindErrorNums();
        int[] nums = new int[]{1,5,3,2,2,7,6,4,8,9};
        int[] arr = f.findErrorNums1(nums);

        for (int i : arr) {
            System.out.print(i + "\t");
        }
    }

    /**
     * 新的思路（下面的是我旧的思路，旧的思路太慢了）：
     * 遍历每个数字做把他们存到map集合中，当遇到重复值时不再存入map数组，记录当前重复值
     *
     * 然后再找当前数组的差值和之前正确数组的差值，最后再求出丢失值
     */
    public int[] findErrorNum(int[] nums) {
        // 数组和
        int count = 0;
        // 返回数组
        int[] result = new int[2];
        Map<Integer, Integer> map = new HashMap();

        // 查找重复值标签
        boolean flag = true;
        for (int i = 0; i < nums.length; i++) {
            // 与运算前后一致
            if (flag && (map.get(nums[i]) != null)) {
                result[0] = nums[i];
                flag = false;
                map = null;
            }
            if (flag) {
                map.put(nums[i], 1);
            }
            count += nums[i];
        }

        // 正确的 1-n 的和
        int ncount = nums.length * (1 + nums.length) / 2;
        // 丢失值
        result[1] = result[0] - (count - ncount);

        return result;
    }

    /**
     * 思路：
     * 先使用归并排序，把他们排好序之后
     * 找到他们的差值
     * 找到差值之后，差值丢失值，再找重复值
     *
     * 但是这个需要先排序再找值，这效率就很慢了，仅仅只击败了22.4%的用户
     *
     */
    public int[] findErrorNums1(int[] nums) {
        int[] tempArr = new int[nums.length];
        // 排序，使用归并排序
        mergeSort(nums, tempArr,0, nums.length - 1);

        // nums 已经排好序了
        int count = 0;
        for (int n : nums) {
            count += n;
        }
        // n项元素之和
        int ncount = nums.length * (1 + nums.length) / 2;

        // 计算他们的差值
        /**
         * 正数：小数丢失
         * 负数：大数丢失
         */
        int cha = count - ncount;
        // 当 cha > 0 时，表示当前索引值不对的值的下一个差值间隔的元素即为重复值
        // 当 cha < 0 时，表示当前索引值不对的值的上一个差值间隔的元素即为重复值
        // 而丢失值肯定就是当前索引不对的元素了...

        int[] result = new int[2];

        // 按差值间隔查找
        /**
         * [丢失的值]即为索引不对的值，而重复值为
         * 当前差值为大于0时：
         *      从头开始遍历
         *      丢失值 + 差值 = 重复值
         * 当差值小于0时：
         *      从末尾开始遍历
         *      丢失值 - 差值 = 重复值
         */
        int i = cha > 0 ? 0 : nums.length - 1;
        for (;;) {
            if (cha > 0 && i >= nums.length) break;
            if (cha < 0 && i < 0) break;

            // 找到了索引值不对的元素
            if (nums[i] != i + 1) {
                result[0] = i + 1 + cha;
                result[1] = i + 1;
                return result;
            }
            i = cha > 0 ? ++i : --i;
        }
        return result;
    }



    // 归并排序
    public void mergeSort(int[] nums, int[] tempArr, int left, int right) {
        // 只有一个元素时不需要排序
        if (left == right) {
            return;
        }
        // 找出中间值
        int mid = (right - left) / 2 + left;
        // 排左边
        mergeSort(nums, tempArr, left, mid);
        // 排右边
        mergeSort(nums, tempArr, mid + 1, right);
        // 合并两个数组
        mergeArray(nums, tempArr, left, mid, right);
    }

    // 合并两个数组
    public void mergeArray(int[] nums, int[] tempArr, int left, int mid, int right) {
        // 左半部分指针
        int l = left;
        // 右半部分指针
        int r = mid + 1;
        // 临时元素指针
        int index = left;

        // 合并两个数组
        while (l <= mid && r <= right) {
            if (nums[l] < nums[r]) {
                tempArr[index++] = nums[l++];
            } else {
                tempArr[index++] = nums[r++];
            }
        }

        // 合并左半边剩余元素
        while (l <= mid) {
            tempArr[index++] = nums[l++];
        }
        // 合并右半边剩余元素
        while (r <= right) {
            tempArr[index++] = nums[r++];
        }

         // TODO --> 我的疑问，为什么这里要把临时数组的元素复制回原数组呢？
        // 把临时数组复制会原数组
        while (left <= right) {
            nums[left] = tempArr[left];
            left++;
        }
    }
}
