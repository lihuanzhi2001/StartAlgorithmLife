package com.lihuanzhi.leetcode.tree;

/**
 * 找出二叉树的最小深度（2023/05/25 22:14:10）
 */
public class MinDepth {
    public static void main(String[] args) {

    }

    /**
     * 思路：
     * 深度遍历，然后返回最小深度子树
     * 在遍历到左节点就是叶子节点时，截断返回，不用继续遍历右节点了
     */
    public int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int ld = minDepth(root.left);

        // 如果左节点已经是叶子节点就不用计算右节点了
        // 因为他最多也只能跟左边高度持平
        if (ld == 1) return 2;

        int rd = minDepth(root.right);
        // 有一个0就返回他们中的最大值
        if (ld == 0 || rd == 0) {
            return ld + rd + 1;
        }

        return Math.min(ld, rd) + 1;
    }
}
