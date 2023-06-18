package com.lihuanzhi.leetcode.array;

/**
 * 和并两个有序数组（2023/05/22 23:23:09）
 * 那我就是要这题来练习一下算法中的经典八大排序
 * 总之就是先把数组合并，然后再进行排序
 * 但是题目的进阶是使用时间复杂度为 O(m + n) 解决问题
 */
public class MergeArray {
    public static void main(String[] args) {
        int[] nums1 = new int[]{1,3,5,0,0,0};
        int[] nums2 = new int[]{2,2,6};
        merge(nums1,3, nums2, 3);

    }

    /**
     * 解题，这里就不练排序了，直接解题吧
     * 思路：
     * nums1有效元素的尾指针mt，nums2的有效元素的尾指针nt，以及nums1数组的末尾指针mnt
     * mt指针和nt指针的元素比较，谁大谁就移动到mnt指针位置，同时元素大的指针左移，mnt指针也左移
     */
    public static void jieTi(int[] nums1, int m, int[] nums2, int n) {
        // nums1数组指针
        int mt = m - 1;
        // nums2数组指针
        int nt = n - 1;
        // 最大数指针
        int mnt = m + n - 1;

        while (nt >= 0) {
            // 如果 mt == -1 ， 则只需要把nums2数组剩余元素依次插入nums1数组
            if (mt == -1 || nums2[nt] >= nums1[mt]) {
                nums1[mnt] = nums2[nt];
                nt--;
            } else {
                nums1[mnt] = nums1[mt];
                mt--;
            }
            mnt--;
        }
    }

    // 打印数组nums的方法
    public static void printArray(int[] nums1) {
        for (int i : nums1) {
            System.out.print(i + "\t");
        }
        System.out.println();
    }

    /**
     * 练习排序用
     */
    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        if (n == 0) {
            return;
        }
        for (int i = 0; i < n; i++) {
            nums1[m + i] = nums2[i];
        }
        // 排序前的nums1
        System.out.println("排序前nums1：");
        printArray(nums1);

        // TODO 排序算法
//        bubbleSortAsc(nums1);       // 冒泡升序
//        bubbleSortDesc(nums1);      // 冒泡降序
//          selectSortAsc(nums1);       // 选择排序升序
//          selectSortDesc(nums1);       // 选择排序降序
//          insertSortAsc(nums1);       // 插入排序升序
//          insertSortDesc(nums1);       // 插入排序升序
//          quickSortAsc(nums1);       // 快速排序升序 --- (左指针右移，右指针左移)
//          quickSortAsc1(nums1);       // 快速排序升序 ---（双指针重合）
          quickSortDesc1(nums1);       // 快速排序降序 ---（双指针重合）

        // 排序后的nums1
        System.out.println("排序后的nums1：");
        printArray(nums1);
    }

    // TODO 八大排序：冒泡、选择、插入、快速、希尔、规定、堆、基数排序

    /**
     * 冒泡排序 ---> (升序)
     * 思想：（升序，降序就是反过来）
     * 相邻的两个元素比较，比较出大的数就前后交换
     */
    public static void bubbleSortAsc(int[] nums1){
        for(int i = 0; i < nums1.length; i++) {
            for(int j = 0; j < nums1.length - 1 - i; j++) {
                if (nums1[j] > nums1[j + 1]) {
                    int temp = nums1[j];
                    nums1[j] = nums1[j + 1];
                    nums1[j + 1] = temp;
                }
            }
        }
    }

    /**
     * 冒泡排序 ---> (降序)
     * 思想：（升序，降序就是反过来）
     * 相邻的两个元素比较，比较出大的数就前后交换
     */
    public static void bubbleSortDesc(int[] nums1){
        for(int i = 0; i < nums1.length; i++) {
            for(int j = nums1.length - 1; j > i; j--) {
                if (nums1[j] > nums1[j - 1]) {
                    int temp = nums1[j];
                    nums1[j] = nums1[j - 1];
                    nums1[j - 1] = temp;
                }
            }
        }
    }

    /**
     * 选择排序 ---> （升序排序）
     * 思想：
     * 每次选择最小或者最大的数放到指定位置，之后每一次都不用比较该数
     */
    public static void selectSortAsc(int[] nums1) {
        for (int i = 0; i < nums1.length; i++) {
            // 假设当前位置数为最小
            int min = i;
            for (int j = i; j < nums1.length; j++) {
                if (nums1[min] > nums1[j]) {
                    min = j;
                }
            }
            // 如果min的索引发生了改变
            if (min != i) {
                int temp = nums1[min];
                nums1[min] = nums1[i];
                nums1[i] = temp;
            }
        }
    }

    /**
     * 选择排序 ---> （降序排序）
     * 思想：
     * 每次选择最小或者最大的数放到指定位置，之后每一次都不用比较该数
     */
    public static void selectSortDesc(int[] nums1) {
        for (int i = 0; i < nums1.length; i++) {
            // 假设当前位置数为最大
            int max = i;
            for (int j = i; j < nums1.length; j++) {
                if (nums1[max] < nums1[j]) {
                    max = j;
                }
            }
            // 如果min的索引发生了改变
            if (max != i) {
                int temp = nums1[max];
                nums1[max] = nums1[i];
                nums1[i] = temp;
            }
        }
    }

    /**
     * 插入排序 ---> （升序排序）
     * 思想：默认前面的数都是有序的，只需要找到第一个不符合的数就可以直接返回，而不用遍历整个数组
     */
    public static void insertSortAsc(int[] nums1) {
        for (int i = 1; i < nums1.length; i++) {
            for (int j = i; j > 0; j--) {
                if (nums1[j] >= nums1[j - 1]) {
                    break;
                }
                int temp = nums1[j];
                nums1[j] = nums1[j - 1];
                nums1[j - 1] = temp;
            }
        }
    }

    /**
     * 插入排序 ---> （降序排序）
     * 思想：默认前面的数都是有序的，只需要找到第一个不符合的数就可以直接返回，而不用遍历整个数组
     */
    public static void insertSortDesc(int[] nums1) {
        for (int i = 1; i < nums1.length; i++) {
            for (int j = i; j > 0; j--) {
                if (nums1[j] <= nums1[j - 1]) {
                    break;
                }
                int temp = nums1[j];
                nums1[j] = nums1[j - 1];
                nums1[j - 1] = temp;
            }
        }
    }

    /**
     * 快速排序 ---> (升序排序) ---> 左指针右移、右指针左移想法...（这个想法没问题，但好像又有问题...）
     *
     * 思想： 每次找一个基准值，把所有小于该基准的数放在该基准值的左边，大于该基准的数放在右边
     * 1个左指针 left，1个右指针 right，1个基准值指针 basic
     * 左指针左移到比基准值大的数时就停止移动，此时到右指针移动
     * 右指针移动到比基准值小的数时停止移动，交换留左右指针的数值
     * 交换完之后左右指针继续移动，直到左右指针重合时，左右指针的值与基准值指针交换   （这想法有问题吧）
     *
     */
    public static void quickSortAsc(int[] nums1) {
        // 找元素最后最后一个数作为基准值（也可以以把第一个数或者中间数作为基准值）
        quickSortRecursion(nums1, 0, nums1.length - 1);

    }

    // 快速排序的递归调用
    public static void quickSortRecursion(int[] nums1, int head, int tail) {
        // head 头，tail 尾

        // 如果头尾重合 或者 头大于尾 时直接返回
        if (head >= tail) {
            return;
        }

        int l = head;           // 左指针
        int r = tail - 1;       // 右指针
        int basic = tail;       // 基准指针

        while (l < r) {
            // l指针数值比基准值大则停止移动
            while (nums1[l] <= nums1[basic]) {
                if (l >= r) break;
                l++;
            }
            // r指针数值比基准值小则停止移动
            while (nums1[r] >= nums1[basic]) {
                if (l >= r) break;
                r--;
            }
            // l != r 就交换
            if (l != r) {
                int temp = nums1[l];
                nums1[l] = nums1[r];
                nums1[r] = temp;
            }
        }
        // 如果lr指向的值比basic大则直接交换
        if (nums1[l] > nums1[basic]) {
            int temp = nums1[l];
            nums1[l] = nums1[basic];
            nums1[basic] = temp;
            // basic 与 lr 索引交换
            basic = l;
        }

        System.out.println("快速排序：");
        printArray(nums1);

        // 此时lr指向的数是排好序了的
        /**
         * TODO 这里我sb了，这里不能使用我下面注释的写法，起码得--basic起步，而不是basic--
         */
//        quickSortRecursion(nums1, head, basic--);   // 排左边
//        quickSortRecursion(nums1, basic++, tail);   // 排右边
        quickSortRecursion(nums1, head, basic - 1);   // 排左边
        quickSortRecursion(nums1, basic + 1, tail);   // 排右边

    }

    /**
     * 快速排序2 ---> (升序排序) ---> 双指针
     * 双指针重合，开始i指针不动，j指针右移，当j指针的数值比基准值大时把i，j的数值交换
     */
    public static void quickSortAsc1(int[] nums1) {
        quickSortAsc(nums1, 0, nums1.length - 1);
    }

    // 双指针重合，的快速排序递归调用，head 和 tail 分别表示需要排序的元素范围 --- 升序
    public static void quickSortAsc(int[] nums1, int head, int tail) {
        // 当head > tail 和 head == tail 时直接返回
        if (head < tail) {
            // 基准值排到指定位置
            int basic = basicSortAsc(nums1, head, tail);
            // 排基准指针左边的数组
            quickSortAsc(nums1, head, basic - 1);
            // 排基准指针右边的数组
            quickSortAsc(nums1, basic + 1, tail);
        }
    }

    // 双指针重合的基准值排序 --- 升序
    public static int basicSortAsc(int nums1[], int i, int basic) {
        for (int j = i; j < basic; j++) {
            // TODO 这里一开始我理解错了，应该是一开始当j指针指向的值比basic小时 i指针指向的值 和 j指针指向的值 互换
            // TODO 然后i指针右移
            if (nums1[j] < nums1[basic]) {
                int temp = nums1[i];
                nums1[i] = nums1[j];
                nums1[j] = temp;
                i++;
                System.out.println("快速排序：");
                printArray(nums1);
            }
        }
        // 如果nums[i] > nums1[basic] 的话就把他们两个交换同时，baisc指针和i指针也交换
        if (nums1[i] > nums1[basic]) {
            int temp = nums1[i];
            nums1[i] = nums1[basic];
            nums1[basic] = temp;
            basic = i;
            System.out.println("快速排序：");
            printArray(nums1);
        }
        return basic;
    }

    /**
     * 快速排序2 ---> (降序排序) ---> 双指针
     * 双指针重合，开始i指针不动，j指针右移，当j指针的数值比基准值大时把 i，j的数值交换 同时i指针右移
     */
    public static void quickSortDesc1(int[] nums1) {
        qsortDesc(nums1, 0, nums1.length - 1);
    }

    // 双指针重合，的快速排序递归调用，head 和 tail 分别表示需要排序的元素范围 --- 降序
    public static void qsortDesc(int[] nums1, int head, int tail) {
        if (head < tail) {
            int basic = basicSortDesc(nums1, head, tail);
            // 排左边
            qsortDesc(nums1, head, basic - 1);
            // 排右边
            qsortDesc(nums1, basic + 1, tail);
        }
    }

    // 双指针重合的基准值排序 ---> 降序
    public static int basicSortDesc(int[] nums1, int i, int basic) {
        for (int j = i; j < basic; j++) {
            if (nums1[j] > nums1[basic]) {
                int temp = nums1[i];
                nums1[i] = nums1[j];
                nums1[j] = temp;
                i++;
            }
        }

        if (nums1[i] < nums1[basic]) {
            int temp = nums1[i];
            nums1[i] = nums1[basic];
            nums1[basic] = temp;
            basic = i;
        }

        return basic;
    }
}
