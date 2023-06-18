package com.lihuanzhi.leetcode.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个二叉树的根节点，返回他的中序遍历（2023/05/25 13:01:24）
 */
public class InorderTraversal {
    public static void main(String[] args) {

    }

    /**
     * 思路：
     * 中序遍历（左根右）
     */
    public static List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        // 遍历中...
        bianli(result, root);
        return result;
    }

    // 递归遍历 ---> (cur：当前节点)
    public static void bianli(List<Integer> result, TreeNode cur) {
        // 没有左节点就添加到集合
        if (cur == null) {
            return;
        }
        // 遍历左节点
        bianli(result, cur.left);
        // 左节点不存在时保存该节点值（因为是中序遍历）
        result.add(cur.val);
        // 遍历有节点
        bianli(result, cur.right);
    }
}
