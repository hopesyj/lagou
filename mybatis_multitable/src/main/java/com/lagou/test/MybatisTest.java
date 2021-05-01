package com.lagou.test;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lagou.mapper.IOrderMapper;
import com.lagou.mapper.IUserMapper;
import com.lagou.mapper.UserMapper;
import com.lagou.pojo.Order;
import com.lagou.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tk.mybatis.mapper.entity.Example;

import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Description
 *
 * @author sheyanjun
 * @date 2021/04/06
 */
public class MybatisTest {

    private IUserMapper mapper;
    private UserMapper userMapper;

    @BeforeEach
    public void before() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        mapper = sqlSession.getMapper(IUserMapper.class);
        userMapper = sqlSession.getMapper(UserMapper.class);
    }

    @Test
    public void test1() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();

        IOrderMapper mapper = sqlSession.getMapper(IOrderMapper.class);
        Map<String, Object> map = new HashMap<>();
        Calendar instance = Calendar.getInstance();
        instance.setTime(new Date());
        instance.set(Calendar.DAY_OF_MONTH, -1);
        map.put("startDate", instance.getTime());
        map.put("endDate", new Date());
        List<Order> orderAndUser = mapper.findOrderByDate(map);
        System.out.println(orderAndUser);
    }

    @Test
    public void test2() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();

        IUserMapper mapper = sqlSession.getMapper(IUserMapper.class);
        List<User> users = mapper.findAll();
        System.out.println(users);
    }

    @Test
    public void test3() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();

        IUserMapper mapper = sqlSession.getMapper(IUserMapper.class);
        List<User> users = mapper.findUserAndRole();
        System.out.println(users);
    }

    @Test
    public void test4() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();

        IUserMapper mapper = sqlSession.getMapper(IUserMapper.class);
        List<User> users = mapper.findUserAndRole();
        System.out.println(users);
    }

    @Test
    public void operateUser() {
        User user = new User();
        user.setId(3);
        user.setUsername("sheyanjun");
        mapper.addUser(user);
        System.out.println(mapper.selectUser());

        user.setUsername("sheyanjun_updated");
        mapper.updateUser(user);
        System.out.println(mapper.selectUser());

        mapper.deleteUser(3);
        System.out.println(mapper.selectUser());
    }

    @Test
    public void pageHelperTest() {
        PageHelper.startPage(1, 1);
        List<User> userList = mapper.selectUser();
        System.out.println(userList);

        PageInfo<User> pageInfo = new PageInfo<>(userList);
        System.out.println("总条数:" + pageInfo.getTotal());
        System.out.println("总页数:" + pageInfo.getPages());
        System.out.println("当前页:" + pageInfo.getPageNum());
        System.out.println("每页显示的条数:" + pageInfo.getPageSize());
    }

    @Test
    public void mapperTest() {
        User user = new User();
        user.setId(1);

        //(1)mapper基础接口
        //select 接口
        User user1 = userMapper.selectOne(user); //根据实体中的属性进行查询，只能有一个返回值
        List<User> users = userMapper.select(null); //查询全部结果
        userMapper.selectByPrimaryKey(1); //根据主键字段进行查询，方法参数必须包含完整的主键属性，查询条件使用等号
        userMapper.selectCount(user); //根据实体中的属性查询总数，查询条件使用等号

        // insert 接口
        int insert = userMapper.insert(user); //保存一个实体，null值也会保存，不会使用数据库默认值
        int i = userMapper.insertSelective(user); //保存实体，null的属性不会保存， 会使用数据库默认值

        // update 接口
        int i1 = userMapper.updateByPrimaryKey(user);//根据主键更新实体全部字段， null值会被更新

        // delete 接口
        int delete = userMapper.delete(user); //根据实体属性作为条件进行删除，查询条件使用等号
        userMapper.deleteByPrimaryKey(1); //根据主键字段进行删除，方法参数必须包含完整的主键属性

        //(2)example方法
        Example example = new Example(User.class);
        example.createCriteria().andEqualTo("id", 1);
        example.createCriteria().andLike("val", "1");
        //自定义查询
        List<User> users1 = userMapper.selectByExample(example);
    }

}
