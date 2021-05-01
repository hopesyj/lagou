package test;

import com.lagou.dao.IUserDao;
import com.lagou.io.Resources;
import com.lagou.io.sqlSession.SqlSession;
import com.lagou.io.sqlSession.SqlSessionFactory;
import com.lagou.io.sqlSession.SqlSessionFactoryBuilder;
import com.lagou.pojo.User;
import org.junit.Test;

import java.io.InputStream;
import java.util.List;

/**
 * Description
 *
 * @author sheyanjun
 * @date 2021/04/01
 */
public class IPersistenceTest {

    @Test
    public void test() throws Exception {
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();

        IUserDao userDao = sqlSession.getMapper(IUserDao.class);

        List<User> all = userDao.findAll();
        System.out.println(all);

        User user = new User();
        user.setUsername("cache");

        User user3 = userDao.findByCondition(user);
        System.out.println(user3);

        //新增
        User user1 = new User();
        user1.setUsername("sheyanjun");
        user1.setPassword("sheyanjun");
        user1.setBirthday("1995-08-01");
        userDao.addUser(user1);

        user = userDao.findByCondition(user1);
        System.out.println("addUser: " + user);

        //修改
        user.setUsername("sheyanjun_update");
        userDao.updateUser(user);

        user = userDao.findByCondition(user);
        System.out.println("updateUser: " + user);

        //删除
        userDao.deleteUser(user);

        all = userDao.findAll();
        System.out.println("deleteUser: " + all);
    }

}
