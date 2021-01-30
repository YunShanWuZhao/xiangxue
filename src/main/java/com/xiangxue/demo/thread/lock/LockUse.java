package com.xiangxue.demo.thread.lock;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockUse {

    private Lock reentrantLock = new ReentrantLock();

    private static CountDownLatch countDownLatch = new CountDownLatch(50);

    private int age = 0;

    public int getAge() {
        return age;
    }

    private synchronized void testReentrant(){
        age++;
        if(age < 10)
            testReentrant();
    }

    private void testAgeAdd() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException interruptedException) {
            interruptedException.printStackTrace();
        }
        //reentrantLock.lock();
        synchronized (reentrantLock){
            try{
                age=age+1;
                age=age+1;
                age=age+5;
                age=age+8;
                age=age+12;
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                //reentrantLock.unlock();
            }
        }

    }

    static class TA implements Runnable{

        private LockUse lockUse;

        public TA(LockUse lockUse) {
            this.lockUse = lockUse;
        }

        @Override
        public void run() {
            System.out.println("running");
            this.lockUse.testReentrant();
            countDownLatch.countDown();
        }
    }

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        LockUse lockUse = new LockUse();
        for(int i = 0;i<50;i++){
            new Thread(new TA(lockUse)).start();
        }
        try{
            countDownLatch.await();
            System.out.println("value:"+lockUse.getAge());
        }catch (InterruptedException interruptedException){
            interruptedException.printStackTrace();
        }
        System.out.println("use:"+(System.currentTimeMillis()-start));
    }
}
