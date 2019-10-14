package com.xiangxue.demo.mq.simple;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.DeliverCallback;
import com.rabbitmq.client.Delivery;
import com.xiangxue.demo.mq.ConnectionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class MqConSumer1 {

    private static Logger logger = LoggerFactory.getLogger(MqConSumer1.class);

    public static final String QUEUE_NAME = "testQueue1";

    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = ConnectionUtil.getConn();
        Channel channel = connection.createChannel();
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        DeliverCallback callback = new DeliverCallback() {
            @Override
            public void handle(String s, Delivery delivery) throws IOException {
                String message = new String(delivery.getBody(), "UTF-8");
                logger.info("receive msg : {}", message);
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    logger.error("sleep error", e);
                }
            }
        };
        channel.basicConsume(QUEUE_NAME, true, callback, consumerTag -> {
        });
    }
}
