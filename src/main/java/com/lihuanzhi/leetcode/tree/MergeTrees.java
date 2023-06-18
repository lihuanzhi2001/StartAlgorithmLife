package com.lihuanzhi.leetcode.tree;

import org.omg.CORBA.TRANSACTION_MODE;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 合并二叉树
 * 想象一下，当你将其中一棵覆盖到另一棵之上时，两棵树上的一些节点将会重叠（而另一些不会）。
 * 你需要将这两棵树合并成一棵新二叉树。
 * 合并的规则是：如果两个节点重叠，那么将这两个节点的值相加作为合并后节点的新值；否则，不为 null 的节点将直接作为新二叉树的节点。
 * 返回合并后的二叉树。
 * 注意: 合并过程必须从两个树的根节点开始。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/merge-two-binary-trees
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @Author: lihuanzhi
 * @Date: 2023/6/6 22:27:18
 */
public class MergeTrees {
    /**
     * 使用广度优先遍历
     */
    public TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
        // 树1
        Queue<TreeNode> queue1 = new LinkedList<>();
        // 树2
        Queue<TreeNode> queue2 = new LinkedList<>();

        // 新树数列
        Queue<TreeNode> queue3 = new LinkedList<>();

        // 新树根节点
        TreeNode root = null;

        if (root1 != null || root2 != null) {
            if (root1 == null && root2 != null) {
                root = root2;
                return root;
            }
            if (root2 == null && root1 != null) {
                root = root1;
                return root;
            }
        } else {
            return root;
        }
        root = new TreeNode();

        // 到这里时，表示两个树都有值
        queue1.offer(root1);
        queue2.offer(root2);
        queue3.offer(root);

        while (!queue3.isEmpty()) {
            TreeNode node = queue3.poll();

            TreeNode node1 = queue1.poll();
            TreeNode node2 = queue2.poll();

            node.val = node1.val + node2.val;

            // 如果两棵树的左节点都有，则直接入列
            if (node1.left != null && node2.left != null) {
                queue1.offer(node1.left);
                queue2.offer(node2.left);

                TreeNode leftNode = new TreeNode();
                node.left = leftNode;
                queue3.offer(leftNode);
            }
            // 如果两棵树的左节点都有，则直接入列
            if (node1.right != null && node2.right != null) {
                queue1.offer(node1.right);
                queue2.offer(node2.right);

                TreeNode rightNode = new TreeNode();
                node.right = rightNode;
                queue3.offer(rightNode);
            }
            // 如果树1的左节点有，而树2的左节点没有
            if (node1.left != null && node2.left == null) {
                node.left = node1.left;
            }
            else if (node1.left == null && node2.left != null) {
                node.left = node2.left;
            }

            // 如果树1的右节点有，而树2的右节点没有
            if (node1.right != null && node2.right == null) {
                node.right = node1.right;
            }
            else if (node1.right == null && node2.right != null) {
                node.right = node2.right;
            }
        }
        return root;

    }

    /**
     * 使用递归实现
     *
     */

    public TreeNode mergeTrees2(TreeNode root1, TreeNode root2) {
        if (root1 == null) {
            return root2;
        }
        if (root2 == null) {
            return root1;
        }
        TreeNode root = new TreeNode();
        // 到这里了表示他们都不是空节点了
        root.val = root1.val + root2.val;

        // 遍历左边
        root.left = mergeTrees2(root1.left, root2.left);
        // 遍历右边
        root.right = mergeTrees2(root1.right, root2.right);

        // 合并完之后，返回根节点
        return root;
    }
}
