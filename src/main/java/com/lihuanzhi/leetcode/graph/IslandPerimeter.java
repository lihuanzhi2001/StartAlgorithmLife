package com.lihuanzhi.leetcode.graph;

import org.omg.PortableInterceptor.INACTIVE;

import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/**
 * 岛屿的周长（2023/06/05 20:43:45）
 *
 * 给定一个 row x col 的二维网格地图 grid ，其中：grid[i][j] = 1 表示陆地， grid[i][j] = 0 表示水域。
 * 网格中的格子 水平和垂直 方向相连（对角线方向不相连）。
 * 整个网格被水完全包围，但其中恰好有一个岛屿（或者说，一个或多个表示陆地的格子相连组成的岛屿）。
 * 岛屿中没有“湖”（“湖” 指水域在岛屿内部且不和岛屿周围的水相连）。
 * 格子是边长为 1 的正方形。
 * 网格为长方形，且宽度和高度均不超过 100 。计算这个岛屿的周长。
 *
 */
public class IslandPerimeter {

    /**
     *
     * @author lihuanzhi
     * @date 2023-06-05 21:01:58
     * @param grid
     * @return int
     */
    public int islandPerimeter(int[][] grid) {
        // 队列保存
        Queue<String> queue = new LinkedList<>();

        // 结束循环标记
        boolean flag = false;
        // 找到第一块陆地
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    grid[i][j] = 2;     // 路边入列之后标记为已读
                    queue.offer(i + "-" + j);
                    flag = true;
                    break;
                }
            }
            if (flag) {
                break;
            }
        }
        // 岛屿周长
        int daoLen = 0;

        while (!queue.isEmpty()) {
            // 拿到横竖坐标
            String row_col = queue.poll();
            String[] rc = row_col.split("-");
            int row = Integer.parseInt(rc[0]);
            int col = Integer.parseInt(rc[1]);

            // 每一块陆地4条边，每挨一块就少一条表
            int len = 4;

            // 判断上面有没有
            if ((row - 1) >= 0) {
                // 不是海周长-1
                if (grid[row - 1][col] != 0) {
                    len--;
                }
                // 陆地且未读取就入列
                if (grid[row - 1][col] == 1) {
                    grid[row - 1][col] = 2;
                    queue.offer((row - 1) + "-" + col);
                }
            }

            // 判断左边
            if ((col - 1) >= 0) {
                // 不是海周长-1
                if (grid[row][col - 1] != 0) {
                    len--;
                }
                // 陆地且未读取就入列
                if (grid[row][col - 1] == 1) {
                    grid[row][col - 1] = 2;
                    queue.offer(row + "-" + (col - 1));
                }
            }
            // 判断右边
            if ((col + 1) < grid[0].length) {
                // 不是海周长-1
                if (grid[row][col + 1] != 0) {
                    len--;
                }
                // 陆地且未读取就入列
                if (grid[row][col + 1] == 1) {
                    grid[row][col + 1] = 2;
                    queue.offer(row + "-" + (col + 1));
                }
            }
            // 判断下面
            if ((row + 1) < grid.length) {
                // 不是海周长-1
                if (grid[row + 1][col] != 0) {
                    len--;
                }
                // 陆地且未读取就入列
                if (grid[row + 1][col] == 1) {
                    grid[row + 1][col] = 2;
                    queue.offer((row + 1) + "-" + col);
                }
            }
            // 周长相加
            daoLen += len;
        }
        return daoLen;
    }
}
