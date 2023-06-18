package com.lihuanzhi.leetcode.tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 左叶子之和（2023/06/05 16:44:54）
 * 练习广度优先算法
 */
public class SumOfLeftLeaves {
    /**
     * 广度优先搜索
     * 思想就是一个数组，把一层一层的遍历
     */
    public int sumOfLeftLeaves(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        // 根节点入列
        queue.offer(root);
        int count = 0;

        // 队列非空就一直遍历
        while (!queue.isEmpty()) {
            // 从队列中取出一个节点
            TreeNode node = queue.poll();
            // 左节点不为空
            if (node.left != null) {
                // 左节点是否为叶子节点
                if (!isLeafNode(node.left)) {
                    count += node.left.val;
                }
                // 不是叶子节点就入列
                else {
                    queue.offer(node.left);
                }
            }
            // 判断右节点是否为空
            if (node.right != null) {
                // 判断右节点是否为叶子节点，不是叶子节点就入列，否则什么也不做
                if (!isLeafNode(node.right)) {
                    queue.offer(node.right);
                }
            }
        }
        return count;
    }
    // 判断节点是否为叶子节点的方法
    public boolean isLeafNode(TreeNode node) {
        return node.left == null && node.right == null;
    }
}
