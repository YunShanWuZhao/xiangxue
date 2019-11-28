package com.xiangxue.demo.mybatis;

import com.xiangxue.demo.mybatis.entity.TUser;
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
        TUser TUser = userMapper.getUser(1);
        System.out.println(TUser);
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
        TUser TUser = new TUser();
        TUser.setName("批量1");
        TUser.setSex("1");
        TUser.setAge("11");
        TUser.setIcNo("87435848367123");
        TUser TUser2 = new TUser();
        TUser2.setName("批量2");
        TUser2.setSex("1");
        TUser2.setAge("11");
        TUser2.setIcNo("87435848367124");
        TUser TUser3 = new TUser();
        TUser3.setName("批量3");
        TUser3.setSex("0");
        TUser3.setAge("11");
        TUser3.setIcNo("87435848367125");
        List<TUser> userEntities = new ArrayList<>();
        userEntities.add(TUser);
        userEntities.add(TUser2);
        userEntities.add(TUser3);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        int count = userMapper.saveUser4(userEntities);
        System.out.println("count:" + count);
    }

    @Test
    public void testAssociation(){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        List<TUser> users = userMapper.queryUserAtribute(1);
        System.out.println(users.get(0).getPosition());
    }

    @Test
    public void testAssociationLazy(){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        List<TUser> users = userMapper.queryUserAttribute2(2);
        System.out.println(users.get(0).getPosition());
    }

}
