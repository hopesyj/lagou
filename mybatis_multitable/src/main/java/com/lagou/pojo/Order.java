package com.lagou.pojo;

import lombok.Data;

import java.util.Date;

/**
 * Description
 *
 * @author sheyanjun
 * @date 2021/04/06
 */
@Data
public class Order {
    private Integer id;
    private String orderTime;
    private Double total;
    private Date date;

    //表明该订单属于哪个用户
    private User user;
}
