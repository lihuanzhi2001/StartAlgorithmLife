package com.lihuanzhi;

import com.lihuanzhi.leetcode.tree.TreeNode;

import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Test{
    public static void main(String[] args) {
//        int[] nums = new int[]{1,5,3,7,6,2,2,4,8,9};
//
//        int yu = nums[0];
//
//        for (int i = 1; i < nums.length; i++) {
//            System.out.println(yu);
//            yu &= nums[i];
//        }
//
//        Map<Integer, Integer> map = new HashMap<>();
//        map.put(1, 1);
//        System.out.println(map.get(2));

//        byte[] barr = new byte[128];
//        for (byte b : barr) {
//            System.out.println(b);
//        }

//        int a = 10;
//        int b = 15;
//
//        double cs = (double) 3;
//
//        double c = (a + b) / cs;
//        System.out.println(c);

        String a = "li";
        String b = a + "zhi";

        String c = "lizhi";

        System.out.println(b == c);

    }

    public void shanchu() {
        // java删除除字母数字字符串之外的字符
        String str = "A man, a plan, a canal: Panama Z";

        char[] strArr = str.toCharArray();

        // 新字符串指针
        int index = 0;
        for (char c : strArr) {
            // 0 - 9
            if (48 <= c && c <= 57) {
                strArr[index++] = c;

            }
            // a - z
            else if (65 <= c && c <= 90) {
                strArr[index++] = c;
            }
            // A - Z
            else if (97 <= c && c <= 122) {
                strArr[index++] = c;
            }
        }
        String newStr = String.valueOf(strArr, 0, index).toLowerCase();


        // 修改后的新字符串
        System.out.println(newStr);
    }
}
