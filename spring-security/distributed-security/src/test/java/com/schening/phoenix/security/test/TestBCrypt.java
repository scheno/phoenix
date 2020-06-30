package com.schening.phoenix.security.test;

import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCrypt;

public class TestBCrypt {

    @Test
    public void test() {
        // 对原始密码进行加密
        String hashPw = BCrypt.hashpw("123", BCrypt.gensalt());
        System.out.println(hashPw);
        // 校验原始密码和BCrypt密码是否一致
        boolean checkPw = BCrypt.checkpw("123", "$2a$10$Pc9jRtRE6vO0k42JfH78IO/UchL3W6.3YUGOx8tXMJO/aP5fUR0X2");
        System.out.println(checkPw);
    }
}
