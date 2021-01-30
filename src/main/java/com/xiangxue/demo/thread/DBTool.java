package com.xiangxue.demo.thread;

import java.sql.Connection;
import java.util.LinkedList;

public class DBTool {

    private static LinkedList<Connection> connections = new LinkedList<>();

    static {
        for (int i = 0; i < 20; i++) {
            connections.addLast(new ConnectionImpl());
        }
    }

    public static Connection fetchConn(long millions) {
        synchronized (connections) {
            long remainTime = System.currentTimeMillis() + millions;
            while (connections.isEmpty() && remainTime > 0) {
                try {
                    connections.wait(millions);
                    if (connections.isEmpty() && remainTime > 0) {
                        System.out.println("被唤醒后依然拿不到连接");
                    }
                    remainTime = remainTime - System.currentTimeMillis();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            if (connections.isEmpty()) {
                System.out.println("未获取到连接");
                return null;
            }
            return connections.removeFirst();
        }
    }

    public static void releaseConn(Connection connection) {
        if (connection != null) {
            synchronized (connections) {
                connections.addLast(connection);
                connections.notifyAll();
                System.out.println("连接释放完毕");
            }
        }
    }
}
