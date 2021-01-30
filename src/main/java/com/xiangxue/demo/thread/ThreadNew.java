package com.xiangxue.demo.thread;

public class ThreadNew implements Runnable{

    @Override
    public void run() {
        while(!Thread.currentThread().isInterrupted()){
            System.out.println("do something");
            try{
                Thread.sleep(1000);
            }catch (InterruptedException interruptedException){
                System.out.println("interrupt flag:"+Thread.currentThread().isInterrupted());
                //释放锁资源
                Thread.currentThread().interrupt();
            }
        }
        System.out.println("thread interrupted:"+Thread.currentThread().isInterrupted());
    }

    public static void main(String[] args) throws InterruptedException {
        Thread currThread = new Thread(new ThreadNew());
        currThread.start();
        Thread.sleep(5000);
        currThread.interrupt();

    }
}
