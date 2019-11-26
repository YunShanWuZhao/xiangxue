package com.xiangxue.demo.mybatis.simple.mapper;

import com.xiangxue.demo.mybatis.entity.UserEntity;

public interface UserMapper {

    Integer getUserAge(Integer id);

    String getUserName(Integer id);

    void saveUser(UserEntity userEntity);

    void saveUser2(UserEntity userEntity);
}
