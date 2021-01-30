package com.xiangxue.demo.thread.express;

public class ExpressTest {

    private static ExpressOperator expressOperator = new ExpressOperator();

    static class KmWaitor implements Runnable{
        @Override
        public void run() {
            try {
                expressOperator.waitKmChange();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    public static void main(String[] args) throws InterruptedException {
        for(int i = 0;i<5;i++){
            new Thread(new KmWaitor()).start();
        }
        Thread.sleep(3000);
        expressOperator.changeKm();
    }
}
