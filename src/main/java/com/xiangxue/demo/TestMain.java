package com.xiangxue.demo;

import java.util.concurrent.*;

public class TestMain {

    public static void main(String[] args) {
        BlockingQueue blockingQueue = new LinkedBlockingQueue(16);
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(8, 20, 10, TimeUnit.MINUTES, blockingQueue);


        int[] arr = new int[]{3, 1, 456, 34, 7, 265, 7, 4, 7654, 673, 54, 234, 643, 6565,};
        int[] result = new int[arr.length];
        sort(arr, result, 0, arr.length - 1);
        for(int i = 0;i<result.length;i++){
            System.out.print(result[i]+",");
        }
    }

    public static void sort(int[] arr, int[] result, int start, int end) {
        if (start >= end) {
            return;
        }
        int len = end - start, mid = ((end - start) >> 1) + start;
        int start1 = start, end1 = mid;
        int start2 = mid + 1, end2 = end;
        sort(arr, result, start1, end1);
        sort(arr, result, start2, end2);
        int k = start;
        while (start1 <= end1 && start2 <= end2) {
            result[k++] = arr[start1] < arr[start2] ? arr[start1++] : arr[start2++];
        }
        while (start1 <= end1) {
            result[k++] = arr[start1++];
        }
        while (start2 <= end2) {
            result[k++] = arr[start2++];

        }
        for (k = start; k <= end; k++) {
            arr[k] = result[k];
        }

    }

}
