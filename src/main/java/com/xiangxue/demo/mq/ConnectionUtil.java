package com.xiangxue.demo.mq;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class ConnectionUtil {

    public static Connection getConn() throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("47.102.223.168");
        factory.setPort(5672);
        factory.setVirtualHost("testVirtualHost1");
        factory.setUsername("test1");
        factory.setPassword("272313");
        Connection connection = factory.newConnection();
        return connection;
    }
}
