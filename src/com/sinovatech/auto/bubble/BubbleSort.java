package com.sinovatech.auto.bubble;

/**
 * 冒泡排序实现
 * 
 * @author:wangyuheng
 * @date:2019/2/25 19:28
 */
public class BubbleSort {

    public static void main(String[] args) {
        int[] arr = {6, 3, 8, 2, 9, 1};

        System.out.println("排序前数组为：");
        for (int i : arr) {
            System.out.print(i + " ");
        }
        for (int j = 0; j < arr.length - 1; j++) {
            for (int k = 0; k < arr.length - 1 - j; k++) {
                if (arr[k] > arr[k + 1]) {
                    int tmp = arr[k];
                    arr[k] = arr[k + 1];
                    arr[k + 1] = tmp;
                }
            }
        }
        System.out.println();
        System.out.println("排序后的数组为：");
        for (int i : arr) {
            System.out.print(i + " ");
        }

    }
}