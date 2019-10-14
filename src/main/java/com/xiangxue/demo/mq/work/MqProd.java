package com.xiangxue.demo.mq.work;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.MessageProperties;
import com.xiangxue.demo.mq.ConnectionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class MqProd {

    private static Logger logger = LoggerFactory.getLogger(MqProd.class);

    public static final String QUEUE_NAME = "queue_test2";

    public static void main(String[] args) throws IOException, TimeoutException {
        String[] messages = new String[]{"aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", "aa", "aaa", "aaaa", "aaaaa", "aaaaaa", "aaaaaaa","aaaaaaaa","b","bb","bbb","bbbb"};
        Connection conn = ConnectionUtil.getConn();
        Channel channel = conn.createChannel();
        channel.queueDeclare(QUEUE_NAME, true, false, false, null);
        for (String msg : messages) {
            channel.basicPublish("", QUEUE_NAME, MessageProperties.PERSISTENT_TEXT_PLAIN, msg.getBytes());
            logger.info("has publish msg:{}", msg);
        }
        channel.close();
        conn.close();
    }
}
