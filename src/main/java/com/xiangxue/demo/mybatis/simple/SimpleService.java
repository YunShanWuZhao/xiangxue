package com.xiangxue.demo.mybatis.simple;

import com.xiangxue.demo.mybatis.entity.UserEntity;
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
        UserEntity userEntity = new UserEntity();
        userEntity.setName("罗叉叉");
        userEntity.setAge("1");
        userEntity.setIcNo("332526199312272316");
        userMapper.saveUser(userEntity);
        Integer id = userEntity.getId();
        logger.info("id:{}", id);
    }

}
