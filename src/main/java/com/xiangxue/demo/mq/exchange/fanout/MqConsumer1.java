package com.xiangxue.demo.mq.exchange.fanout;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.DeliverCallback;
import com.xiangxue.demo.mq.ConnectionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class MqConsumer1 {

    private static Logger logger = LoggerFactory.getLogger(MqConsumer1.class);

    private static final String EXCHANGE_NAME = "testExchange1";

    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = ConnectionUtil.getConn();
        Channel channel = connection.createChannel();

        channel.exchangeDeclare(EXCHANGE_NAME, "fanout");
        String queueName = channel.queueDeclare().getQueue();
        channel.queueBind(queueName, EXCHANGE_NAME, "");
        DeliverCallback callback = (consumerTag, delivery) ->{
            String msg = new String(delivery.getBody());
            logger.info("mqCounsumer1 has received  msg:{}", msg);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };
        channel.basicConsume(queueName, true, callback, consumerTag ->{});
    }

}
