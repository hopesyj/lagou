package com.lagou.test;

import com.lagou.mapper.IUserMapper;
import com.lagou.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;

/**
 * Description
 *
 * @author sheyanjun
 * @date 2021/04/11
 */
public class CacheTest {

    private IUserMapper mapper;
    private SqlSessionFactory sqlSessionFactory;

    @BeforeEach
    public void before() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        mapper = sqlSession.getMapper(IUserMapper.class);
    }

    @Test
    public void firstLevelCache() {
        //第一次查询id为1的用户
        User user1 = mapper.findUserById(1);

        //第二次查询id为1的用户
        User user2 = mapper.findUserById(1);

        System.out.println(user1 == user2);

        user1.setUsername("update");
        mapper.updateUser(user1);

        //第三次查询id为1的用户
        User user3 = mapper.findUserById(1);

        System.out.println(user2 == user3);
    }

    @Test
    public void secondLevelCache() {
        SqlSession sqlSession1 = sqlSessionFactory.openSession();
        SqlSession sqlSession2 = sqlSessionFactory.openSession();
        SqlSession sqlSession3 = sqlSessionFactory.openSession();

        IUserMapper mapper1 = sqlSession1.getMapper(IUserMapper.class);
        IUserMapper mapper2 = sqlSession2.getMapper(IUserMapper.class);
        IUserMapper mapper3 = sqlSession3.getMapper(IUserMapper.class);

        User user1 = mapper1.findUserById(1);
        sqlSession1.close();//清空一级缓存
        User user2 = mapper2.findUserById(1);

        System.out.println(user1 == user2);//false：二级缓存的是数据，不是对象

        user2.setUsername("cache");
        mapper3.updateUser(user2);
        sqlSession3.commit();//清空二级缓存

        User user3 = mapper2.findUserById(1);
        System.out.println(user3);
    }
}
