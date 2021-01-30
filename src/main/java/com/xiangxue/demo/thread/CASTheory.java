package com.xiangxue.demo.thread;

import java.util.concurrent.atomic.AtomicInteger;

public class CASTheory {

    private AtomicInteger digit = new AtomicInteger(0);

    public void operate() {
        while (true) {
            int originalValue = digit.get();
            System.out.println(Thread.currentThread().getName()+":originalValue:"+originalValue);
            if (digit.compareAndSet(originalValue, ++originalValue)) {
                System.out.println(Thread.currentThread().getName()+"newValue:"+originalValue);
                break;
            }
            System.out.println("recalculate num...");
        }
    }

    public static void main(String[] args) {
        CASTheory casTheory = new CASTheory();
        for(int i = 0;i<10;i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    casTheory.operate();
                }
            }).start();
        }
    }

}
