package com.xiangxue.demo.thread;

public class ThreadNW {

    public static ThreadNW threadNW = new ThreadNW();

    public String a;
    private String b;

    public synchronized void doA() {
        if (!"a".equals(a)) {
            try {
                System.out.println("waiting aaa");
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("111a now is a");
        }
        System.out.println("222a now is a");
    }

    public synchronized void doB() {
        System.out.println("111 sleep 3000");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        a = "a";
        try {
            System.out.println("222sleep 3000");
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        notifyAll();
    }

    public static void main(String[] args) {
        new ThreadA().start();
        new ThreadB().start();
    }

    static class ThreadB extends Thread {

        @Override
        public void run() {
            threadNW.doB();
        }
    }

    static class ThreadA extends Thread {
        @Override
        public void run() {
            threadNW.doA();
        }
    }
}