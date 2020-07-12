package com.schening.phoenix.security.repository;

import com.schening.phoenix.security.po.PermissionPO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

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
            @Result(column = "url", property = "url")
    })
    @Select("SELECT id, code, description, url from t_permission")
    List<PermissionPO> listPermissions();
}
