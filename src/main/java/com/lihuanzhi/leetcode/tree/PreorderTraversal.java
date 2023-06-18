package com.lihuanzhi.leetcode.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * 遍历一个二叉树，使用前序遍历 (2023/05/27 16:38:52)
 */
public class PreorderTraversal {
    public static void main(String[] args) {

    }

    // 二叉树的遍历就不需要思路了
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        bianli(list, root);
        return list;
    }

    public void bianli(List<Integer> list, TreeNode root) {
        if (root == null) {
            return;
        }

        list.add(root.val);
        bianli(list, root.left);
        bianli(list, root.right);
    }
}
