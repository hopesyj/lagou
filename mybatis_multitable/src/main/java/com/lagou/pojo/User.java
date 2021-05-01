package com.lagou.pojo;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Description
 *
 * @author sheyanjun
 * @date 2021/04/06
 */
@Data
@Table(name = "user")
public class User implements Serializable {
    @Id //对应的是主键 id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //设置主键的生成策略
    private Integer id;
    // @Column 当类名与属性名不一致时使用
    private String username;

    // //该用户所具有的订单信息
    // List<Order> orderList;
    // //该用户所具有的角色信息
    // List<Role> roleList;
}
