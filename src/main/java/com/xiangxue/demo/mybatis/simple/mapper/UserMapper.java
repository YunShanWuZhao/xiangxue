package com.xiangxue.demo.mybatis.simple.mapper;

import com.xiangxue.demo.mybatis.entity.UserEntity;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {

    Integer getUserAge(Integer id);

    String getUserName(Integer id);

    UserEntity getUser(Integer id);

    void saveUser(UserEntity userEntity);

    void saveUser2(UserEntity userEntity);

    int saveUser3(@Param("id")Integer id,@Param("name")String name,@Param("age")String age,@Param("sex") String sex, @Param("icNo") String icNo);
}
