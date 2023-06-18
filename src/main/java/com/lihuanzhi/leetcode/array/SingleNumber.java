package com.lihuanzhi.leetcode.array;

/**
 * 只出现一次的数字（2023/05/27 09:33:26）
 *
 * 给你一个 非空 整数数组 nums ，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。
 * 你必须设计并实现线性时间复杂度的算法来解决此问题，且该算法只使用常量额外空间。
 *
 * 示例 1 ：
 * 输入：nums = [2,2,1]
 * 输出：1
 *
 * 示例 2 ：
 * 输入：nums = [4,1,2,1,2]
 * 输出：4
 *
 * 示例 3 ：
 * 输入：nums = [1]
 * 输出：1
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/single-number
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class SingleNumber {
    public static void main(String[] args) {

    }

    /**
     * 思路：
     * 一个当前 最小数min 和 当前最大数max
     * 如果当前值在 【min - max】 范围之内的话 就使用 【总数count - 当前值】
     * 如果值 == min 或 == max 的话就剔除 max 或 min
     *
     * 如果剔除的是max 的话就把 让 max = min，
     *
     * 总结：这个想法不对，对应这题来说，使用异或运算才是正解。
     */
    public int singleNumber(int[] nums) {
        // 当前最大值
        int min = Integer.MAX_VALUE;
        // 当前最小值
        int max = Integer.MIN_VALUE;

        for (int i = 0; i < nums.length; i++) {

        }
        return -1;
    }

    /**
     * 官方解答：异或运算
     *
     * 假设数组中有 2m+1 个数，其中有 m 个数各出现两次，一个数出现一次。
     * 令 a1​、a2​、……、am​ 为出现两次的 m 个数，am+1​ 为出现一次的数。
     * 根据性质 3，数组中的全部元素的异或运算结果总是可以写成如下形式：
     *
     * (a1​⊕a1​)⊕(a2​⊕a2​)⊕⋯⊕(am​⊕am​)⊕am+1​
     *
     * 根据性质 2 和性质 1，上式可化简和计算得到如下结果：
     *
     * 0⊕0⊕⋯⊕0⊕am+1​=am+1​
     *
     * 因此，数组中的全部元素的异或运算结果即为数组中只出现一次的数字。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode.cn/problems/single-number/solution/zhi-chu-xian-yi-ci-de-shu-zi-by-leetcode-solution/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
    public int singleNumber1(int[] nums) {
        int single = 0;
        for (int num : nums) {
            single ^= num;
        }
        return single;
    }
}
