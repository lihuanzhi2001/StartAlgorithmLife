package com.lihuanzhi.leetcode.tree;

/**
 * 给定二叉树的头节点，检查他是否轴对称（2023/05/25 14:32:21）
 */
public class IsSymmetric {
    public static void main(String[] args) {

    }

    /**
     * 思路：
     * 感觉其实就是建议两棵树是否相等
     * 不过这个是左节点要等于右节点
     */
    public boolean isSymmetric(TreeNode root) {
        return bianli(root.left, root.right);
    }

    /**
     * 比较两个节点是否相等
     */
    public boolean bianli(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        }
        if (p == null || q == null) {
            return false;
        }
        if (p.val != q.val) {
            return false;
        }

        // 继续遍历该节点的左右节点是否相等
        return bianli(p.left, q.right) && bianli(p.right, q.left);
    }
}
