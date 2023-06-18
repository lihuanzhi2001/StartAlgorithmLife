package com.lihuanzhi.leetcode.tree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 二叉搜索树的最小绝对值差
 * @Author: lihuanzhi
 * @Date: 2023/6/5 21:51:35
 *
 */
public class GetMinimumDifference {
    /**
     * 注释：练习广度优先查询用的
     *
     * @author lihuanzhi
     * @date 2023-06-05 21:52:22
     */
    public int getMinimumDifference(TreeNode root) {

        return -1;
    }

    /**
     * 广度优先搜索我不知道怎么实现，但是使用中序遍历就很好实现，
     * 因为这是一个二叉搜索树，即根节点的左侧比根小，右侧比根大
     * 所以中序遍历时，就能得到一个有序的序列
     *
     * 这里是尝试使用深度优先搜索实现一些，据我所了解的，广度的思想是一个序列，深度的思想就是一个栈
     * 所以这里把广度的队列换成栈试一下
     *
     */

    int preNum = -1;
    int min = Integer.MAX_VALUE;
    public int getMinimumDifference2(TreeNode root) {
        dfs(root);
        return min;

    }

    // 遍历
    public void dfs(TreeNode node) {
        if (node == null) {
            return;
        }

        // 遍历左节点
        dfs(node.left);

        // 这里做中序遍历的操作
        if (preNum == -1) {
            preNum = node.val;
        } else {
            min = Math.min(min, node.val - preNum);
            preNum = node.val;
        }

        // 遍历右节点
        dfs(node.right);
    }

    /**
     * 使用单调栈的方法
     */
    int ans = Integer.MAX_VALUE;
    int pre = -1;
    public int getMinimumDifference3(TreeNode root) {
        TreeNode cur = root;
        Stack<TreeNode> stack = new Stack<>();

        while (cur != null || !stack.isEmpty()) {
            /**root作为根节点的最左子树全部入栈 */
            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }
            cur = stack.pop();
            updateVal(cur);
            cur = cur.right;

        }
        return ans;

    }

    // 更新
    public void updateVal(TreeNode node) {
        if (pre == -1) {
            pre = node.val;
        } else {
            ans = Math.min(ans, node.val - pre);
            pre = node.val;
        }
    }
}
