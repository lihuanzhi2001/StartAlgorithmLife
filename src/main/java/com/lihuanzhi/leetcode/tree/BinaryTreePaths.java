package com.lihuanzhi.leetcode.tree;

import java.util.*;

/**
 * 二叉树的所有路径
 *
 * @Author: lihuanzhi
 * @Date: 2023/6/14 20:04:21
 */
public class BinaryTreePaths {
    /**
     * 一开始我想使用深度遍历，好像有问题，所以这里我换成
     * @param root
     * @return
     */
    public List<String> binaryTreePaths(TreeNode root) {
        // 这里我有个误区了，我想使用List保存每一次遍历到的节点值，
        // 但是苦于每次都可以会有新的节点值会打乱原有的顺序而不知道怎么办，
        // 如果是这样的话我就得重新建一个集合来拷贝过去了，感觉很蠢
        // 不过既然是一一对应的，我使用的是队列来保存节点，我为什么不能使用队列也来保存对应的节点顺序值呢
        // 我一直陷入误区了，我的...

        List<String> list = new ArrayList<>();

        // 节点队列
        Queue<TreeNode> nodeQueue = new LinkedList<>();
        // 节点对应的路径队列
        Queue<String> pathQueue = new LinkedList<>();

        nodeQueue.offer(root);
        pathQueue.offer(root.val + "");

        while (!nodeQueue.isEmpty()) {
            TreeNode node = nodeQueue.poll();
            String path = pathQueue.poll();

            // 先判断当前节点是否为叶子节点，是的话直接返回结果了
            if (!isLeafNode(node)) {
                // 左节点是否有值
                if (node.left != null) {
                    pathQueue.offer(path + "->" + node.left.val);
                    nodeQueue.offer(node.left);
                }
                // 右节点是否有值
                if (node.right != null) {
                    pathQueue.offer(path + "->" + node.right.val);
                    nodeQueue.offer(node.right);
                }
            } else {
                list.add(path);
            }
        }
        return list;
    }


    // 判断当前节点是否为叶子节点
    public boolean isLeafNode (TreeNode node){
        return node.left == null && node.right == null;
    }
}
