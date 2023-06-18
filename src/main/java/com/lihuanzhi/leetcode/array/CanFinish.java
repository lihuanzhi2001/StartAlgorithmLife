package com.lihuanzhi.leetcode.array;

import java.util.ArrayList;
import java.util.List;

/**
 * 课程表(2023/05/31 09:38:18)
 *
 * 你这个学期必须选修 numCourses 门课程，记为 0 到 numCourses - 1 。
 * 在选修某些课程之前需要一些先修课程。 先修课程按数组 prerequisites 给出，其中 prerequisites[i] = [ai, bi] ，表示如果要学习课程 ai 则 必须 先学习课程  bi 。
 *
 *     例如，先修课程对 [0, 1] 表示：想要学习课程 0 ，你需要先完成课程 1 。
 * 请你判断是否可能完成所有课程的学习？如果可以，返回 true ；否则，返回 false 。
 *
 * 示例 1：
 * 输入：numCourses = 2, prerequisites = [[1,0]]
 * 输出：true
 * 解释：总共有 2 门课程。学习课程 1 之前，你需要完成课程 0 。这是可能的。
 *
 * 示例 2：
 * 输入：numCourses = 2, prerequisites = [[1,0],[0,1]]
 * 输出：false
 * 解释：总共有 2 门课程。学习课程 1 之前，你需要先完成​课程 0 ；并且学习课程 0 之前，你还应先完成课程 1 。这是不可能的。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/course-schedule
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class CanFinish {
    public static void main(String[] args) {
        CanFinish cf = new CanFinish();
        int numCourses = 4;
        int[][] prerequisites = new int[][]{{0,1},{0,2},{1,3},{3,0}};
        cf.canFinish(numCourses, prerequisites);
    }

    /**
     * 找环：【虽然LeetCode没通过（超时），但是思路应该没问题，看能不能优化了】
     * 我的思路就是先建一个二阶01矩阵，行列都为numCourses的矩阵
     * 有值的行列索引就设为1，最后建立一个对角线不存在值，对角不存在值的一个二阶01矩阵（因为如果对角存在、对角线存在就证明有环，直接返回false）
     *
     * 最后建立完矩阵之后就从外围遍历
     * 外围只有行列都有值的时候才可能存在环，存在环的话就压缩矩阵，例如：1-7 7-2 压缩成 1-2 最后矩阵1-2设为1
     * 一直压缩，每次都判断对角是否存在，因为压缩之后矩阵压缩之后对角可能存在值，所以每次都得判断对角是否存在
     * 对角存在的话证明有环，直接返回false，没有的话就继续进行压缩
     * 直到找到对角存在返回false 或者 没找到就返回true，表示没环
     */
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        // 最多排列组合 (n - 1) + (n-2) + ... + 0 个
        int maxDui = (numCourses) * (numCourses - 1 + 0) / 2;
        // 长度超过最大排列组合必出现环
        if (prerequisites.length > maxDui) {
            return false;
        }

        // 建立二维数组
        byte[][] barr = new byte[numCourses][numCourses];

//        // 行一维空矩阵
//        byte[] rbarr = new byte[numCourses];
//        // 列一维空矩阵
//        byte[] cbarr = new byte[numCourses];

        for (int[] prerequisite : prerequisites) {
            // 如果在对角线则直接返回false
            if (prerequisite[0] == prerequisite[1]) {
                return false;
            }
            // 对角存在直接返回false
            if (barr[prerequisite[1]][prerequisite[0]] != 0) {
                return false;
            }
            barr[prerequisite[0]][prerequisite[1]] = 1;
            // 标记行有值
//            rbarr[prerequisite[0]] = 1;
            // 标记列有值
//            cbarr[prerequisite[1]] = 1;
        }

        // 二维数组建立完成
        // 行一维空矩阵建立完成
        // 列一维空矩阵建立完成

        // 外层循环
        // 行 barr.length
        // 列 barr[0].length

        // 二级矩阵 【由外层向内层】 循环遍历
        for (int i = barr.length - 1; i >= 0; i--) {
            byte rb = 0;        // 行有值标识
            byte cb = 0;        // 列有值标识
            // 判断对角是否存在，存在就直接返回false
            for (int j = 0; j <= i; j++) {
                if ((barr[j][i] & barr[i][j]) == 1) {
                    return false;
                }
                rb |= barr[i][j];
                cb |= barr[j][i];
            }
            // 如果行和列都没值就直接跳过
            if ((rb & cb) == 0) {
                continue;
            }

            // 对角不存在且行列都有值的话就进行进行矩阵压缩
            for (int r = 0; r <= i; r++) {
                for (int c = i; c >= 0; c--) {
                    // 都有值的话就进行压缩
                    if ((barr[r][i] & barr[i][c]) == 1) {
                        // 对角有值就返回false
                        if (barr[c][r] == 1) {
                            return false;
                        }
                        barr[r][c] = 1;
                    }
                }
            }
        }
        return true;
    }

    /**
     * 找环：【虽然LeetCode没通过（超时），但是思路应该没问题，看能不能优化了】
     *
     * 这次我换一个思路来，因为转成成一个01二阶矩阵的话只有对角部分有值，所以这里需要压缩存在矩阵
     * 但是，如果他们不在同一侧怎么办呢？那就把他们移动到上三角部分（下三角部分），并赋值为-1，表示为存在于对角
     *
     */
    public boolean canFinish2(int numCourses, int[][] prerequisites) {
        // 最多排列组合 (n - 1) + (n-2) + ... + 0 个
        int maxDui = (numCourses) * (numCourses - 1 + 0) / 2;
        // 长度超过最大排列组合必出现环
        if (prerequisites.length > maxDui) {
            return false;
        }

        // 建立二维数组
        byte[][] barr = new byte[numCourses][numCourses];

        for (int[] prerequisite : prerequisites) {
            // 如果在对角线则直接返回false
            if (prerequisite[0] == prerequisite[1]) {
                return false;
            }
            // 对角存在直接返回false
            if (barr[prerequisite[1]][prerequisite[0]] != 0) {
                return false;
            }
            barr[prerequisite[0]][prerequisite[1]] = 1;
        }
        // 二维数组建立完成

        // 外层循环
        // 行 barr.length
        // 列 barr[0].length

        // 二级矩阵 【由外层向内层】 循环遍历
        for (int i = barr.length - 1; i >= 0; i--) {
            // 行集合
            List<Integer> rList = new ArrayList<>();
            // 列集合
            List<Integer> cList = new ArrayList<>();

            // 找到所有有值的行列位置
            for (int j = 0; j <= i; j++) {
                // 行不为空
                if (barr[j][i] != 0) {
                    rList.add(j);
                }
                // 列不为空
                if (barr[i][j] != 0) {
                    cList.add(j);
                }

            }
            // 如果 行或列 其中有一个没值，就一定不会产生环，直接跳过就好
            if (rList.size() == 0 || cList.size() == 0) {
                continue;
            }

            // 行列都有值的话就进行进行矩阵压缩，压缩的同时判断压缩位置对角是否存在
            for (int r = 0; r < rList.size(); r++) {
                for (int c = 0; c < cList.size(); c++) {
                    // 压缩时判断压缩位置对角是否存在
                    if (barr[cList.get(c)][rList.get(r)] != 0) {
                        return false;
                    }
                    // 不存在就压缩
                    barr[rList.get(r)][cList.get(c)] = 1;
                }
            }
        }
        return true;
    }

    /**
     * 官方解答：深度优先搜索
     *
     * 额，不是很懂，研究一下
     */
    // 索引下标表示每个先学课程，list表示下一个能学的课程集合
    List<List<Integer>> edges;
    // 该数组表示当前节点是否被遍历过
    int[] visited;
    // 找到环时结束循环标记
    boolean valid = true;

    public boolean canFinish3(int numCourses, int[][] prerequisites) {
        edges = new ArrayList<List<Integer>>();
        for (int i = 0; i < numCourses; ++i) {
            edges.add(new ArrayList<Integer>());
        }
        visited = new int[numCourses];
        for (int[] info : prerequisites) {
            edges.get(info[1]).add(info[0]);
        }
        for (int i = 0; i < numCourses && valid; ++i) {
            // 当前节点未被遍历过就进行深度搜索
            if (visited[i] == 0) {
                dfs(i);
            }
        }
        return valid;
    }

    public void dfs(int u) {
        // 标记当前节点为遍历中
        visited[u] = 1;
        // 遍历当前节点的下一节点
        for (int v: edges.get(u)) {
            // 如果当前节点下一节点未被遍历就深度搜索
            if (visited[v] == 0) {
                dfs(v);
                // 遇到环就直接返回了，不用继续遍历
                if (!valid) {
                    return;
                }

            }
            // 如果当前节点的下一节点为遍历中，那么表示当前深度存在环，直接返回flase
            else if (visited[v] == 1) {
                valid = false;
                return;
            }
        }
        // 遍历完不存在环的环标记当前节点为2，表示已经遍历完了
        visited[u] = 2;
    }



}
