package com.xiangxue.demo.thread.future;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class UserFuture {

    private static class  UseCallbale implements Callable<Integer>{
        private int sum;
        @Override
        public Integer call() throws Exception {
            System.out.println("子线程开始计算");

            for(int i = 0; i<5000; i++){
                if(Thread.currentThread().isInterrupted()){
                    System.out.println("子线程被终止");
                    return sum;
                }
                sum = sum+i;
            }
            System.out.println("子线程计算结束:"+sum);
            return sum;
        }
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        UseCallbale useCallbale = new UseCallbale();
        FutureTask<Integer> futureTask = new FutureTask<>(useCallbale);

        new Thread(futureTask).start();

        futureTask.cancel(true);
        int result = futureTask.get();

        System.out.println("result:"+result);
    }
}
