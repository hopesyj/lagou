// package com.lagou.dao;
//
// import com.lagou.pojo.User;
// import org.apache.ibatis.io.Resources;
// import org.apache.ibatis.session.SqlSession;
// import org.apache.ibatis.session.SqlSessionFactory;
// import org.apache.ibatis.session.SqlSessionFactoryBuilder;
//
// import java.io.IOException;
// import java.io.InputStream;
// import java.util.List;
//
// /**
//  * Description
//  *
//  * @author sheyanjun
//  * @date 2021/04/05
//  */
// public class IUserDaoImpl implements IUserDao {
//     @Override
//     public List<User> findAll() throws IOException {
//         //1.Resources工具类，把配置文件加载成字节输入流
//         InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
//         //构造者模式
//         //2.解析了配置文件，并创建了sqlSessionFactory
//         SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
//         //工厂模式
//         //3.生产sqlSession，默认开启一个事务，但是不会自动提交，进行增删改操作时要手动提交事务，可以传入true使其自动提交
//         SqlSession sqlSession = sqlSessionFactory.openSession();
//         //4.sqlSession调用方法：查询所有selectList，查询单个selectOne，添加insert，修改update，删除delete
//         List<User> userList = sqlSession.selectList("user.findAll");
//         sqlSession.close();
//         return userList;
//     }
//
// }
