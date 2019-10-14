package com.xiangxue.demo.mq;

public class MqConsumer1 {
    public void listener(String foo) {
        System.out.println("MqConsumer1 receive msg:" + foo);
    }
}
