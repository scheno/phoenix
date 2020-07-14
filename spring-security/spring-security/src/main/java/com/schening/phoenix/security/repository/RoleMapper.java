package com.schening.phoenix.security.repository;

import com.schening.phoenix.security.po.RolePO;
import org.apache.ibatis.annotations.*;

import java.util.List;


/**
 * @author schening
 * @date 2020/7/13
 */
@Mapper
public interface RoleMapper {

    /**
     * 查询所有权限信息
     *
     * @return
     */
    @Results({
            @Result(column = "id", property = "id"),
            @Result(column = "role_name", property = "roleName"),
            @Result(column = "description", property = "description"),
            @Result(column = "status", property = "status")
    })
    @Select("SELECT id, role_name, description, status from t_role")
    List<RolePO> listRoles();

    /**
     * 查询所有权限信息
     *
     * @param userId
     * @return
     */
    @Results({
            @Result(column = "id", property = "id"),
            @Result(column = "role_name", property = "roleName"),
            @Result(column = "description", property = "description"),
            @Result(column = "status", property = "status")
    })
    @Select("SELECT id, role_name, description, status from t_role where id in (select role_id from t_user_role where user_id = #{userId})")
    List<RolePO> listRolesByUserId(@Param("userId") String userId);

}
