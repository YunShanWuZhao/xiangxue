package com.xiangxue.demo.redis;


import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;

import java.io.IOException;
import java.net.Socket;

public class RedisClient {

    Socket socket;

    public static void main(String[] args) {
        Jedis jedis = new Jedis("47.102.223.168", 6379);
        jedis.auth("272313");
        jedis.set("name", "liwei");
        jedis.close();
        System.out.println("罗理伟".getBytes().length);
        Pipeline pipeline = jedis.pipelined();


    }

    public RedisClient(){
        try {
            socket = new Socket("47.102.223.168", 6379);
            StringBuffer sbf = new StringBuffer("*2").append("\r\n")
                    .append("$4").append("\r\n")
                    .append("auth").append("\r\n")
                    .append("$6").append("\r\n")
                    .append("272313").append("\r\n");
            socket.getOutputStream().write(sbf.toString().getBytes());
            byte[] result = new byte[2048];
            socket.getInputStream().read(result);
            System.out.println("connect result:"+new String(result));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public String set(String key, String value){
        StringBuffer sbf = new StringBuffer("*3").append("\r\n")
                .append("$3").append("\r\n")
                .append("SET").append("\r\n")
                .append("$").append(key.getBytes().length).append("\r\n")
                .append(key).append("\r\n")
                .append("$").append(value.getBytes().length).append("\r\n")
                .append(value).append("\r\n");
        try {
            socket.getOutputStream().write(sbf.toString().getBytes());
            byte[] result = new byte[2048];
            socket.getInputStream().read(result);
            System.out.println("get return msg:"+new String(result));
            return new String(result);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "";
    }
}
