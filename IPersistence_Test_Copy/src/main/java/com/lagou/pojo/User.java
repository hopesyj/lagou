package com.lagou.pojo;

import lombok.Data;

/**
 * Description
 *
 * @author sheyanjun
 * @date 2021/04/01
 */
@Data
public class User {
    //主键标识
    private Integer id;
    //用户名
    private String username;
    // 密码
    private String password;
    // 生日
    private String birthday;


}
