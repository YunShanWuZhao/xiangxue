package com.xiangxue.demo.mybatis;

import com.xiangxue.demo.mybatis.entity.UserEntity;
import com.xiangxue.demo.mybatis.simple.mapper.UserMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class SimpleTest {

    private SqlSessionFactory sqlSessionFactory;

    @Before
    public void init() throws IOException {
        InputStream inputStream = Resources.getResourceAsStream("mybatis/simple/mybatis-config.xml");
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        inputStream.close();
    }

    @Test
    public void testParamMap() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        UserEntity userEntity = userMapper.getUser(1);
        System.out.println(userEntity);
    }

    @Test
    public void testInsertUser3() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        Integer id = null;
        int count = userMapper.saveUser3(id, "李叉叉", "18", "1", "3325181548631");
        System.out.println("count:" + count);
        System.out.println("id:" + id);
    }

    @Test
    public void testBatchInsert() {
        UserEntity userEntity = new UserEntity();
        userEntity.setName("批量1");
        userEntity.setSex("1");
        userEntity.setAge("11");
        userEntity.setIcNo("87435848367123");
        UserEntity userEntity2 = new UserEntity();
        userEntity2.setName("批量2");
        userEntity2.setSex("1");
        userEntity2.setAge("11");
        userEntity2.setIcNo("87435848367124");
        UserEntity userEntity3 = new UserEntity();
        userEntity3.setName("批量3");
        userEntity3.setSex("0");
        userEntity3.setAge("11");
        userEntity3.setIcNo("87435848367125");
        List<UserEntity> userEntities = new ArrayList<>();
        userEntities.add(userEntity);
        userEntities.add(userEntity2);
        userEntities.add(userEntity3);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        int count = userMapper.saveUser4(userEntities);
        System.out.println("count:" + count);
    }

}
