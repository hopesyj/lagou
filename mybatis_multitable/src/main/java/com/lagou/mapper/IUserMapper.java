package com.lagou.mapper;

import com.lagou.pojo.User;
import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * Description
 *
 * @author sheyanjun
 * @date 2021/04/06
 */
@CacheNamespace //开启二级缓存
public interface IUserMapper {

    //查询用户信息，同时查询出每个用户关联的订单信息
    @Results({
            @Result(property = "id",column = "id"),
            @Result(property = "username",column = "username"),
            @Result(property = "orderList",column = "id",javaType = List.class,
            many = @Many(select = "com.lagou.mapper.IOrderMapper.findOrderByUid")),
    })
    @Select("select * from user")
    List<User> findAll();

    //查询用户信息，同时查询出每个用户关联的角色信息
    @Results({
            @Result(property = "id",column = "id"),
            @Result(property = "username",column = "username"),
            @Result(property = "roleList",column = "id",javaType = List.class,
                    many = @Many(select = "com.lagou.mapper.IRoleMapper.findRoleByUid")),
    })
    @Select("select * from user")
    List<User> findUserAndRole();

    //添加用户
    @Insert("insert into user values(#{id},#{username},null,null)")
    void addUser(User user);

    //更新用户
    @Update("update user set username = #{username} where id = #{id}")
    void updateUser(User user);

    //查询用户
    @Select("select * from user")
    List<User> selectUser();

    //删除用户
    @Delete("delete from user where id = #{id}")
    void deleteUser(Integer id);

    //根据id查询用户
    @Select("select * from user where id = #{id}")
    User findUserById(Integer id);

}
