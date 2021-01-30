package com.xiangxue.demo.thread;

public class EndThread extends Thread {

    @Override
    public void run() {
        String threadName = Thread.currentThread().getName();
        while (true) {
            System.out.println(threadName + " is running");
            System.out.println(threadName + " interrupt flag is:" + isInterrupted());
            if(interrupted()){
                System.out.println("interrupted, exit...");
                System.out.println("interrupted, status:"+isInterrupted());
                break;
            }
        }
    }

    public static void main(String[] args) throws Exception {
        Thread endThread = new EndThread();
        endThread.start();
        Thread.sleep(1000);
        endThread.interrupt();
    }
}
