package com.schening.phoenix.security.service.impl;

import com.schening.phoenix.security.po.PermissionPO;
import com.schening.phoenix.security.po.RolePO;
import com.schening.phoenix.security.po.UserPO;
import com.schening.phoenix.security.repository.RoleMapper;
import com.schening.phoenix.security.repository.UserMapper;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author schening
 * @date 2020/6/29
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Resource
    private UserMapper userMapper;

    @Resource
    private RoleMapper roleMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //登录账号
        System.out.println("username=" + username);
        //根据账号去数据库查询...
        // 这里暂时使用静态数据
        // UserDetails userDetails = User.withUsername(username).password("$2a$10$Pc9jRtRE6vO0k42JfH78IO/UchL3W6.3YUGOx8tXMJO/aP5fUR0X2").authorities("p1").build();
        UserPO userPO = userMapper.getUserByUsername(username);
        if (userPO == null) {
            return null;
        }

        List<RolePO> rolePOList = roleMapper.listRolesByUserId(userPO.getId());

        // 查询用户权限
        List<String> roleList = new ArrayList<>();

        rolePOList.forEach(rolePO -> roleList.add("ROLE_" + rolePO.getRoleName().toUpperCase()));
        String[] roleArray = new String[roleList.size()];
        roleList.toArray(roleArray);

        UserDetails userDetails = User.withUsername(userPO.getFullname()).password(userPO.getPassword()).authorities(roleArray).build();

        return userDetails;
    }
}
