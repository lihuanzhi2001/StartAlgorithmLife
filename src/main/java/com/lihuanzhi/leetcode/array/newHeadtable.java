package com.lihuanzhi.leetcode.array;

public class newHeadtable {
    public static void main(String[] args) {

        System.out.println(new newHeadtable().canFinish(3, new int[][]{{2, 0}, {2, 1}}));
    }

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        if (prerequisites.length < 1) {
            return true;
        }
        for (int i = 0; i < prerequisites.length; i++) {
            for (int a = 0; a < prerequisites.length; a++) {
                if ((prerequisites[i][0] == prerequisites[a][1]) & (prerequisites[i][1] == prerequisites[a][0])) {
                    return false;
                }
            }
        }
        int[] enable = new int[numCourses];
        for (int i = 0; i < numCourses; i++) {
            enable[i] = -1;
        }
        int xiabiao = 0;
        for (int i = 0; i < prerequisites.length; i++) {
            boolean f = true;
            for (int a = 0; a < prerequisites.length; a++) {
                if (prerequisites[i][0] == prerequisites[a][1]) {
                    f = false;
                    break;
                }
            }
            if (f) {
                boolean hf = true;
                for (int a = 0; a < xiabiao; a++) {
                    if (prerequisites[i][0] == enable[a]) {
                        hf = false;
                        break;
                    }
                }
                if (hf) {
                    enable[xiabiao] = prerequisites[i][0];
                    xiabiao++;
                }
            }
        }


        for (int e = 0; e < prerequisites.length; e++) {
            for (int i = 0; i < prerequisites.length; i++) {
                boolean f = false;
                for (int a = 0; a < xiabiao; a++) {
                    if (prerequisites[i][0] == enable[a]) {
                        f = true;
                        break;
                    }
                }


                if (f) {
                    for (int c = 0; c < prerequisites.length; c++) {
                        if (prerequisites[i][1] == prerequisites[c][1]) {
                            boolean ff = false;

                            for (int a = 0; a < xiabiao; a++) {
                                if (prerequisites[c][0] == enable[a]) {
                                    ff = true;
                                    break;
                                }
                            }
                            if (!ff) {
                                f = false;
                                break;
                            }
                        }
                    }
                }


                if (f) {
                    boolean hf = true;
                    for (int a = 0; a < xiabiao; a++) {
                        if (prerequisites[i][1] == enable[a]) {
                            hf = false;
                            break;
                        }
                    }
                    if (hf) {
                        enable[xiabiao] = prerequisites[i][1];
                        xiabiao++;
                    }
                }

            }
        }


        for (int a = 0; a < xiabiao; a++) {
            System.out.println(enable[a]);
        }
        boolean jieguo = true;
        for (int i = 0; i < prerequisites.length; i++) {
            boolean f1 = false;
            boolean f2 = false;
            for (int a = 0; a < xiabiao; a++) {
                if (prerequisites[i][0] == enable[a]) {
                    f1 = true;

                }
                if (prerequisites[i][1] == enable[a]) {
                    f2 = true;

                }

            }
            if (!(f1 & f2))
                jieguo = false;
        }
        return jieguo;

    }
}
