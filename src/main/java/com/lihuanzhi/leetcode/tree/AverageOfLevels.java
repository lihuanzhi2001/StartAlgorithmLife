package com.lihuanzhi.leetcode.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 二叉树的层平均值
 *
 * @Author: lihuanzhi
 * @Date: 2023/6/7 15:05:11
 */
public class AverageOfLevels {

    public List<Double> averageOfLevels(TreeNode root) {
        List<Double> list = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();

        int cur = 0;    // 当前层宽
        int next = 0;   // 下一层宽
        int num = cur;  // 每层个数
        // 和
        double count = 0;

        queue.offer(root);
        cur = 1;
        num = cur;

        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            // 当前层
            count += node.val;
            num--;

            if (node.left != null) {
                queue.offer(node.left);
                next++;
            }
            if (node.right != null) {
                queue.offer(node.right);
                next++;
            }

            // 当前层遍历完了
            if (num == 0) {
                double c = (double) cur;
                list.add(count / c);

                // 重置
                count = 0;
                num = cur = next;
                next = 0;
            }
        }
        return list;
    }
}
