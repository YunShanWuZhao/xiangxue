package com.xiangxue.demo.redis;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class RedisServer {

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(6379);
            Socket socket = serverSocket.accept();
            byte[] message = new byte[1024];
            socket.getInputStream().read(message);
            System.out.println(new String(message));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
