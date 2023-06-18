package com.lihuanzhi.leetcode.tree;

import java.util.*;

/**
 * 两数之和
 * 如果换成是普通的数组或集合找两数之和，我大概会有思路，一换成树或什么的时候就乱了，
 * 而且这个还是二叉搜索树，基本上算是排好序了的
 *
 * 以后看到两数之和 一定 要记得使用双指针啊！！！ 切记 ！！！
 *
 * @Author: lihuanzhi
 * @Date: 2023/6/7 16:43:50
 */
public class FindTarget {

    /**
     * 官方解答： 迭代 + 中序遍历 + 双指针
     * 这个所谓的双指针就是，假设你有一个数值升序排序的序列
     * 那么你可以使用一个 <队头的左指针> 和 <队尾的尾指针>
     * 当前两个指针指向的值相加小于目标值时，左指针右移，大于时，右指针左移。
     * 当前两个指针重合时，结束循环，表示没有找到两个值相加等于目标值
     *
     * 遍历时，只加载最左指针数到栈中，只加载最右指针数到栈中
     * 这里左子树栈顶的数就是最小数，而右子树栈顶就是最大的数
     *      这里精髓就来了，当 两个值相加 小于目标值时，左子树要开始移动
     *      左子树的栈顶元素出栈，并把该出栈节点右节点入栈（不为null时）
     *      同时把以该节点作为根节点的小子树的最左子数的全部节点入栈，这里就能保证每次出栈的元素都是中序遍历的升序排列的数值了
     *
     *      同样的，当 两个值相加 大于目标值时，右子树要开始移动
     *      先出栈，然后再加载该出栈节点的所有最右子树节点，全部入栈，最后进保证中序遍历的排序了。
     *
     * 最后如果两个栈出栈的元素相等时，那么就直接返回false了，因为找不到，不存在
     *
     */
    public boolean findTarget(TreeNode root, int k) {
        TreeNode left = root;       // 左指针
        TreeNode right = root;      // 右指针

        // 左子树栈
        Stack<TreeNode> lstack = new Stack<>();
        // 右子树栈
        Stack<TreeNode> rstack = new Stack<>();

        lstack.push(left);
        // 最左子树入栈
        while (left.left != null) {
            left = left.left;
            lstack.push(left);
        }

        rstack.push(right);
        // 最右子树入栈
        while (right.right != null) {
            right = right.right;
            rstack.push(right);
        }

        // 此时left 和 right 都指向了树的最左和最右了，分别表示最小值和最大值
        // 当前left 和 right 不相等时就一直比较下去
        while (left != right) {
            if (left.val + right.val == k) {
                return true;
            }
            // 如果两两相加大于目标值，就直接左指针右移
            if (left.val + right.val < k) {
                left = loadLeft(lstack);
            }
            // 如果两两相加大于等于目标值，就直接右指针左移
            else {
                right = loadRight(rstack);
            }
        }
        return false;
    }

    // 左子树加载
    public TreeNode loadLeft(Stack<TreeNode> stack) {
        TreeNode root = stack.pop();
        // 加载比当前栈顶节点还大的值
        TreeNode node = root.right;
        while (node != null) {
            stack.push(node);
            node = node.left;
        }
        return root;
    }

    // 加载右子树
    public TreeNode loadRight(Stack<TreeNode> stack) {
        TreeNode root = stack.pop();
        // 加载比当前栈顶节点还小的值
        TreeNode node = root.left;
        while (node != null) {
            stack.push(node);
            node = node.right;
        }
        return root;
    }

    /**
     * 这里我尝试使用题解的另外一个方法，深度优先搜索（递归） + 哈希表
     */
    Set<Integer> set = new HashSet<>();
    public boolean findTarget2(TreeNode root, int k) {
        // 遍历到头了就在直接返回false
        if (root == null) {
            return false;
        }
        // 找到了目标值
        if (set.contains(k - root.val)) {
            return true;
        }
        set.add(root.val);
        // 遍历左子树 和 右子树
        return findTarget2(root.left, k) || findTarget2(root.right, k);
    }

}
