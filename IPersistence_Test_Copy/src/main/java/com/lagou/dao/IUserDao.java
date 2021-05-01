package com.lagou.dao;

import com.lagou.pojo.User;

import java.util.List;

/**
 * Description
 *
 * @author sheyanjun
 * @date 2021/04/04
 */
public interface IUserDao {

    //查询所有用户
    List<User> findAll();

    //根据条件查询
    User findByCondition(User user);

    //添加用户
    Integer addUser(User user);

    //更新用户
    Integer updateUser(User user);

    //删除用户
    Integer deleteUser(User user);

}
