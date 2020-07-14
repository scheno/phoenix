package com.schening.phoenix.security.repository;

import com.schening.phoenix.security.domain.PermissionDO;
import org.apache.ibatis.annotations.*;

import java.util.List;


/**
 * @author schening
 * @date 2020/6/29
 */
@Mapper
public interface PermissionMapper {

    /**
     * 查询所有权限信息
     *
     * @return
     */
    @Results({
            @Result(column = "id", property = "id"),
            @Result(column = "code", property = "code"),
            @Result(column = "description", property = "description"),
            @Result(column = "url", property = "url"),
            @Result(column = "role_list", property = "roleList")
    })
    @Select("SELECT url, group_concat(t_role.role_name) as role_list\n" +
            "FROM t_permission\n" +
            "LEFT JOIN t_role_permission ON t_permission.id = t_role_permission.permission_id\n" +
            "LEFT JOIN t_role ON t_role_permission.role_id = t_role.id\n" +
            "GROUP BY url\n" +
            "ORDER BY url")
    List<PermissionDO> listPermissions();
}
