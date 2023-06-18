package com.lihuanzhi.leetcode.tree;

import java.util.Stack;

/**
 * 是否为平衡二叉树
 */
public class IsBalanced {
    public static void main(String[] args) {

    }

    /**
     * 思路：
     * 遍历左右子树，当当前节点只有一个节点时，且这个节点的左右节点不同时为null
     * 则直接返回false
     */
    public boolean isBalanced(TreeNode root) {
        return treeDepth(root) >= 0;
    }

    public int treeDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int ld = treeDepth(root.left);
        int rd = treeDepth(root.right);

        if (ld == -1 || rd == -1 || Math.abs(ld - rd) > 1) {
            return -1;
        }
        return Math.max(ld, rd) + 1;
    }


}
