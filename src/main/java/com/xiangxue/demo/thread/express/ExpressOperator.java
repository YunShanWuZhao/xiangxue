package com.xiangxue.demo.thread.express;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ExpressOperator {

    private int km = 100;

    private Lock kmLock = new ReentrantLock(false);
    private Condition kmCondition = kmLock.newCondition();

    public void waitKmChange() throws InterruptedException {
        kmLock.lock();
        try{
            while (km > 10){
                System.out.println(Thread.currentThread().getName()+"里程数大于10，等待");
                kmCondition.await();
                System.out.println(Thread.currentThread().getName()+"被唤醒，重新争取锁");
            }
            Thread.sleep(2000);
            System.out.println(Thread.currentThread().getName()+"里程数小于10，结束等待");
        }finally {
            kmLock.unlock();
        }
    }

    public void changeKm() throws InterruptedException{
        kmLock.lock();
        try{
            km = 5;
            kmCondition.signalAll();
            System.out.println(Thread.currentThread().getName()+"里程数已被修改");
        }finally {
            kmLock.unlock();
        }
    }
}
