package com.schening.phoenix.security.uaa.repository;

import com.schening.phoenix.security.uaa.dto.PermissionDTO;
import com.schening.phoenix.security.uaa.dto.UserDTO;
import org.apache.ibatis.annotations.*;

import java.util.List;


/**
 * @author schening
 * @date 2020/6/29
 */
@Mapper
public interface UserMapper {

    /**
     * 根据用户名查询用户
     *
     * @param username
     * @return
     */
    @Results({
            @Result(column = "id", property = "id"),
            @Result(column = "username", property = "username"),
            @Result(column = "password", property = "password"),
            @Result(column = "fullname", property = "fullname"),
            @Result(column = "phone", property = "phone")
    })
    @Select("SELECT id, username, password, fullname FROM t_user where username = #{username}")
    UserDTO getUserByUsername(@Param("username") String username);

    /**
     * 根据用户名查询用户权限
     *
     * @param userId
     * @return
     */
    @Results({
            @Result(column = "id", property = "id"),
            @Result(column = "code", property = "code"),
            @Result(column = "description", property = "description"),
            @Result(column = "url", property = "url")
    })
    @Select("SELECT * from t_permission WHERE id IN (SELECT permission_id FROM t_role_permission " +
            "WHERE role_id IN(SELECT role_id FROM t_user_role WHERE user_id = #{userId}))")
    List<PermissionDTO> findPermissionByUserId(@Param("userId") String userId);
}
