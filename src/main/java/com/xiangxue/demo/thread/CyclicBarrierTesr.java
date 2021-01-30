package com.xiangxue.demo.thread;

import java.util.Map;
import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierTesr {

    static CyclicBarrier cyclicBarrier;

    private static ConcurrentHashMap<String, Integer> resultMap = new ConcurrentHashMap();

    public static void main(String[] args) {
        cyclicBarrier = new CyclicBarrier(3, new CollectJob());
        for (int i = 0; i < 3; i++) {
            new Thread(new SubJob()).start();
        }
        for (Map.Entry<String, Integer> entry : resultMap.entrySet()) {
            System.out.println("entry key:" + entry.getKey() + ";value:" + entry.getValue());
        }
    }

    public static class CollectJob implements Runnable {
        @Override
        public void run() {
            System.out.println("collect resultMap---");
            for (Map.Entry<String, Integer> entry : resultMap.entrySet()) {
                System.out.println("result key:" + entry.getKey() + ";value:" + entry.getValue());
            }
        }
    }

    static class SubJob implements Runnable {
        @Override
        public void run() {
            System.out.println("doing something prepare ...");
            try {
                int random = new Random().nextInt(10);
                Thread.sleep(2000 + random * 1000);
                System.out.println(Thread.currentThread().getName() + " do ok");
                resultMap.put(Thread.currentThread().getName(), random);
                cyclicBarrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }

            System.out.println("now doing something ok");
        }
    }
}
