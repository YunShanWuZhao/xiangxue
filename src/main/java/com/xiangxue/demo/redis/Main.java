package com.xiangxue.demo.redis;

public class Main {

    public static void main(String[] args) {
        RedisClient redisClient = new RedisClient();
        redisClient.set("name", "PC");
    }
}
