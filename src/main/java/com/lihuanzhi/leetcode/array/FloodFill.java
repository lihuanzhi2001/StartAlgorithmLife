package com.lihuanzhi.leetcode.array;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 图形渲染
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/flood-fill
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @Author: lihuanzhi
 * @Date: 2023/6/8 14:29:30
 */
public class FloodFill {

    /**
     * 使用广度优先搜索
     */
    public int[][] floodFill(int[][] image, int sr, int sc, int color) {
        // 目标值
        int target = image[sr][sc];

        // 行高
        int rlen = image.length;
        // 列宽
        int clen = image[0].length;


        Queue<Integer> rqueue = new LinkedList<>();
        Queue<Integer> cqueue = new LinkedList<>();

        rqueue.offer(sr);
        cqueue.offer(sc);

        while (!rqueue.isEmpty() && !cqueue.isEmpty()) {
            int r = rqueue.poll();
            int c = cqueue.poll();

            image[sr][sc] = color;

            // 遍历上下左右没有被遍历过且等于目标值的数值

            // 上面
            if (r - 1 >= 0 && image[r - 1][c] == target) {
                rqueue.offer(r - 1);
                cqueue.offer(c);
                image[r-1][c] = color;
            }
            // 下面
            if (r + 1 < rlen && image[r + 1][c] == target) {
                rqueue.offer(r + 1);
                cqueue.offer(c);
                image[r+1][c] = color;
            }
            // 左边
            if (c - 1 >= 0 && image[r][c - 1] == target) {
                rqueue.offer(r);
                cqueue.offer(c - 1);
                image[r][c-1] = color;
            }
            // 右边
            if (c + 1 < clen && image[r][c + 1] == target) {
                rqueue.offer(r);
                cqueue.offer(c + 1);
                image[r][c+1] = color;
            }
        }
        return image;
    }
}
