package com.lagou.dao;

import com.lagou.pojo.User;

import java.io.IOException;
import java.util.List;

/**
 * Description
 *
 * @author sheyanjun
 * @date 2021/04/05
 */
public interface IUserDao {
    //查询所有用户
    List<User> findAll() throws IOException;

    //多条件组合查询，演示if
    List<User> findByCondition(User user);

    //多值查询，演示foreach
    List<User> findByIds(List<Long> ids);
}
