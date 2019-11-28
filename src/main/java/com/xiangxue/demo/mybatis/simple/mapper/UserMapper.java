package com.xiangxue.demo.mybatis.simple.mapper;

import com.xiangxue.demo.mybatis.entity.TUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {

    Integer getUserAge(Integer id);

    String getUserName(Integer id);

    TUser getUser(Integer id);

    void saveUser(TUser TUser);

    void saveUser2(TUser TUser);

    int saveUser3(@Param("id")Integer id,@Param("name")String name,@Param("age")String age,@Param("sex") String sex, @Param("icNo") String icNo);

    int saveUser4(@Param("users") List<TUser> users);

    List<TUser> queryUserAtribute(Integer id);

    List<TUser> queryUserAttribute2(Integer id);
}
