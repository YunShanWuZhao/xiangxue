package com.xiangxue.demo.mybatis.simple;

import com.xiangxue.demo.mybatis.entity.TUser;
import com.xiangxue.demo.mybatis.simple.mapper.UserMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;

public class SimpleService {

    private static Logger logger = LoggerFactory.getLogger(SimpleService.class);

    private SqlSessionFactory sqlSessionFactory;

    public SimpleService() {
        try {
            String mybatisResource = "mybatis/simple/mybatis-config.xml";
            InputStream configStream = Resources.getResourceAsStream(mybatisResource);
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(configStream);
        } catch (Exception e) {
            logger.error("build sqlSessionFactory error", e);
        }
    }

    public void saveUser() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        TUser TUser = new TUser();
        TUser.setName("罗叉叉");
        TUser.setAge("1");
        TUser.setIcNo("332526199312272316");
        userMapper.saveUser2(TUser);
        sqlSession.commit();
        Integer id = TUser.getId();
        logger.info("id:{}", id);
    }

}
