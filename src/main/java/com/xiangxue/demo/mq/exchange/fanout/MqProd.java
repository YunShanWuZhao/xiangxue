package com.xiangxue.demo.mq.exchange.fanout;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.impl.AMQImpl;
import com.xiangxue.demo.mq.ConnectionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class MqProd {

    private static Logger logger = LoggerFactory.getLogger(MqProd.class);

    public static final String EXCHANGE_NAME = "testExchange1";

    public static void main(String[] args) throws IOException, TimeoutException {
        String message = "小跳蛙a";
        Connection connection = ConnectionUtil.getConn();
        Channel channel = connection.createChannel();
        channel.exchangeDeclare(EXCHANGE_NAME, "fanout");
        channel.basicPublish(EXCHANGE_NAME, "", null, message.getBytes());
        logger.info("has send message:{]", message);
    }
}
