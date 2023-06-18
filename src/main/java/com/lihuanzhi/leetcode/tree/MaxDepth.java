package com.lihuanzhi.leetcode.tree;

/**
 * 二叉树的最大深度 (2023/05/25 14:54:22)
 * 给定一个二叉树的根节点，计算该树的最大深度
 */
public class MaxDepth {
    public static void main(String[] args) {

    }

    /**
     * 二叉树的最大深度
     */
    public int maxDepth(TreeNode root) {
        // 空节点的深度为0
        if (root == null) {
            return 0;
        }

        // 计算左节点深度
        int leftDepth = maxDepth(root.left);
        // 计算右节点深度
        int rightDepth = maxDepth(root.right);

        // 返回最大节点深度
        return rightDepth > leftDepth ? rightDepth + 1 : leftDepth + 1;
    }
}
