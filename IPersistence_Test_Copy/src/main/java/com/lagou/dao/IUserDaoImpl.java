// package com.lagou.dao;
//
// import com.lagou.io.Resources;
// import com.lagou.io.sqlSession.SqlSession;
// import com.lagou.io.sqlSession.SqlSessionFactory;
// import com.lagou.io.sqlSession.SqlSessionFactoryBuilder;
// import com.lagou.pojo.User;
//
// import java.io.InputStream;
// import java.util.List;
//
// /**
//  * Description
//  *
//  * @author sheyanjun
//  * @date 2021/04/04
//  */
// public class IUserDaoImpl implements IUserDao {
//
//     public List<User> findAll() throws Exception {
//         InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
//         SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
//         SqlSession sqlSession = sqlSessionFactory.openSession();
//
//         //调用
//         List<User> userList = sqlSession.selectList("user.selectList");
//         System.out.println(userList);
//         return userList;
//     }
//
//     public User findByCondition(User user) throws Exception {
//         InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
//         SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
//         SqlSession sqlSession = sqlSessionFactory.openSession();
//
//         //调用
//         User user2 = sqlSession.selectOne("user.selectOne", user);
//         System.out.println(user2);
//         return user;
//     }
//
// }
