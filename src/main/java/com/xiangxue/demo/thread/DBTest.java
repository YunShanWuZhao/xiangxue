package com.xiangxue.demo.thread;

import java.sql.Connection;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

public class DBTest {

    public static void main(String[] args) {
        AtomicInteger succCount = new AtomicInteger(0);
        CountDownLatch end = new CountDownLatch(50);
        for(int i = 0;i<50;i++){
            new Thread(new ConnThread(succCount)).start();

        }
        try {
            end.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("成功个数:"+succCount.get());
    }

}

class ConnThread implements Runnable {

    private AtomicInteger succCount;

    public ConnThread(AtomicInteger succCount) {
        this.succCount = succCount;
    }

    @Override
    public void run() {
        Connection connection = DBTool.fetchConn(3000);
        if(connection == null){
            System.out.println("获取连接失败");
            return;
        }
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("连接操作成功");

        succCount.incrementAndGet();
        DBTool.releaseConn(connection);
    }
}
