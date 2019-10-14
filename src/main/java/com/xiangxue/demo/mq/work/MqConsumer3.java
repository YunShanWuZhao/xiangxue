package com.xiangxue.demo.mq.work;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.DeliverCallback;
import com.xiangxue.demo.mq.ConnectionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class MqConsumer3 {
    private static Logger logger = LoggerFactory.getLogger(MqConsumer1.class);

    public static final String QUEUE_NAME = "queue_test2";

    public static void main(String[] args) throws IOException, TimeoutException {
        Connection conn = ConnectionUtil.getConn();
        Channel channel = conn.createChannel();
        channel.queueDeclare(QUEUE_NAME, true, false, false, null);
        channel.basicQos(1);
        DeliverCallback callback = (tag, deliver) -> {
            String msg = new String(deliver.getBody());
            logger.info("consumer3 handle msg:{}", msg);
            try {
                Thread.sleep(msg.length() * 1000);
            } catch (InterruptedException e) {
                logger.error("sleep error", e);
            }
            logger.info("consumer1 done msg:{}", msg);
            channel.basicAck(deliver.getEnvelope().getDeliveryTag(), false);
        };
        channel.basicConsume(QUEUE_NAME, false, callback, consumerTag -> {
        });
    }
}
