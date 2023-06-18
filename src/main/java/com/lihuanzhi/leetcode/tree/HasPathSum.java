package com.lihuanzhi.leetcode.tree;

/**
 * （2023/05/26 10:02:47）
 * 路径总和,给定一个二叉树的根节点root，和一个表示目标和的整数 targetSum 。
 * 判断该树中是否存在 根节点到叶子节点 的路径，
 * 这条路径上所有节点值相加等于目标和 targetSum 。
 * 如果存在，返回 true ；否则，返回 false
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/path-sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class HasPathSum {
    public static void main(String[] args) {

    }

    /**
     * 思路：
     * 也是递归，每次使用（目标值 - 当前值） 如果是0且为叶子节点则返回true
     */
    public boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) {
            return false;
        }

        // 如果是叶子节点判断当前节点是否等于目标值
        if (root.left == null && root.right == null) {
            return root.val == targetSum;
        }
        // 否则继续判断左右子树是否有符合的的路径和
        return hasPathSum(root.left, targetSum - root.val) || hasPathSum(root.right, targetSum - root.val);

        // 我的思路
//        return getPathSum(root, targetSum) == 0;
    }

    /** 我的思路
     * 计算 (目标值 - 当前节点值) 是否为0
     * 如果 (targetSum - root.val < root.val) 时，只能遍历左子树，
     * 但是这个好像只能适用于左大于右的二叉树（好像叫红黑树吧）
     * 所以本题不适用
     *
     *
     */
    public int getPathSum(TreeNode root, int targetSum) {
        // 返回 -1 表示还没找到
        if (root == null) {
            return -1;
        }

        // 如果左右节点都是null则返回(目标值 - 当前值)
        if (root.left == null && root.right == null && targetSum == root.val) {
            return 0;
        }


        int lts = getPathSum(root.left, targetSum - root.val);
        // 如果lts == 0 则表示找到了
        if (lts == 0) return 0;

        int rts = getPathSum(root.right, targetSum - root.val);
        // 如果 rts == 0 也表示找到了
        if (rts == 0) return 0;

        return -1;
    }
}
