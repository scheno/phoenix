package com.schening.phoenix.security.service;

import com.schening.phoenix.security.dto.UserDTO;
import com.schening.phoenix.security.repository.UserMapper;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author schening
 * @date 2020/6/29
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Resource
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //登录账号
        System.out.println("username=" + username);
        //根据账号去数据库查询...
        // 这里暂时使用静态数据
        // UserDetails userDetails = User.withUsername(username).password("$2a$10$Pc9jRtRE6vO0k42JfH78IO/UchL3W6.3YUGOx8tXMJO/aP5fUR0X2").authorities("p1").build();
        UserDTO userDTO = userMapper.getUserByUsername(username);
        if(userDTO == null){ return null; }
        //这里暂时使用静态数据
        UserDetails userDetails = User.withUsername(userDTO.getFullname()).password(userDTO.getPassword()).authorities("p1").build();

        return userDetails;
    }
}
