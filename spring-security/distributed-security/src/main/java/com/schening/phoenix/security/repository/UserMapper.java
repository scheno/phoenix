package com.schening.phoenix.security.repository;

import com.schening.phoenix.security.dto.UserDTO;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author schening
 * @date 2020/6/29
 */
@Repository
public class UserMapper {

    @Resource
    private JdbcTemplate jdbcTemplate;

    /**
     *
     * @param username
     * @return
     */
    public UserDTO getUserByUsername(String username) {
        String sql = "select id, username, password, fullname from t_user where username = ?";
        List<UserDTO> userDTOList = jdbcTemplate.queryForList(sql, new Object[]{username}, UserDTO.class);
        if (userDTOList == null && userDTOList.size() <= 0) {
            return null;
        }

        return userDTOList.get(0);
    }
}
