package com.xiangxue.demo.thread;

public class ThreadJoin {

    private static class ThreadA implements Runnable{
        Thread thread;

        @Override
        public void run() {
            if(thread != null){
                try {
                    System.out.println("ThreadA wait for thread :"+thread.getName()+" to finish");
                    thread.join();
                } catch (InterruptedException e) {
                    System.out.println("join interrupted");
                    e.printStackTrace();
                }
            }
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Thread A run finish");
        }
    }

    private static class ThreadB implements Runnable{

        @Override
        public void run() {
            System.out.println("start run ThreadB...");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("end run ThreadB...");
        }
    }



    public static void main(String[] args) {
        ThreadB threadB = new ThreadB();
        ThreadA threadA = new ThreadA();
        Thread thread = new Thread(threadB);
        threadA.thread = thread;
        new Thread(threadA).start();
    }
}
