package com.lihuanzhi.leetcode.tree;

import sun.reflect.generics.tree.Tree;

/**
 * 相同的树(2023/05/25 13:23:28)
 * 给定两个树的根节点，p和q
 * 如果两个树在结构上相同，并且节点具有相同的值，则认为它们是相同的。
 */
public class IsSameTree {
    public static void main(String[] args) {

    }

    /**
     * 思路：
     * 递归遍历
     */
    public static boolean isSameTree(TreeNode p, TreeNode q) {
        return bianli(p, q);
    }

    public static boolean bianli(TreeNode p, TreeNode q) {
        // 如果p和q相对则表示他们都为null，则直接返回
        if (p == q) {
            return true;
        }
        // q 或 q 有一个不是null直接返回false
        if (p == null && q != null) {
            return false;
        }
        if (p != null && q == null) {
            return false;
        }
        // 两个节点数值不相等则直接返回false
        if (p.val != q.val) {
            return false;
        }

        // 比对左节点
        if (!bianli(p.left, q.left)) {
           return false;
        }

        // 比对右节点
        if (!bianli(p.right, q.right)) {
            return false;
        }

        return true;
    }

    /**
     * 官方解法
     */
    public static boolean bianli1(TreeNode p, TreeNode q) {
        // 如果p和q相对则表示他们都为null，则直接返回
        if (p == null && q == null) {
            return true;
        }
        // 因为p 和 q 已经校验不会同时为null了，所有这里两个只有有null直接false
        if (p == null || q == null) {
            return false;
        }
        // 两个节点数值不相等则直接返回false
        if (p.val != q.val) {
            return false;
        }
        // 同时为true才会返回true
        return bianli1(p.left, q.left) && bianli1(p.right, q.right);
    }
}
