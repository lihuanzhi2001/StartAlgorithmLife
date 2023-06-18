package com.lihuanzhi.leetcode.tree;

/**
 * 树节点的第K个祖先
 * 难度：困难
 *
 * @Author: lihuanzhi
 * @Date: 2023/6/12 20:49:21
 */
public class TreeAncestor {
    // 父节点数组
    int[] prent;
    // 节点层级数组
    int[] nodeLevel;
    // 每一层的节点数
    int[] nodeLevelNum;
    // 没一层对应的的节点数以及开始的索引
    int[][] nodeLevelNumStart;


    public TreeAncestor(int n, int[] parent) {
        nodeLevel = new int[n];
        nodeLevelNum = new int[n];
        nodeLevelNumStart = new int[n][2];

        // 假设从第1层开始算
        int curLevel = 1;
        int curLevelNum = 1;
        int curLevelStart = 0;
        nodeLevel[0] = curLevel;
        nodeLevelNum[0] = curLevelNum;
        nodeLevelNumStart[0] = new int[]{curLevelNum, curLevelStart};
        curLevel++;
        curLevelNum = 0;
        curLevelStart = -1;

        // 上一层最大值
        int preMax = 0;

        // 初始化节点层级数组
        for (int i = 1; i < parent.length; i++) {
            if (parent[i] <= preMax) {
                nodeLevel[i] = curLevel;
                curLevelNum++;
            }
            // 不小于的话就开始下一层，并记录当前
            else {
                curLevel++;
                preMax = i - 1;

                nodeLevel[i] = curLevel;
                nodeLevelNum[curLevel - 1] = curLevelNum;
                nodeLevelNumStart[curLevel - 1] = new int[]{curLevelNum, curLevelStart};

                curLevelStart = i;
                curLevelNum = 0;
            }
        }

        prent = parent;
    }

    public int getKthAncestor(int node, int k) {
        // 如果当节点所在的层级 <= 需要向上查找的层级，就直接返回-1
        if (nodeLevel[node] <= k) {
            return -1;
        }

        // 如果往上k层之后，k层只有一个节点，那么

        // 没小于的话就一直向上查找

        // 父节点了
        int pn = prent[node];

        while (pn != -1 && k > 1) {
            k--;
            pn = prent[pn];
        }

        return pn;
    }
}
