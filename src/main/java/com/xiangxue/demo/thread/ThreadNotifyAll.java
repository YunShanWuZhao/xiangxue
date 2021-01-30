package com.xiangxue.demo.thread;

public class ThreadNotifyAll {

    public static ThreadNotifyAll threadNotifyAll = new ThreadNotifyAll();

    public synchronized void waitA(){
        while (true){
            try {
                System.out.println("Thread:"+Thread.currentThread().getName()+",start to wait.");
                wait();
                System.out.println("Thread:"+Thread.currentThread().getName()+",end to wait..");
                Thread.sleep(5000);
                System.out.println("Thread:"+Thread.currentThread().getName()+", finish task...");
                break;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public synchronized void notifyA(){
        try {
            Thread.sleep(2000);
            System.out.println("start to notify");
            notifyAll();
            System.out.println("finish to notify all...");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static class ThreadWaitA extends Thread{
        @Override
        public void run() {
            threadNotifyAll.waitA();
        }
    }

    public static class ThreadNotifyA extends Thread{
        @Override
        public void run() {
            threadNotifyAll.notifyA();
        }
    }

    public static void main(String[] args) {
        for(int i = 0;i<5;i++){
            Thread thread = new ThreadWaitA();
            thread.setName("thread-----"+i);
            thread.start();
        }
        new ThreadNotifyA().start();
    }
}
