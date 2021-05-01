package com.lagou.mapper;

import com.lagou.pojo.Role;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Description
 *
 * @author sheyanjun
 * @date 2021/04/11
 */
public interface IRoleMapper {
    //通过用户id查询角色信息
    @Select("select * from sys_role r,sys_user_role ur where ur.userid = #{uid} and r.id = ur.roleid")
    List<Role> findRoleByUid(Integer uid);
}
