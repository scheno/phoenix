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
        //根据账号去数据库查询...
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
