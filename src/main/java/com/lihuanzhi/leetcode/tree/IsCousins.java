package com.lihuanzhi.leetcode.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 二叉树的堂兄弟节点
 *
 * @Author: lihuanzhi
 * @Date: 2023/6/8 20:49:16
 */
public class IsCousins {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode nodel = new TreeNode(2);
        TreeNode noder = new TreeNode(3);

        root.left = nodel;
        root.right = noder;

        TreeNode nodelr = new TreeNode(4);
        TreeNode noderr = new TreeNode(5);
        nodel.right = nodelr;
        noder.right = noderr;

        isCousins(root, 5, 4);
    }
    public static boolean isCousins(TreeNode root, int x, int y) {
        boolean xflag = false;
        int xindex = -1;
        boolean yflag = false;
        int yindex = -1;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        // 节点分布情况
        List<Integer> list = new ArrayList<>();
        list.add(1);

        while (!queue.isEmpty()) {
            int ceng = queue.size();
            List<Integer> nlist = new ArrayList<>();
            for (int i = 0; i < ceng; i++) {
                TreeNode node = queue.poll();

                if (node.val == x) {
                    xflag = true;
                    xindex = i;
                } else if (node.val == y) {
                    yflag = true;
                    yindex = i;
                }

                // 如果找到了就直接退出循环
                if (xflag && yflag) {
                    break;
                }

                int len = 0;
                if (node.left != null) {
                    len++;
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    len++;
                    queue.offer(node.right);
                }
                if (len > 0) {
                    nlist.add(len);
                }

            }
            // 同一层，有一个真，但不是全真
            if ((xflag | yflag) && !(xflag & yflag)) {
                return false;
            }
            // 同一层都为真
            if (xflag && yflag) {
                // 判断是否为同一父节点
                if (Math.abs(xindex - yindex) > 1) {
                    return true;
                }

                /**
                 * 这里的思路就是
                 * 把每一层节点的分布情况统计好之后，如果找到了目标的两个值且在同一层，并且这两个值的索引还是相连的，
                 * 这个时候就需要计算一下他们的父节点是否相同了
                 * 例如当前层的节点分布情况list为：
                 * 1    1   2   2   1
                 * 此时目标节点的两个值为：4,5（因为他们是0开始的，所以这两个值得都+1，变成5,6）
                 * 此时就找一下5和6是否相连，
                 * 此时对这个list进行累加，当前找到比最小索引值（5）大的时候可以开始判断了
                 *      当前 当前累加值 >= 最小索引值 && 当前累计值 >= 最大索引值时，表示他们是同一个父节点
                 *      当前 当前累计值 >= 最小索引值 && 当前累计值 <  最大索引值时，表示他们不是同一个父节点
                 *
                 * 但是感觉这个方法很浪费内存，还是不用这个方法了吧
                 * 毕竟这个方法也是我当前想到的一个思路，所以想记录一下，不知道以后有没有机会能用上...
                 * (2023/06/09 09:58:26)
                 *
                 */
                int minindex = (xindex < yindex ? xindex : yindex) + 1;
                int maxindex = (xindex < yindex ? yindex : xindex) + 1;
                int num = 0;
                // 是相邻节点
                for (Integer i : list) {
                    num += i;
                    if (num >= minindex && num < maxindex) {
                        return true;
                    } else if (num >= minindex && num >= maxindex) {
                        return false;
                    }
                }

            }
            // 没有真，全是假，继续下一层
            list = nlist;
        }
        return false;
    }

    /**
     * 使用另外一个方法实现
     *
     * @author lihuanzhi
     * @date 2023-06-09 10:00:02
     * @param root
     * @param x
     * @param y
     * @return boolean
     */
    public static boolean isCousins2(TreeNode root, int x, int y) {
        boolean xflag = false;
        TreeNode xp = null;

        boolean yflag = false;
        TreeNode yp = null;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        int ceng = queue.size();

        while (!queue.isEmpty()) {
            ceng = queue.size();
            for (int i = 0; i < ceng; i++) {
                TreeNode node = queue.poll();

                // 左节点不为空
                if (node.left != null) {
                    if (node.left.val == x) {
                        xflag = true;
                        xp = node;
                    } else if (node.left.val == y) {
                        yflag = true;
                        yp = node;
                    }
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    if (node.right.val == x) {
                        xflag = true;
                        xp = node;
                    } else if (node.right.val == y) {
                        yflag = true;
                        yp = node;
                    }
                    queue.offer(node.right);
                }

                // 如果提前找到两个节点，就直接结束循环，不过这题数据量不多，加了这个感觉会更慢，所有不加了
            }
            // 只找到一个节点
            if ((yflag | xflag) && !(xflag & yflag)) {
                return false;
            }

            // 找到两个节点
            if (xflag && yflag) {
                if (xp == yp) {
                    return false;
                } else {
                    return true;
                }
            }

            // 什么节点也没找到，继续循环
        }
        return false;
    }
}
