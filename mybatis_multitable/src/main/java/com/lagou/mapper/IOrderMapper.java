package com.lagou.mapper;

import com.lagou.pojo.Order;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * Description
 *
 * @author sheyanjun
 * @date 2021/04/06
 */
public interface IOrderMapper {

    //查询订单的同事还查询该订单所属的用户
    // @Results({
    //         @Result(property = "id", column = "id"),
    //         @Result(property = "orderTime", column = "ordertime"),
    //         @Result(property = "total", column = "total"),
    //         @Result(property = "user", column = "uid", javaType = User.class,
    //                 one = @One(select = "com.lagou.mapper.IUserMapper.findUserById"))
    // })
    // @Select("select * from orders")
    List<Order> findOrderAndUser();



    List<Order> findOrderByDate(Map<String,Object> map);


    //根据用户id查询订单列表
    @Select("select * from orders where uid = #{uid}")
    List<Order> findOrderByUid(Integer uid);
}
