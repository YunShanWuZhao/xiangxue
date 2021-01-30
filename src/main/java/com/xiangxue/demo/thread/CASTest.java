package com.xiangxue.demo.thread;

import java.util.concurrent.atomic.AtomicReference;

public class CASTest {

    static class User{
        public String userName;
        public String age;
    }

    public static void main(String[] args) {
        AtomicReference<User> atomicReference = new AtomicReference<>();
        User user = new User();
        user.userName = "罗理伟";
        User user2 = new User();
        user2.userName = "叉叉";
        atomicReference.set(user);
        atomicReference.compareAndSet(user, user2);
        User newUser = atomicReference.get();
        System.out.println(newUser.userName);
    }
}
