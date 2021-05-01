package com.lagou.test;

import com.lagou.dao.IUserDao;
import com.lagou.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Description
 *
 * @author sheyanjun
 * @date 2021/04/05
 */
public class MybatisTest {

    @Test
    public void findAll() throws IOException {
        //1.Resources工具类，把配置文件加载成字节输入流
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        //构造者模式
        //2.解析了配置文件，并创建了sqlSessionFactory
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        //工厂模式
        //3.生产sqlSession，默认开启一个事务，但是不会自动提交，进行增删改操作时要手动提交事务，可以传入true使其自动提交
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //4.sqlSession调用方法：查询所有selectList，查询单个selectOne，添加insert，修改update，删除delete
        List<User> userList = sqlSession.selectList("user.findAll");
        System.out.println(userList);
        sqlSession.close();
    }

    @Test
    public void saveUser() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        //构造者模式
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        //工厂模式
        SqlSession sqlSession = sqlSessionFactory.openSession();

        User user = new User();
        user.setId(4L);
        user.setUsername("sheyanjun4");
        sqlSession.insert("user.saveUser", user);
        //提交事务
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void updateUser() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        //构造者模式
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        //工厂模式
        SqlSession sqlSession = sqlSessionFactory.openSession();

        User user = new User();
        user.setId(4L);
        user.setUsername("sheyanjun4update");
        sqlSession.update("user.updateUser", user);
        //提交事务
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void deleteUser() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        //构造者模式
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        //工厂模式
        SqlSession sqlSession = sqlSessionFactory.openSession();

        User user = new User();
        user.setId(4L);
        user.setUsername("sheyanjun4update");
        sqlSession.delete("user.deleteUser", 4L);
        //提交事务
        sqlSession.commit();
        sqlSession.close();
    }

    // @Test
    // public void testUserDao() throws IOException {
    //     IUserDao userDao = new IUserDaoImpl() {};
    //     List<User> all = userDao.findAll();
    //     System.out.println(all);
    // }

    @Test
    public void testUserDaoProxy() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();

        IUserDao mapper = sqlSession.getMapper(IUserDao.class);
        List<User> all = mapper.findAll();
        System.out.println(all);

        User user = new User();
        user.setId(4L);
        user.setUsername("sheyanjun4update");
        List<User> byCondition = mapper.findByCondition(user);
        System.out.println(byCondition);

        List<Long> ids = new ArrayList<>(2);
        ids.add(1L);
        ids.add(2L);
        List<User> byIds = mapper.findByIds(ids);
        System.out.println(byIds);
    }
}
