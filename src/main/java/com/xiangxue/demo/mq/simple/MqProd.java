package com.xiangxue.demo.mq.simple;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.xiangxue.demo.mq.ConnectionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class MqProd {

    private static Logger logger = LoggerFactory.getLogger(MqProd.class);

    public static final String QUEUE_NAME = "testQueue1";

    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = ConnectionUtil.getConn();
        Channel channel = connection.createChannel();
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        String message = "hello dear;";
        channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
        logger.info("has send msg");
        channel.close();
        connection.close();
    }
}
