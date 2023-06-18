package com.lihuanzhi.leetcode.tree;

import javax.transaction.TransactionRequiredException;

/**
 * 将有序数组转成二叉搜索树 （2023/05/25 15:05:51）
 */
public class SortedArrayToBST {
    public static void main(String[] args) {

    }

    // 既然他严格按照升序排序，那么只需要找到中间指针就好了
    public TreeNode sortedArrayToBST(int[] nums) {

        return getNode(nums, 0, nums.length - 1);
    }

    // 获取根节点
    public TreeNode getNode(int[] nums, int left, int right) {
        // 越界直接返回null
        if (left > right) {
            return null;
        }

        // 中间指针
        int mid = (left + right) / 2;
        // 中间节点
        TreeNode root = new TreeNode(nums[mid]);

        // 左节点
        root.left = getNode(nums, left, mid - 1);
        // 右节点
        root.right = getNode(nums, mid + 1, right);

        return root;
    }

}
